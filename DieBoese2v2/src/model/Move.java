package model;

import java.awt.Point;

public class Move {

	private char[][] board;

	public Move(Board board) {
		this.board = board.getBoard();
	}

	// checks if there is an empty space on the board
	public boolean movePossible() {
		for (var x = 0; x < this.board.length; x++) {
			for (var y = 0; y < this.board.length; y++) {
				if (this.board[x][y] != ' ')
					return false;
			}
		}
		return true;
	}

	// places given figure at given coordinates on the board
	public void setMove(Point coordinates, char figure, int turnCount) throws InvalidMoveException {
		if (!this.isValidMove(coordinates))
			throw new InvalidMoveException("Field is not empty!");

		if (turnCount < 7) {
			this.block(coordinates);
		} else if (turnCount == 9) {
			this.secondMove(coordinates);
		} else {
			this.setMove(coordinates, figure);
		}

		this.capture(coordinates);
	}

	private void block(Point coordinates) {
	}

	private void capture(Point coordinates) {

	}

	// checks if space is empty
	private boolean isValidMove(Point coordinates) {
		return this.board[coordinates.x][coordinates.y] == ' ';
	}

	private void secondMove(Point coordinates) {

	}

	// access through setMove(Point, char, int), block(Point), and secondMove(Point)
	private void setMove(Point coordinates, char figure) {
		this.board[coordinates.x][coordinates.y] = figure;
	}
}
