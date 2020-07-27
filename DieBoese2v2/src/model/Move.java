package model;

import java.awt.Point;

public class Move {

	private char[][] board;

	public Move(Board board) {
		this.board = board.getBoard();
	}

	public boolean hasWon() {
		// under construction
		//if(board.isFull()) return true;
		return false;
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
			this.secondMove(coordinates, figure);
		} else {
			this.setMove(coordinates, figure);
		}

		this.capture(coordinates);
	}

	// DEFAULT FOR TESTING
	void block(Point coordinates) {
		this.setMove(coordinates, 'B');
	}

	// DEFAULT FOR TESTING
	void capture(Point coordinates) {
		// under construction
	}

	// DEFAULT FOR TESTING
	// makes a copy of the original board
	char[][] copyBoard() {
		var copy = new char[this.board.length][this.board.length];

		for (var x = 0; x < copy.length; x++) {
			for (var y = 0; y < copy.length; y++) {
				var tmp = this.board[x][y];
				copy[x][y] = tmp;
			}
		}

		return copy;
	}

	// DEFAULT FOR TESTING
	// checks if space is empty
	boolean isValidMove(Point coordinates) {
		return this.board[coordinates.x][coordinates.y] == ' ';
	}

	// DEFAULT FOR TESTING
	// second regular move of first player where several fields are blocked
	void secondMove(Point coordinates, char figure) throws InvalidMoveException {
		var tmpArray = this.copyBoard();
		var block = 'B';

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

		for (var x = 1; x < (tmpArray.length - 1); x++) {
			for (var y = 1; y < (tmpArray.length - 1); y++) {
				tmpArray[x][y] = block;
			}
		}

		if (tmpArray[coordinates.x][coordinates.y] == ' ') {
			this.setMove(coordinates, figure);
		} else {
			throw new InvalidMoveException();
		}
	}

	// DEFAULT FOR TESTING
	// access through setMove(Point, char, int), block(Point), and secondMove(Point)
	void setMove(Point coordinates, char figure) {
		this.board[coordinates.x][coordinates.y] = figure;
	}
}
