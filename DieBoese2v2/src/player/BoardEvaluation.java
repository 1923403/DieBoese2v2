package player;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import model.LongestRow;
import model.Turn;

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
			if ((lastMoves[i].x == 0) && (lastMoves[i].y == 0) && (board[lastMoves[i].x][lastMoves[i].y] == ' '))
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
		return Turn.hasWon(board, lastMove, board[lastMove.x][lastMove.y]);
//		var figure = board[lastMove.x][lastMove.y];
//		return (this.longestRow(board, figure, lastMove) > 4);
	}

	private int longestPossibleRow(final char[][] board, final char figure, final char blank,
			final Point coordinates) {
		return new LongestRow(board, coordinates, figure).longestPossibleRow(blank);
	}

	// from class Turn

	/**
	 * returns longest row starting from given coordinates
	 *
	 * @param figure      figure which the algorithm is looking for
	 * @param coordinates starting point
	 * @return longest row
	 */
	private int longestRow(final char[][] board, final char figure, final Point coordinates) {
		return new LongestRow(board, coordinates, figure).run();
	}

	private boolean winPossible(char[][] board, Point move) {
		return ((this.longestPossibleRow(board, this.myFigure, ' ', move) > 4)
				|| (this.longestPossibleRow(board, this.enemyFigure, ' ', move) > 4));
//		return ((this.longestRow(board, this.myFigure, ' ', move) > 4)
//				|| (this.longestRow(board, this.enemyFigure, ' ', move) > 4));
	}
}
