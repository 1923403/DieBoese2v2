package model;

import java.awt.Point;
import java.util.ArrayList;

import exceptions.InvalidMoveException;
import player.Player;

public class Turn {

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
		return new Capture(board, coordinates, figure, enemyFigure).run();
	}

	public static boolean hasWon(final char[][] board, final Point coordinates, final char figure) {
		return new CheckWin(board, coordinates, figure).run();
	}

	// places given figure at given coordinates on the board if possible
	public static void setMove(final Player player, final Data data) throws InvalidMoveException {
		new SetMove(player, data).run();
	}
}
