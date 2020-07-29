package player;

import java.awt.Point;

import control.Game;
import model.InvalidMoveException;
import model.Move;

public class Player {
	private final boolean DEBUG = false;

	private final char figure;
	private Point myMove;

	public Player(final char figure) {
		this.figure = figure;
	}

	public char getFigure() {
		return this.figure;
	}

	public Point getMyMove() {
		return this.myMove;
	}

	public void move(final int boardSize, Move move, char enemyFigure, int turnCount) {
		var point = new Point();

		System.out.println("Please enter coordinates:");
		var coordinates = Game.readInput();

		if (this.isValidString(boardSize, coordinates)) {
			if (this.DEBUG)
				System.out.println("valid String " + coordinates);
			point = this.convertCoordinates(boardSize, coordinates);
		} else {
			if (this.DEBUG)
				System.out.println("no valid String " + coordinates);
			System.out.println("You have entered invalid coordinates!");
			this.move(boardSize, move, enemyFigure, turnCount);
		}
		try {
			move.setMove(point, this.figure, enemyFigure, turnCount);
			this.setMyMove(point);
		} catch (InvalidMoveException e) {
			System.out.println("this is no valid move...try another field");
		}
	}

	private void setMyMove(final Point move) {
		this.myMove = move;
	}

	// privat, for testing default
	Point convertCoordinates(final int boardSize, final String coordinates) {
		final var point = new Point();
		var number = "";

		for (var i = 0; i < coordinates.length(); i++)
			if ((coordinates.charAt(i) < 58))
				number += coordinates.charAt(i);
			else
				point.x = coordinates.toLowerCase().charAt(i) - 97;

		point.y = boardSize - Integer.valueOf(number);

		return point;
	}

	// private, for testing default
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

		// checks if number is valid
		if (Integer.valueOf(number) > (boardSize - 1))
			return false;

		return true;
	}
}
