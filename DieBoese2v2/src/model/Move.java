package model;

import java.awt.Point;

public class Move {

	private Board board;

	public Move(final Board board) {
		this.board = board;
	}
	
	public boolean hasWon() {
		// under construction
		 if(!movePossible()) {
			 System.out.println("no more move possible");
			 return true;
		 }
		 
		return false;
	}

	// checks if there is an empty space on the board
	public boolean movePossible() {
		for (final char[] element : this.board.getBoard())
			for (var y = 0; y < this.board.getBoard().length; y++)
				if (element[y] == ' ')//?
					return true;
		return false;
	}

	// places given figure at given coordinates on the board
	public void setMove(final Point coordinates, final char figure, final char enemyFigure, final int turnCount)
			throws InvalidMoveException {
		this.isValidMove(this.board.getBoard(), coordinates);

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
	// checks if space is empty
	boolean isValidMove(final char[][] board, final Point coordinates) throws InvalidMoveException {
		if (this.board.getBoard()[coordinates.x][coordinates.y] == ' ')
			return true;
		else
			throw new InvalidMoveException("Field is not empty!");
	}

	// DEFAULT FOR TESTING
	// second regular move of first player where several fields are blocked
	void secondMove(final Point coordinates, final char figure) throws InvalidMoveException {
		System.out.println("second move..."); //debug
		final var tmpArray = this.board.copyBoard();
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
		this.board.getBoard()[coordinates.x][coordinates.y] = figure;
	}
}
