package model;

import java.awt.Point;

public class Move {

	private char[][] board;

	public Move(Board board) {
		this.board = board.getBoard();
	}

	// checks if space is empty
	public boolean emptySpace(Point coordinates) {
		return this.board[coordinates.x][coordinates.y] == ' ';
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
	public void setMove(Point coordinates, char figure) {
		this.board[coordinates.x][coordinates.y] = figure;
	}
}
