package player;

import java.awt.Point;

import exceptions.InvalidMoveException;
import exceptions.InvalidStringException;
import io.Input;
import io.localization.ConsoleOutput;
import model.Data;

public class HumanPlayer extends Player {
	private String coordinates;

	public HumanPlayer(final char figure, final Data data) {
		super(figure, data);
	}

	@Override
	public void move() {
		boolean exception;
		do {
			exception = false;
			ConsoleOutput.printCoordinateInput();
			this.readCoordinates();
			if (this.validCoordinates())
				exception = this.setMove();
		} while (exception);
		this.updateData();
	}

	private int adaptNumberToBoardOrder(final String number) {
		return this.data.getBoardSize() - Integer.valueOf(number);
	}

	private Point convertCoordinates() {
		return new Point(this.convertLetter(), this.convertNumber());
	}

	/**
	 * converts letter in string to int
	 */
	private int convertLetter() {
		for (var i = 0; i < this.coordinates.length(); i++)
			if (this.isLetter(i))
				return this.coordinates.charAt(i) - 'a';
		return 0;
	}

	/**
	 * converts number in string to int
	 */
	private int convertNumber() {
		var number = "";
		for (var i = 0; i < this.coordinates.length(); i++)
			if (this.isNumber(i))
				number += this.coordinates.charAt(i);
		return this.adaptNumberToBoardOrder(number);
	}

	private boolean isLetter(final int position) {
		return (this.coordinates.charAt(position) >= 'a') && (this.coordinates.charAt(position) <= 'z');
	}

	private boolean isNumber(final int position) {
		return (this.coordinates.charAt(position) >= '0') && (this.coordinates.charAt(position) <= '9');
	}

	private void readCoordinates() {
		this.coordinates = Input.readInput().toLowerCase();
	}

	private boolean setMove() {
		this.setMyMove(this.convertCoordinates());
		try {
			this.setMoveInData();
		} catch (final InvalidMoveException e) {
			System.out.println(e.getMessage());
			return true;
		}
		return false;
	}

	private void setMoveInData() throws InvalidMoveException {
		this.data.getTurn().setMove(this.data, this);
	}

	private void updateData() {
		this.data.load(this.getFigure(), this.getMyMove());
	}

	private void validateCoordinates() throws InvalidStringException {
		this.data.getValidation().validateString(this.data.getBoardSize(), this.coordinates);
	}

	private boolean validCoordinates() {
		try {
			this.validateCoordinates();
		} catch (final InvalidStringException e) {
			System.out.println(e.getMessage());
			return true;
		}
		return false;
	}
}
