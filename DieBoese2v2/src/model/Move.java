package model;

import java.awt.Point;

public class Move {

	private Board board;

	public Move(final Board board) {
		this.board = board;
	}

	public boolean hasWon(char figure, Point coordinates) {
		// under construction
//		if (coordinates != null)
//			System.out.println("longest row: " + longestRow(figure, coordinates));
		if (coordinates != null && longestRow(figure, coordinates) >= 5) {
			return true;
		}
		if (!movePossible()) {
			System.out.println("no more move possible");
			return true;
		}

		return false;
	}

	/**
	 * 
	 * @param figure      	figure which the algorithm is looking for
	 * @param coordinates 	starting point
	 * @return 				longest row
	 */
	private int longestRow(char figure, Point coordinates) {
		// under construction, not working yet
		// horizontal row
		System.out.println(coordinates);
		Point direction = new Point();
		direction.x = 1;
		direction.y = 0;
		var horizontalFigures = figuresInRow(figure, coordinates, direction);

		direction.x = 0;
		direction.y = 1;
		var verticalFigures = figuresInRow(figure, coordinates, direction);

		direction.x = 1;
		direction.y = 1;
		var diagonalFigures1 = figuresInRow(figure, coordinates, direction);

		direction.x = 1;
		direction.y = -1;
		var diagonalFigures2 = figuresInRow(figure, coordinates, direction);

		return Math.max(Math.max(horizontalFigures, verticalFigures), Math.max(diagonalFigures1, diagonalFigures2));
	}

	/**
	 * counts how many same figures are in the row next to the last placed figure
	 * 
	 * @param figure      placed figure / symbol
	 * @param coordinates position where figure was placed
	 * @param direction
	 * @return
	 */
	private int figuresInRow(char figure, Point coordinates, Point direction) {
		// not working
//		if (board.getBoard()[coordinates.x][coordinates.y] != figure)
//			System.err.println("FEHLER!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		var counter = 1;
		var posX = coordinates.x + direction.x;
		var posY = coordinates.y + direction.y;
		var boardLength = board.getBoard().length;
		while (posX < boardLength && posY < boardLength && posY >= 0 && board.getBoard()[posX][posY] == figure) {
			counter++;
			posX += direction.x;
			posY += direction.y;
		}
		posX = coordinates.x - direction.x;
		posY = coordinates.y - direction.y;
		while (posX >= 0 && posY < boardLength && posY >= 0 && board.getBoard()[posX][posY] == figure) {
			counter++;
			posX -= direction.x;
			posY -= direction.y;
		}
//		System.out.println("direction: " + direction + ", length: " + counter);
		return counter;
	}

	// checks if there is an empty space on the board
	public boolean movePossible() {
		for (final char[] element : this.board.getBoard())
			for (var y = 0; y < this.board.getBoard().length; y++)
				if (element[y] == ' ')
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
		var direction = new Point();
		// right
		direction.x = 1;
		direction.y = 0;
		captureDirections(coordinates, direction, figure, enemyFigure);

		// left
		direction.x = -1;
		direction.y = 0;
		captureDirections(coordinates, direction, figure, enemyFigure);

		// top
		direction.x = 0;
		direction.y = 1;
		captureDirections(coordinates, direction, figure, enemyFigure);

		// bottom
		direction.x = 0;
		direction.y = -1;
		captureDirections(coordinates, direction, figure, enemyFigure);

		// top right
		direction.x = 1;
		direction.y = 1;
		captureDirections(coordinates, direction, figure, enemyFigure);

		// bottom right
		direction.x = 1;
		direction.y = -1;
		captureDirections(coordinates, direction, figure, enemyFigure);

		// bottom left
		direction.x = -1;
		direction.y = -1;
		captureDirections(coordinates, direction, figure, enemyFigure);

		// top left
		direction.x = -1;
		direction.y = 1;
		captureDirections(coordinates, direction, figure, enemyFigure);
	}

	/**
	 * direction from capture, checks if capturing is possible 
	 * in this specific direction
	 * 
	 * @param coordinates
	 * @param direction
	 * @param figure
	 * @param enemyFigure
	 */
	private void captureDirections(Point coordinates, Point direction, char figure, char enemyFigure) {
		var point1 = new Point(); //should be enemys figure for capturing
		var point2 = new Point(); //should be enemys figure for capturing
		var point3 = new Point(); //should be players figure for capturing
		point1.x = coordinates.x + direction.x;
		point1.y = coordinates.y + direction.y;
		point2.x = point1.x + direction.x;
		point2.y = point1.y + direction.y;
		point3.x = point2.x + direction.x;
		point3.y = point2.y + direction.y;
		if ((point3.x < board.getBoard().length) && (point3.y < board.getBoard().length) && (point3.x >= 0)
				&& (point3.y >= 0) && (point3.x >= 0)) {//checks if this point is located on board
			if (board.getBoard()[point3.x][point3.y] == figure && board.getBoard()[point1.x][point1.y] == enemyFigure
					&& board.getBoard()[point2.x][point2.y] == enemyFigure) {//checks if p3 == own figure and p1 == p2 == enemy figure
				System.out.println("captured...");
				setMove(point1, ' '); //deletes enemy figure
				setMove(point2, ' ');
			}
		}
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
		System.out.println("second move..."); // debug
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
		System.out.println("places figure on: " + coordinates.x + ", " + coordinates.y);
		this.board.getBoard()[coordinates.x][coordinates.y] = figure;
	}
}
