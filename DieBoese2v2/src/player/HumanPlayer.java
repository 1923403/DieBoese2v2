package player;

import java.awt.Point;

import io.Input;
import io.localization.ConsoleOutput;
import model.Data;

public class HumanPlayer extends Player {
	private String coordinates;
	private final boolean DEBUG = false;

	public HumanPlayer(final char figure, Data data) {
		super(figure, data);
	}

	@Override
	public void move() {
		ConsoleOutput.printCoordinateInput();
		this.readCoordinates();
		try {
			this.validateString(this.data.getBoardSize());
		} catch (final InvalidStringException e) {
			System.out.println(e.getMessage());
			this.move();
		}
		this.setMyMove(this.convertCoordinates(this.data.getBoardSize()));
	}

	private Point convertCoordinates(final int boardSize) {
		return new Point(this.getLetter(), this.getNumber(boardSize));
	}

	/**
	 * gets letter in string as int
	 */
	private int getLetter() {
		var letter = 0;
		for (var i = 0; i < this.coordinates.length(); i++)
			if (((this.coordinates.charAt(i) >= 'a') && (this.coordinates.charAt(i) <= 'z')))
				letter = this.coordinates.charAt(i) - 'a';
		return letter;
	}

	/**
	 * gets number in string as int
	 */
	private int getNumber(final int boardSize) {
		var number = "";
		for (var i = 0; i < this.coordinates.length(); i++)
			if (((this.coordinates.charAt(i) >= '0') && (this.coordinates.charAt(i) <= '9')))
				number += this.coordinates.charAt(i);
		return (boardSize - Integer.valueOf(number));
	}

	private void readCoordinates() {
		this.coordinates = Input.readInput().toLowerCase();
	}

	/**
	 * checks if character is a letter or a number
	 *
	 * @throws InvalidStringException
	 */
	private void validateCharacters() throws InvalidStringException {
		for (var i = 0; i < this.coordinates.length(); i++)
			if ((this.coordinates.charAt(i) < '0')
					|| ((this.coordinates.charAt(i) > '9') && (this.coordinates.charAt(i) < 'a'))
					|| (this.coordinates.charAt(i) > 'z'))
				throw new InvalidStringException(this.coordinates.charAt(i) + " is not a valid character!");
	}

	/**
	 * checks if string length is 2 or 3
	 *
	 * @throws InvalidStringException
	 */
	private void validateLength() throws InvalidStringException {
		if (this.coordinates.length() < 2)
			throw new InvalidStringException("Please enter more than one character!");
		if (this.coordinates.length() > 3)
			throw new InvalidStringException("Please don't enter more than three characters!");
	}

	/**
	 * checks if letter is valid
	 *
	 * @throws InvalidStringException
	 */
	private void validateLetter(final int boardSize) throws InvalidStringException {
		for (var i = 0; i < this.coordinates.length(); i++)
			if (this.coordinates.charAt(i) >= 'a')
				if ((this.coordinates.charAt(i) - 'a') > (boardSize - 1))
					throw new InvalidStringException(
							"Please enter a letter between a and " + (char) ('a' + (boardSize - 1)) + "!");
	}

	/**
	 * checks if there is only one letter
	 *
	 * @throws InvalidStringException
	 */
	private void validateLetterCount() throws InvalidStringException {
		var letterCount = 0;
		for (var i = 0; i < this.coordinates.length(); i++)
			if (this.coordinates.charAt(i) >= 'a')
				if (++letterCount > 1)
					throw new InvalidStringException("Please don't enter more than one letter!");
		if (letterCount == 0)
			throw new InvalidStringException("You need to enter a letter as well!");
	}

	/**
	 * checks if number is valid
	 *
	 * @throws InvalidStringException
	 */
	private void validateNumber(final int boardSize) throws InvalidStringException {
		var number = "";
		for (var i = 0; i < this.coordinates.length(); i++)
			if (((this.coordinates.charAt(i) >= '0') && (this.coordinates.charAt(i) <= '9')))
				number += this.coordinates.charAt(i);
		if ((Integer.valueOf(number) <= 0) || (Integer.valueOf(number) > (boardSize)))
			throw new InvalidStringException("Please enter a number between 1 and " + boardSize + "!");
	}

	/**
	 * checks if second character is a letter in case string has a length of 3
	 *
	 * @throws InvalidStringException
	 */
	private void validateOrder() throws InvalidStringException {
		if ((this.coordinates.length() == 3)
				&& ((this.coordinates.charAt(1) <= '0') || (this.coordinates.charAt(1) >= '9')))
			throw new InvalidStringException("Please don't enter more than one number!");
	}

	private void validateString(final int boardSize) throws InvalidStringException {
		this.validateLength();
		this.validateCharacters();
		this.validateLetterCount();
		this.validateOrder();
		this.validateLetter(boardSize);
		this.validateNumber(boardSize);
	}
}
