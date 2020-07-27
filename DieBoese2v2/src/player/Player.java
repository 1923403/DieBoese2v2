package player;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Player {

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

	public void move(final int boardSize) {
		var coordinates = "";
		var point = new Point();

		System.out.println("Please enter coordinates:");

		try (var in = new BufferedReader(new InputStreamReader(System.in))) {
			coordinates = in.readLine();
		} catch (final IOException e) {
			System.err.println("There seems to be a problem with your input device!");
		}

		if (this.isValidString(boardSize, coordinates))
			point = this.convertCoordinates(boardSize, coordinates);
		else {
			System.out.println("You have entered invalid coordinates!");
			this.move(boardSize);
		}

		this.setMyMove(point);
	}

	//privat, for testing default
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

	private boolean isValidString(final int boardSize, final String coordinates) {
		var letterCount = 0;
		var number = 0;

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

	private void setMyMove(final Point move) {
		this.myMove = move;
	}
}
