package model;

import java.awt.Point;

public class Move {

	private final char[][] board;

	public Move(final Board board) {
		this.board = board.getBoard();
	}

	public boolean hasWon() {
		// under construction
		// if(!movePossible()) return true;
		return false;
	}

	// checks if there is an empty space on the board
	public boolean movePossible() {
		for (final char[] element : this.board)
			for (var y = 0; y < this.board.length; y++)
				if (element[y] != ' ')
					return false;
		return true;
	}

	// places given figure at given coordinates on the board
	public void setMove(final Point coordinates, final char figure, final char enemyFigure, final int turnCount)
			throws InvalidMoveException {
		this.isValidMove(this.board, coordinates);

		if (turnCount < 7)
			this.block(coordinates);
		else if (turnCount == 9)
			this.secondMove(coordinates, figure);
		else
			this.setMove(coordinates, figure);

		this.capture(coordinates, figure, enemyFigure);
	}

	// DEFAULT FOR TESTING
	void block(final Point coordinates) {
		this.setMove(coordinates, 'B');
	}

	// DEFAULT FOR TESTING
	void capture(final Point coordinates, final char figure, final char enemyFigure) {
		// under construction
	}

	// DEFAULT FOR TESTING
	// makes a copy of the original board
	char[][] copyBoard() {
		final var copy = new char[this.board.length][this.board.length];

		for (var x = 0; x < copy.length; x++)
			for (var y = 0; y < copy.length; y++) {
				final var tmp = this.board[x][y];
				copy[x][y] = tmp;
			}

		return copy;
	}

	// DEFAULT FOR TESTING
	// checks if space is empty
	boolean isValidMove(final char[][] board, final Point coordinates) throws InvalidMoveException {
		if (this.board[coordinates.x][coordinates.y] == ' ')
			return true;
		else
			throw new InvalidMoveException("Field is not empty!");
	}

	// DEFAULT FOR TESTING
	// second regular move of first player where several fields are blocked
	void secondMove(final Point coordinates, final char figure) throws InvalidMoveException {
		final var tmpArray = this.copyBoard();
		final var block = 'B';

		// up
		tmpArray[0][tmpArray.length / 2] = block;
		// down
		tmpArray[tmpArray.length - 1][tmpArray.length / 2] = block;
		// left
		tmpArray[tmpArray.length / 2][0] = block;
		// right
		tmpArray[tmpArray.length / 2][tmpArray.length - 1] = block;

		if ((tmpArray.length % 2) == 0) {
			// up
			tmpArray[0][(tmpArray.length / 2) + 1] = block;
			// down
			tmpArray[tmpArray.length - 1][(tmpArray.length / 2) + 1] = block;
			// left
			tmpArray[(tmpArray.length / 2) + 1][0] = block;
			// right
			tmpArray[(tmpArray.length / 2) + 1][tmpArray.length - 1] = block;
		}

		for (var x = 1; x < (tmpArray.length - 1); x++)
			for (var y = 1; y < (tmpArray.length - 1); y++)
				tmpArray[x][y] = block;

		if (this.isValidMove(tmpArray, coordinates))
			this.setMove(coordinates, figure);
	}

	// DEFAULT FOR TESTING
	// access through setMove(Point, char, int), block(Point), and secondMove(Point)
	void setMove(final Point coordinates, final char figure) {
		this.board[coordinates.x][coordinates.y] = figure;
	}
}
