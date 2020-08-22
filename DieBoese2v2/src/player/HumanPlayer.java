package player;

import java.awt.Point;

import io.Input;
import io.localization.ConsoleOutput;
import model.Data;
import model.InvalidMoveException;

public class HumanPlayer extends Player {
	private String coordinates;
	private final boolean DEBUG = false;

	public HumanPlayer(final char figure, Data data) {
		super(figure, data);
	}

	@Override
	public void move() {
		boolean exceptionThrown;
		do {
			exceptionThrown = false;
			ConsoleOutput.printCoordinateInput();
			this.readCoordinates();
			try {
				this.data.getValidation().validateString(this.data.getBoardSize(), this.coordinates);
			} catch (final InvalidStringException e) {
				System.out.println(e.getMessage());
				exceptionThrown = true;
			}
			if (!exceptionThrown) {
				this.setMyMove(this.convertCoordinates());
				try {
					this.data.getMove().setMove(this.data, this);
				} catch (InvalidMoveException e) {
					System.out.println(e.getMessage());
					this.move();
				}
			}
		} while (exceptionThrown);
		this.data.load(this.getFigure(), this.getMyMove());

	}

	private Point convertCoordinates() {
		return new Point(this.convertLetter(), this.convertNumber());
	}

	/**
	 * converts letter in string to int
	 */
	private int convertLetter() {
		var letter = 0;
		for (var i = 0; i < this.coordinates.length(); i++)
			if (((this.coordinates.charAt(i) >= 'a') && (this.coordinates.charAt(i) <= 'z')))
				letter = this.coordinates.charAt(i) - 'a';
		return letter;
	}

	/**
	 * converts number in string to int
	 */
	private int convertNumber() {
		var number = "";
		for (var i = 0; i < this.coordinates.length(); i++)
			if (((this.coordinates.charAt(i) >= '0') && (this.coordinates.charAt(i) <= '9')))
				number += this.coordinates.charAt(i);
		return (this.data.getBoardSize() - Integer.valueOf(number));
	}

	private void readCoordinates() {
		this.coordinates = Input.readInput().toLowerCase();
	}
}
