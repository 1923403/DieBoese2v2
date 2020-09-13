package model;

import java.awt.Point;

public class LongestRow {

	public int longestPossibleRow(char[][] board, final char figure, final char blank, final Point coordinates) {
		var a = this.checkHorizontalRow(board, figure, blank, coordinates);
		var b = this.checkVerticalRow(board, figure, blank, coordinates);
		var c = this.checkDiagonalRowUp(board, figure, blank, coordinates);
		var d = this.checkDiagonalRowDown(board, figure, blank, coordinates);
		return this.getMaxValue(a, b, c, d);
	}

	/**
	 *
	 * @param figure      figure which the algorithm is looking for
	 * @param coordinates starting point
	 * @return longest row
	 */
	public int run(char[][] board, final char figure, final Point coordinates) {
		return this.longestPossibleRow(board, figure, figure, coordinates);
	}

	private int checkDiagonalRowDown(final char[][] board, final char figure, final char blank,
			final Point coordinates) {
		return this.figuresInRow(board, figure, blank, coordinates, new Point(1, -1));
	}

	private int checkDiagonalRowUp(final char[][] board, final char figure, final char blank, final Point coordinates) {
		return this.figuresInRow(board, figure, blank, coordinates, new Point(1, 1));
	}

	private int checkHorizontalRow(final char[][] board, final char figure, final char blank, final Point coordinates) {
		return this.figuresInRow(board, figure, blank, coordinates, new Point(1, 0));
	}

	private int checkVerticalRow(final char[][] board, final char figure, final char blank, final Point coordinates) {
		return this.figuresInRow(board, figure, blank, coordinates, new Point(0, 1));
	}

	private int countFiguresInNegativeDirection(final char[][] board, final char figure, final char blank,
			final Point coordinates, final Point direction) {
		var counter = 0;
		var x = coordinates.x - direction.x;
		var y = coordinates.y - direction.y;
		while (this.figureIsOnBoard(board, x, y, figure, blank)) {
			counter++;
			x -= direction.x;
			y -= direction.y;
		}
		return counter;
	}

	private int countFiguresInPositiveDirection(final char[][] board, final char figure, final char blank,
			final Point coordinates, final Point direction) {
		var counter = 0;
		var x = coordinates.x + direction.x;
		var y = coordinates.y + direction.y;
		while (this.figureIsOnBoard(board, x, y, figure, blank)) {
			counter++;
			x += direction.x;
			y += direction.y;
		}
		return counter;
	}

	private boolean figureIsOnBoard(final char[][] board, final int x, final int y, final char figure,
			final char blank) {
		return (x < board.length) && (y < board.length) && (x >= 0) && (y >= 0)
				&& ((board[x][y] == figure) || (board[x][y] == blank));
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
		counter += this.countFiguresInNegativeDirection(board, figure, blank, coordinates, direction);
		counter += this.countFiguresInPositiveDirection(board, figure, blank, coordinates, direction);
		return counter;
	}

	private int getMaxValue(final int a, final int b, final int c, final int d) {
		return Math.max(Math.max(a, b), Math.max(c, d));
	}
}
