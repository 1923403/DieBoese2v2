package model;

import java.awt.Point;
import java.util.ArrayList;

import io.localization.ConsoleOutput;
import player.Player;

public class Turn {

	private final Board board;

	public Turn(final Board board) {
		this.board = board;
	}

	public ArrayList<Point> capture(char[][] board, final Point coordinates, final char figure,
			final char enemyFigure) {
		var capturedPos = new ArrayList<Point>();
		final var direction = new Point();
		// right
		direction.setLocation(1, 0);
		capturedPos.addAll(this.captureDirections(board, coordinates, direction, figure, enemyFigure));

		// left
		direction.setLocation(-1, 0);
		capturedPos.addAll(this.captureDirections(board, coordinates, direction, figure, enemyFigure));

		// top
		direction.setLocation(0, 1);
		capturedPos.addAll(this.captureDirections(board, coordinates, direction, figure, enemyFigure));

		// bottom
		direction.setLocation(0, -1);
		capturedPos.addAll(this.captureDirections(board, coordinates, direction, figure, enemyFigure));

		// top right
		direction.setLocation(1, 1);
		capturedPos.addAll(this.captureDirections(board, coordinates, direction, figure, enemyFigure));

		// bottom right
		direction.setLocation(1, -1);
		capturedPos.addAll(this.captureDirections(board, coordinates, direction, figure, enemyFigure));

		// bottom left
		direction.setLocation(-1, -1);
		capturedPos.addAll(this.captureDirections(board, coordinates, direction, figure, enemyFigure));

		// top left
		direction.setLocation(-1, 1);
		capturedPos.addAll(this.captureDirections(board, coordinates, direction, figure, enemyFigure));

		return capturedPos;
	}

	// determines if game is over and a player has won
	public boolean hasWon(final char figure, final Point coordinates) {

		if ((coordinates != null) && (this.longestRow(this.board.getBoard(), figure, coordinates) >= 5))
			return true;
		if (!this.movePossible()) {
			System.out.println("no more move possible");
			return true;
		}
		return false;
	}

	/**
	 *
	 * @param figure      figure which the algorithm is looking for
	 * @param coordinates starting point
	 * @return longest row
	 */
	public int longestRow(char[][] board, final char figure, final Point coordinates) {
		return longestRow(board, figure, figure, coordinates); // row
	}

	public int longestRow(char[][] board, final char figure, final char blank, final Point coordinates) {
		// only coordinates required, figure not needed

		// horizontal row
//		System.out.println(coordinates);
		final Point direction = new Point();

		direction.setLocation(1, 0);
		final var horizontalFigures = this.figuresInRow(board, figure, blank, coordinates, direction);

		// vertical row
		direction.setLocation(0, 1);
		final var verticalFigures = this.figuresInRow(board, figure, blank, coordinates, direction);

		// diagonal row (top right to bottom left)
		direction.setLocation(1, 1);
		final var diagonalFigures1 = this.figuresInRow(board, figure, blank, coordinates, direction);

		// diagonal row (top left to bottom right)
		direction.setLocation(1, -1);
		final var diagonalFigures2 = this.figuresInRow(board, figure, blank, coordinates, direction);

		return Math.max(Math.max(horizontalFigures, verticalFigures), Math.max(diagonalFigures1, diagonalFigures2)); // max
																														// value
																														// /
																														// longest

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
	public void setMove(Data data, Player player) throws InvalidMoveException {
		int turnCount = data.getTurnCounter();

		if (turnCount == 9)
			this.isValidMove(this.secondMove(), player.getMyMove());
		else
			this.isValidMove(this.board.getBoard(), player.getMyMove());

		if (turnCount < 7)
			this.block(player.getMyMove());
		else
			this.setMove(player.getMyMove(), player.getFigure());

		this.capture(this.board.getBoard(), player.getMyMove(), player.getFigure(), data.getEnemyFigure());
		if (turnCount == 8)
			Board.printBoard(this.secondMove());
	}

	private void block(final Point coordinates) {
		this.setMove(coordinates, 'B');
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
	private ArrayList<Point> captureDirections(char[][] board, final Point coordinates, final Point direction,
			final char figure, final char enemyFigure) {
		final var point1 = new Point(coordinates.x + direction.x, coordinates.y + direction.y); // should be enemys
																								// figure for capturing
		final var point2 = new Point(point1.x + direction.x, point1.y + direction.y); // should be enemys figure for
																						// capturing
		final var point3 = new Point(point2.x + direction.x, point2.y + direction.y); // should be players figure for
																						// capturing

		if ((point3.x < board.length) && (point3.y < board.length) && (point3.x >= 0) && (point3.y >= 0)
				&& (point3.x >= 0)) {// checks if this point is located on board
			if ((board[point3.x][point3.y] == figure) && (board[point1.x][point1.y] == enemyFigure)
					&& (board[point2.x][point2.y] == enemyFigure)) {// checks if p3 == own figure and p1
																	// == p2 == enemy figure
				// ConsoleOutput.printCapture(enemyFigure);
				board[point1.x][point1.y] = ' '; // deletes enemy figure
				board[point2.x][point2.y] = ' ';
				var capturedPos = new ArrayList<Point>();
				capturedPos.add(point1);
				capturedPos.add(point2);
				return capturedPos;
			}
		}
		return new ArrayList<Point>();
	}

	/**
	 * counts how many same figures are in the row next to the last placed figure
	 *
	 * @param figure      placed figure / symbol
	 * @param coordinates position where figure was placed
	 * @param direction
	 * @return
	 */
	private int figuresInRow(char[][] board, final char figure, final char blank, final Point coordinates,
			final Point direction) {

		var counter = 1;
		var posX = coordinates.x + direction.x;
		var posY = coordinates.y + direction.y;
		final var boardLength = board.length;
		while ((posX < boardLength) && (posY < boardLength) && (posY >= 0)
				&& ((board[posX][posY] == figure) || (board[posX][posY] == blank))) {
			counter++;
			posX += direction.x;
			posY += direction.y;
		}
		posX = coordinates.x - direction.x;
		posY = coordinates.y - direction.y;
		while ((posX >= 0) && (posY < boardLength) && (posY >= 0)
				&& ((board[posX][posY] == figure) || (board[posX][posY] == blank))) {
			counter++;
			posX -= direction.x;
			posY -= direction.y;
		}
		return counter;
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
}
