package player;

import java.awt.Point;

import control.Game;
import localization.Language;

public class HumanPlayer extends Player {
	private final boolean DEBUG = false;

	public HumanPlayer(final char figure) {
		super(figure);
	}

	@Override
	public void move(final int boardSize) {
		Language.printCoordinateInput();
		try {
			this.move(boardSize, Game.readInput());
		} catch (final InvalidStringException e) {
			System.err.println(e.getMessage());
			Game.pause();
			this.move(boardSize);
		}
	}

	private Point convertCoordinates(final int boardSize, final String coordinates) {
		return new Point(this.getLetter(coordinates), this.getNumber(boardSize, coordinates));
	}


	/**
	 * gets letter in string as int
	 */
	private int getLetter(final String coordinates) {
		var letter = 0;
		for (var i = 0; i < coordinates.length(); i++)
			if ((coordinates.charAt(i) > 57))
				letter = coordinates.toLowerCase().charAt(i) - 97;
		return letter;
	}

	/**
	 * gets number in string as int
	 */
	private int getNumber(final int boardSize, final String coordinates) {
		var number = "";
		for (var i = 0; i < coordinates.length(); i++)
			if ((coordinates.charAt(i) < 58))
				number += coordinates.charAt(i);
		return (boardSize - Integer.valueOf(number));
	}

	/**
	 * checks if character is a letter or a number
	 */
	private boolean hasValidCharacters(final String coordinates) {
		for (var i = 0; i < coordinates.length(); i++)
			if ((coordinates.charAt(i) < 48) || ((coordinates.charAt(i) > 57) && (coordinates.charAt(i) < 97))
					|| (coordinates.charAt(i) > 122))
				return false;
		return true;
	}

	/**
	 * checks if string length is 2 or 3
	 */
	private boolean hasValidLength(final String coordinates) {
		return ((coordinates.length() >= 2) && (coordinates.length() <= 3));
	}

	/**
	 * checks if there is only one letter
	 */
	private boolean hasValidLetterCount(final String coordinates) {
		var letterCount = 0;
		for (var i = 0; i < coordinates.length(); i++)
			if (coordinates.charAt(i) > 96)
				letterCount++;
		return letterCount == 1;
	}

	/**
	 * checks if second character is a letter in case string has a length of 3
	 */
	private boolean isInValidOrder(final String coordinates) {
		if ((coordinates.length() == 3) && (coordinates.charAt(1) > 96))
			return false;
		else
			return true;
	}

	/**
	 * checks if letter is valid
	 */
	private boolean isValidLetter(final int boardSize, final String coordinates) {
		for (var i = 0; i < coordinates.length(); i++)
			if (coordinates.charAt(i) > 96)
				if ((coordinates.charAt(i) - 97) > (boardSize - 1))
					return false;
		return true;
	}

	/**
	 * checks if number is valid
	 */
	private boolean isValidNumber(final int boardSize, final String coordinates) {
		var number = "";
		for (var i = 0; i < coordinates.length(); i++)
			if ((coordinates.charAt(i) < 58))
				number += coordinates.charAt(i);
		return ((Integer.valueOf(number) > 0) && (Integer.valueOf(number) <= (boardSize)));
	}

	private void isValidString(final int boardSize, final String coordinates) throws InvalidStringException {
		if (!this.hasValidLength(coordinates)
				|| !this.hasValidCharacters(coordinates)
				|| !this.isInValidOrder(coordinates)
				|| !this.isValidLetter(boardSize, coordinates)
				|| !this.hasValidLetterCount(coordinates)
				|| !this.isValidNumber(boardSize, coordinates))
			throw new InvalidStringException("You have entered invalid coordinates!");
	}

	/**
	 * checks string and sets move
	 */
	private void move(final int boardSize, final String coordinates) throws InvalidStringException {
		this.isValidString(boardSize, coordinates);
		this.setMyMove(this.convertCoordinates(boardSize, coordinates));
	}
}
