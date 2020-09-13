package model;

import java.awt.Point;

public class CheckWin {

	private final char[][] board;
	private final Point coordinates;
	private final char figure;

	public CheckWin(final char[][] board, final Point coordinates, final char figure) {
		this.board = board;
		this.coordinates = coordinates;
		this.figure = figure;
	}

	// determines if game is over and a player has won
	public boolean run() {
		if ((this.isValidCoordinate() && this.has5InARow())
				|| (!this.movePossible()))
			return true;
		return false;
	}

	private boolean has5InARow() {
		return (Turn.longestRow(this.board, this.figure, this.coordinates) >= 5);
	}

	private boolean isValidCoordinate() {
		return this.coordinates != null;
	}

	// checks if there is an empty space on the board
	private boolean movePossible() {
		for (final char[] element : this.board)
			for (var y = 0; y < this.board.length; y++)
				if (element[y] == ' ')
					return true;
		return false;
	}
}