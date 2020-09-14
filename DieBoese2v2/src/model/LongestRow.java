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
		if (this.winPossible(new Point(1, -1)))
			return this.figuresInRow(blank, new Point(1, -1));
		return 0;
	}

	private int checkDiagonalRowUp(final char blank) {
		if (this.winPossible(new Point(1, 1)))
			return this.figuresInRow(blank, new Point(1, 1));
		return 0;
	}

	private int checkHorizontalRow(final char blank) {
		if (this.winPossible(new Point(1, 0)))
			return this.figuresInRow(blank, new Point(1, 0));
		return 0;
	}

	private int checkVerticalRow(final char blank) {
		if (this.winPossible(new Point(0, 1)))
			return this.figuresInRow(blank, new Point(0, 1));
		return 0;
	}

	private int countFiguresInNegativeDirection(final char blank, int counter, final Point direction) {
		var x = this.coordinates.x - direction.x;
		var y = this.coordinates.y - direction.y;
		while (this.figureIsOnBoard(x, y, blank)) {
			counter++;
			x -= direction.x;
			y -= direction.y;
			if (counter > 4)
				break;
		}
		return counter;
	}

	private int countFiguresInPositiveDirection(final char blank, int counter, final Point direction) {
		var x = this.coordinates.x + direction.x;
		var y = this.coordinates.y + direction.y;
		while (this.figureIsOnBoard(x, y, blank)) {
			counter++;
			x += direction.x;
			y += direction.y;
			if (counter > 4)
				break;
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
		counter = this.countFiguresInNegativeDirection(blank, counter, direction);
		if (counter < 5)
			counter = this.countFiguresInPositiveDirection(blank, counter, direction);
		return counter;
	}

	private int getMaxValue(final int a, final int b, final int c, final int d) {
		return Math.max(Math.max(a, b), Math.max(c, d));
	}

	private boolean winPossible(final Point direction) {
		return this.figuresInRow(' ', direction) > 4;
	}
}
