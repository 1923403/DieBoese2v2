package model;

import java.awt.Point;

public class Move {

	private Board board;

	public Move(Board board) {
		this.board = board;
	}

	// checks if there is an empty space on the board
	public boolean movePossible() {
		var array = this.board.getBoard();
		for (var x = 0; x < array.length; x++) {
			for (var y = 0; y < array.length; y++) {
				if (array[x][y] != ' ')
					return false;
			}
		}
		return true;
	}

	// places given figure at given coordinates on the board
	public void setMove(Point coordinates, char figure) {
		var array = this.board.getBoard();
		array[coordinates.x][coordinates.y] = figure;
	}
}
