package player;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class BoardEvaluation {
	public int maxValue = Integer.MIN_VALUE;
	public int minValue = Integer.MAX_VALUE;
	private final char enemyFigure;
	private final char myFigure;

	public BoardEvaluation(char myFigure, char enemyFigure) {
		this.myFigure = myFigure;
		this.enemyFigure = enemyFigure;
	}

	public int evaluateBoard(char[][] board, Point[] lastMoves, boolean isMaximizing) {
		var myLongestRow = 0;
		var longestEnemyRow = 0;
		for (var i = 0; i < lastMoves.length; i++) {
			if (lastMoves[i].x == 0 && lastMoves[i].y == 0 && board[lastMoves[i].x][lastMoves[i].y] == ' ')
				System.err.println("last moves fehler!!!");
			if (board[lastMoves[i].x][lastMoves[i].y] != ' ') { // if figure got captured during minimax
				if ((i % 2) == 0)
					myLongestRow = Math.max(myLongestRow, this.longestRow(board, this.myFigure, lastMoves[i]));
				else
					longestEnemyRow = Math.max(longestEnemyRow,
							this.longestRow(board, this.enemyFigure, lastMoves[i]));
			}
		}
		if (isMaximizing) {
			if (myLongestRow >= longestEnemyRow) {
				this.maxValue = Math.max(myLongestRow, this.maxValue);
				return myLongestRow;
			} else {
				this.minValue = Math.min(-longestEnemyRow, this.minValue);
				return -longestEnemyRow;
			}
		} else {
			if (myLongestRow > longestEnemyRow) {
				this.maxValue = Math.max(myLongestRow, this.maxValue);
				return myLongestRow;
			} else {
				this.minValue = Math.min(-longestEnemyRow, this.minValue);
				return -longestEnemyRow;
			}
		}
	}

	// evaluatePoints and evaluateBoard could use the same evaluation for code
	// reduction / simplification
	/**
	 * creates a hashmap containing all points and a simple evaluation of it
	 * 
	 * @param board
	 * @param allPoints all points that should be evaluated
	 * @return evaluated hashmap
	 */
	public HashMap<Point, Integer> evaluatePoints(char[][] board, ArrayList<Point> allPoints) {
		var evaluatedPoints = new HashMap<Point, Integer>();
		for (var point : allPoints) {
			if (this.winPossible(board, point)) {
				var evaluation = Math.max(this.longestRow(board, this.myFigure, point),
						this.longestRow(board, this.enemyFigure, point));
				evaluatedPoints.put(point, evaluation);
			}
		}
		return evaluatedPoints;
	}

	public boolean hasWon(char[][] board, Point lastMove) {
		var figure = board[lastMove.x][lastMove.y];
		return (this.longestRow(board, figure, lastMove) > 4);

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
			if (counter > 4)
				break;
		}
		posX = coordinates.x - direction.x;
		posY = coordinates.y - direction.y;
		while ((posX >= 0) && (posY < boardLength) && (posY >= 0)
				&& ((board[posX][posY] == figure) || (board[posX][posY] == blank))) {
			counter++;
			posX -= direction.x;
			posY -= direction.y;
			if (counter > 4)
				break;
		}
		return counter;
	}

	// from class Turn

	private int longestRow(char[][] board, final char figure, final char blank, final Point coordinates) {
		// only coordinates required, figure not needed

		// horizontal row
		final Point horizontal = new Point(1, 0);// with enum?
		var horizontalFigures = 0;
		if (this.figuresInRow(board, figure, ' ', coordinates, horizontal) > 4)
			horizontalFigures = this.figuresInRow(board, figure, blank, coordinates, horizontal);

		// vertical row
		final Point vertical = new Point(0, 1);
		var verticalFigures = 0;
		if (this.figuresInRow(board, figure, ' ', coordinates, vertical) > 4)
			verticalFigures = this.figuresInRow(board, figure, blank, coordinates, vertical);

		// diagonal row (top right to bottom left)
		final Point diagonal1 = new Point(1, 1);
		var diagonalFigures1 = 0;
		if (this.figuresInRow(board, figure, ' ', coordinates, diagonal1) > 4)
			diagonalFigures1 = this.figuresInRow(board, figure, blank, coordinates, diagonal1);

		// diagonal row (top left to bottom right)
		final Point diagonal2 = new Point(1, -1);
		var diagonalFigures2 = 0;
		if (this.figuresInRow(board, figure, ' ', coordinates, diagonal2) > 4)
			diagonalFigures2 = this.figuresInRow(board, figure, blank, coordinates, diagonal2);

		return Math.max(Math.max(horizontalFigures, verticalFigures), Math.max(diagonalFigures1, diagonalFigures2));
	}

	/**
	 *
	 * @param figure      figure which the algorithm is looking for
	 * @param coordinates starting point
	 * @return longest row
	 */
	private int longestRow(char[][] board, final char figure, final Point coordinates) {
		return this.longestRow(board, figure, figure, coordinates); // row
	}

	private boolean winPossible(char[][] board, Point move) {
		return ((this.longestRow(board, this.myFigure, ' ', move) > 4)
				|| (this.longestRow(board, this.enemyFigure, ' ', move) > 4));
	}
}
