package model;

import java.awt.Point;

import io.localization.ConsoleOutput;

public class Move {

	private final Board board;

	public Move(final Board board) {
		this.board = board;
	}

	// determines if game is over and a player has won
	public boolean hasWon(final char figure, final Point coordinates) {

		if ((coordinates != null) && (this.longestRow(figure, coordinates) >= 5))
			return true;
		if (!this.movePossible()) {
			System.out.println("no more move possible");
			return true;
		}
		return false;
	}

	// checks if there is an empty space on the board
	public boolean movePossible() {
		for (final char[] element : this.board.getBoard())
			for (var y = 0; y < this.board.getBoard().length; y++)
				if (element[y] == ' ')
					return true;
		return false;
	}

	// places given figure at given coordinates on the board if possible
	public boolean setMove(final Point coordinates, final char figure, final char enemyFigure, final int turnCount) {
		try {
			if (turnCount == 9)
				this.isValidMove(this.secondMove(), coordinates);
			else
				this.isValidMove(this.board.getBoard(), coordinates);
		} catch (final InvalidMoveException e) {
			System.err.println(e.getMessage());
			return false;
		}

		if (turnCount < 7)
			this.block(coordinates);
		else
			this.setMove(coordinates, figure);

		this.capture(coordinates, figure, enemyFigure);
		if (turnCount == 8)
			Board.printBoard(secondMove());
		return true;
	}

	private void block(final Point coordinates) {
		this.setMove(coordinates, 'B');
	}

	private void capture(final Point coordinates, final char figure, final char enemyFigure) {
		final var direction = new Point();
		// right
		direction.setLocation(1, 0);
		this.captureDirections(coordinates, direction, figure, enemyFigure);

		// left
		direction.setLocation(-1, 0);
		this.captureDirections(coordinates, direction, figure, enemyFigure);

		// top
		direction.setLocation(0, 1);
		this.captureDirections(coordinates, direction, figure, enemyFigure);

		// bottom
		direction.setLocation(0, -1);
		this.captureDirections(coordinates, direction, figure, enemyFigure);

		// top right
		direction.setLocation(1, 1);
		this.captureDirections(coordinates, direction, figure, enemyFigure);

		// bottom right
		direction.setLocation(1, -1);
		this.captureDirections(coordinates, direction, figure, enemyFigure);

		// bottom left
		direction.setLocation(-1, -1);
		this.captureDirections(coordinates, direction, figure, enemyFigure);

		// top left
		direction.setLocation(-1, 1);
		this.captureDirections(coordinates, direction, figure, enemyFigure);
	}

	// checks if space is empty
	private boolean isValidMove(final char[][] board, final Point coordinates) throws InvalidMoveException {
		if (board[coordinates.x][coordinates.y] == ' ')
			return true;
		throw new InvalidMoveException("Field is not empty!");
	}

	// second regular move of first player where several fields are blocked
	private char[][] secondMove() {
		ConsoleOutput.debugInformation("secondMove..."); // debug
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
		return tmpArray;
	}

	// access through setMove(Point, char, int), block(Point), and secondMove(Point)
	private void setMove(final Point coordinates, final char figure) {
		ConsoleOutput.debugInformation("places figure on: " + coordinates.x + ", " + coordinates.y);
		this.board.getBoard()[coordinates.x][coordinates.y] = figure;
	}

	/**
	 * direction from capture, checks if capturing is possible in this specific
	 * direction
	 *
	 * @param coordinates
	 * @param direction
	 * @param figure
	 * @param enemyFigure
	 */
	private void captureDirections(final Point coordinates, final Point direction, final char figure,
			final char enemyFigure) {
		final var point1 = new Point(coordinates.x + direction.x, coordinates.y + direction.y); // should be enemys figure for capturing
		final var point2 = new Point(point1.x + direction.x, point1.y + direction.y); // should be enemys figure for capturing
		final var point3 = new Point(point2.x + direction.x, point2.y + direction.y); // should be players figure for capturing

		if ((point3.x < this.board.getBoard().length) && (point3.y < this.board.getBoard().length) && (point3.x >= 0)
				&& (point3.y >= 0) && (point3.x >= 0)) {// checks if this point is located on board
			if ((this.board.getBoard()[point3.x][point3.y] == figure)
					&& (this.board.getBoard()[point1.x][point1.y] == enemyFigure)
					&& (this.board.getBoard()[point2.x][point2.y] == enemyFigure)) {// checks if p3 == own figure and p1
																					// == p2 == enemy figure
				ConsoleOutput.printCapture(enemyFigure);
				this.setMove(point1, ' '); // deletes enemy figure
				this.setMove(point2, ' ');
			}
		}
	}

	/**
	 * counts how many same figures are in the row next to the last placed figure
	 *
	 * @param figure      placed figure / symbol
	 * @param coordinates position where figure was placed
	 * @param direction
	 * @return
	 */
	private int figuresInRow(final char figure, final Point coordinates, final Point direction) {

		var counter = 1;
		var posX = coordinates.x + direction.x;
		var posY = coordinates.y + direction.y;
		final var boardLength = this.board.getBoard().length;
		while ((posX < boardLength) && (posY < boardLength) && (posY >= 0)
				&& (this.board.getBoard()[posX][posY] == figure)) {
			counter++;
			posX += direction.x;
			posY += direction.y;
		}
		posX = coordinates.x - direction.x;
		posY = coordinates.y - direction.y;
		while ((posX >= 0) && (posY < boardLength) && (posY >= 0) && (this.board.getBoard()[posX][posY] == figure)) {
			counter++;
			posX -= direction.x;
			posY -= direction.y;
		}
		return counter;
	}

	/**
	 *
	 * @param figure      figure which the algorithm is looking for
	 * @param coordinates starting point
	 * @return longest row
	 */
	private int longestRow(final char figure, final Point coordinates) {

		// horizontal row
		System.out.println(coordinates);
		final Point direction = new Point();
		
		direction.setLocation(1, 0);
		final var horizontalFigures = this.figuresInRow(figure, coordinates, direction);

		// vertical row
		direction.setLocation(0, 1);
		final var verticalFigures = this.figuresInRow(figure, coordinates, direction);

		// diagonal row (top right to bottom left)
		direction.setLocation(1, 1);
		final var diagonalFigures1 = this.figuresInRow(figure, coordinates, direction);

		// diagonal row (top left to bottom right)
		direction.setLocation(1, -1);
		final var diagonalFigures2 = this.figuresInRow(figure, coordinates, direction);

		return Math.max(Math.max(horizontalFigures, verticalFigures), Math.max(diagonalFigures1, diagonalFigures2)); // max
																														// value
																														// /
																														// longest
																														// row
	}
}
