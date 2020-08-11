package player;

import java.awt.Point;

import control.Game;

public class HumanPlayer extends Player {
	private final boolean DEBUG = false;

	public HumanPlayer(final char figure) {
		super(figure);
	}

	@Override
	public void move(final int boardSize) {
		System.out.println("Please enter coordinates:");
		this.move(boardSize, Game.readInput());
	}

	// private, default for testing
	Point convertCoordinates(final int boardSize, final String coordinates) {
		final var point = new Point();
		var number = "";

		for (var i = 0; i < coordinates.length(); i++)
			if ((coordinates.charAt(i) < 58))
				number += coordinates.charAt(i);
			else
				point.x = coordinates.toLowerCase().charAt(i) - 97;

		point.y = boardSize - Integer.valueOf(number);
		if (this.DEBUG)
			System.out.println(coordinates + " = " + point);
		return point;
	}

	// private, default for testing
	boolean isValidString(final int boardSize, final String coordinates) {
		var letterCount = 0;
		var number = "";

		if ((coordinates.length() < 2) || (coordinates.length() > 3))
			return false;

		// checks if the second character is a letter in case the string has a length of
		// 3
		if ((coordinates.length() == 3) && (coordinates.charAt(1) > 96))
			return false;

		for (var i = 0; i < coordinates.length(); i++) {
			// checks if character is a letter or a number
			if ((coordinates.charAt(i) < 48) || ((coordinates.charAt(i) > 57) && (coordinates.charAt(i) < 97))
					|| (coordinates.charAt(i) > 122))
				return false;

			// checks if letter is valid, and counts letters
			if (coordinates.charAt(i) > 96)
				if ((coordinates.charAt(i) - 97) > (boardSize - 1))
					return false;
				else
					letterCount++;

			if (letterCount > 1)
				return false;

			if ((coordinates.charAt(i) < 58))
				number += coordinates.charAt(i);
		}

		if (letterCount == 0)
			return false;

		if (Integer.valueOf(number) == 0)
			return false;

		// checks if number is valid
		if (Integer.valueOf(number) > (boardSize))
			return false;

		return true;
	}

	// private, default for testing
	void move(final int boardSize, String coordinates) {
		var point = new Point();

		if (this.isValidString(boardSize, coordinates)) {
			if (this.DEBUG)
				System.out.println("valid String " + coordinates);
			point = this.convertCoordinates(boardSize, coordinates);
			this.setMyMove(point);
		} else {
			if (this.DEBUG)
				System.out.println("no valid String " + coordinates);
			System.out.println("You have entered invalid coordinates!");
			this.move(boardSize);
		}
	}
}