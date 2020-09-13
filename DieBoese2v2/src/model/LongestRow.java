package model;

import java.awt.Point;

public class LongestRow {

	private final char[][] board;
	private final Point coordinates;
	private final char figure;

	public LongestRow(final char[][] board, final Point coordinates, final char figure) {
		this.board = board;
		this.coordinates = coordinates;
		this.figure = figure;
	}

	public int longestPossibleRow(final char blank) {
		var a = this.checkHorizontalRow(blank);
		var b = this.checkVerticalRow(blank);
		var c = this.checkDiagonalRowUp(blank);
		var d = this.checkDiagonalRowDown(blank);
		return this.getMaxValue(a, b, c, d);
	}

	/**
	 *
	 * @param figure      figure which the algorithm is looking for
	 * @param coordinates starting point
	 * @return longest row
	 */
	public int run() {
		return this.longestPossibleRow(this.figure);
	}

	private int checkDiagonalRowDown(final char blank) {
		return this.figuresInRow(blank, new Point(1, -1));
	}

	private int checkDiagonalRowUp(final char blank) {
		return this.figuresInRow(blank, new Point(1, 1));
	}

	private int checkHorizontalRow(final char blank) {
		return this.figuresInRow(blank, new Point(1, 0));
	}

	private int checkVerticalRow(final char blank) {
		return this.figuresInRow(blank, new Point(0, 1));
	}

	private int countFiguresInNegativeDirection(final char blank, final Point direction) {
		var counter = 0;
		var x = this.coordinates.x - direction.x;
		var y = this.coordinates.y - direction.y;
		while (this.figureIsOnBoard(x, y, blank)) {
			counter++;
			x -= direction.x;
			y -= direction.y;
		}
		return counter;
	}

	private int countFiguresInPositiveDirection(final char blank, final Point direction) {
		var counter = 0;
		var x = this.coordinates.x + direction.x;
		var y = this.coordinates.y + direction.y;
		while (this.figureIsOnBoard(x, y, blank)) {
			counter++;
			x += direction.x;
			y += direction.y;
		}
		return counter;
	}

	private boolean figureIsOnBoard(final int x, final int y, final char blank) {
		return (x < this.board.length) && (y < this.board.length) && (x >= 0) && (y >= 0)
				&& ((this.board[x][y] == this.figure) || (this.board[x][y] == blank));
	}

	/**
	 * counts how many same figures are in the row next to the last placed figure
	 *
	 * @param figure      placed figure / symbol
	 * @param coordinates position where figure was placed
	 * @param direction
	 * @return
	 */
	private int figuresInRow(final char blank, final Point direction) {
		var counter = 1;
		counter += this.countFiguresInNegativeDirection(blank, direction);
		counter += this.countFiguresInPositiveDirection(blank, direction);
		return counter;
	}

	private int getMaxValue(final int a, final int b, final int c, final int d) {
		return Math.max(Math.max(a, b), Math.max(c, d));
	}
}
