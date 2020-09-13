package model;

import java.awt.Point;
import java.util.ArrayList;

import exceptions.InvalidMoveException;
import player.Player;

public class Turn {

	private final Board board;

	/**
	 * captures enemy figures if possible
	 *
	 * @param board
	 * @param coordinates last placed figure
	 * @param figure      myFigure
	 * @param enemyFigure enemyFigure
	 * @return Point array of captured figures
	 */
	public static ArrayList<Point> capture(final char[][] board, final Point coordinates, final char figure,
			final char enemyFigure) {
		return new Capture(coordinates, board, figure, enemyFigure).run();
	}

	/**
	 *
	 * @param figure      figure which the algorithm is looking for
	 * @param coordinates starting point
	 * @return longest row
	 */
	public static int longestRow(final char[][] board, final char figure, final Point coordinates) {
		return new LongestRow().run(board, figure, coordinates);
	}

	public Turn(final Board board) {
		this.board = board;
	}

	public boolean hasWon(final Point coordinates, final char[][] board, final char figure) {
		return new CheckWin(coordinates, board, figure).run();
	}

	public int longestPossibleRow(final char[][] board, final char figure, final char blank, final Point coordinates) {
		return new LongestRow().longestPossibleRow(board, figure, blank, coordinates);
	}

	// places given figure at given coordinates on the board if possible
	public void setMove(final Player player, final Data data) throws InvalidMoveException {
		new SetMove(player, data).run();
	}
}
