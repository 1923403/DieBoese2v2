package player;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class BoardEvaluation {

	private final char myFigure;
	private final char enemyFigure;

	public BoardEvaluation(char myFigure, char enemyFigure) {
		this.myFigure = myFigure;
		this.enemyFigure = enemyFigure;
	}

	public HashMap<Point, Integer> evaluatePoints(char[][] board, ArrayList<Point> allPoints) {
		var evaluatedPoints = new HashMap<Point, Integer>();
		// System.out.println("Vorbewertung:");
		for (var point : allPoints) {
			if (winPossible(board, point)) {
				var evaluation = Math.max(longestRow(board, myFigure, point), longestRow(board, enemyFigure, point));
				evaluatedPoints.put(point, evaluation);
				// System.out.println(point + " " + evaluation);
			}
		}
		// System.out.println("---Ende---");
		return evaluatedPoints;
	}

	private boolean winPossible(char[][] board, Point move) {
		return ((longestRow(board, myFigure, ' ', move) > 4) || (longestRow(board, enemyFigure, ' ', move) > 4));
	}

	public int evaluateBoard(char[][] board, ArrayList<Point> lastMoves, boolean isMaximizing) {
		var myLongestRow = 0;
		var longestEnemyRow = 0;
		for (var i = 0; i < lastMoves.size(); i++) {
			if (i % 2 == 0) {
				myLongestRow = Math.max(myLongestRow, longestRow(board, myFigure, lastMoves.get(i)));
			} else {
				longestEnemyRow = Math.max(longestEnemyRow, longestRow(board, enemyFigure, lastMoves.get(i)));
			}
		}
		if (isMaximizing) {
			if (myLongestRow >= longestEnemyRow)
				return myLongestRow;
			else
				return -longestEnemyRow;
		} else {
			if (myLongestRow > longestEnemyRow)
				return myLongestRow;
			else
				return -longestEnemyRow;
		}
	}

	public boolean hasWon(char[][] board, Point lastMove) {
		var figure = board[lastMove.x][lastMove.y];
		return (longestRow(board, figure, lastMove) > 4);

	}

	// from class Turn

	/**
	 *
	 * @param figure      figure which the algorithm is looking for
	 * @param coordinates starting point
	 * @return longest row
	 */
	private int longestRow(char[][] board, final char figure, final Point coordinates) {
		return longestRow(board, figure, figure, coordinates); // row
	}

	private int longestRow(char[][] board, final char figure, final char blank, final Point coordinates) {
		// only coordinates required, figure not needed

		// horizontal row
		final Point horizontal = new Point(1, 0);
		final var horizontalFigures = this.figuresInRow(board, figure, blank, coordinates, horizontal);

		// vertical row
		final Point vertical = new Point(0, 1);
		final var verticalFigures = this.figuresInRow(board, figure, blank, coordinates, vertical);

		// diagonal row (top right to bottom left)
		final Point diagonal1 = new Point(1, 1);
		final var diagonalFigures1 = this.figuresInRow(board, figure, blank, coordinates, diagonal1);

		// diagonal row (top left to bottom right)
		final Point diagonal2 = new Point(1, -1);
		final var diagonalFigures2 = this.figuresInRow(board, figure, blank, coordinates, diagonal2);

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
}
