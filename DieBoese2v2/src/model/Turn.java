package model;

import java.awt.Point;
import java.util.ArrayList;

import exceptions.InvalidMoveException;
import io.localization.ConsoleOutput;
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

	public Turn(final Board board) {
		this.board = board;
	}

	// determines if game is over and a player has won
	public boolean hasWon(final char figure, final Point coordinates) {
		if ((coordinates != null) && (this.has5InARow(figure, coordinates)))
			return true;
		if (!this.movePossible()) {
			System.out.println("no move possible");
			return true;
		}
		return false;
	}

	public int longestPossibleRow(final char[][] board, final char figure, final char blank, final Point coordinates) {
		return new LongestRow().longestPossibleRow(board, figure, blank, coordinates);
	}

	/**
	 *
	 * @param figure      figure which the algorithm is looking for
	 * @param coordinates starting point
	 * @return longest row
	 */
	public int longestRow(final char[][] board, final char figure, final Point coordinates) {
		return new LongestRow().run(board, figure, coordinates);
	}

	// checks if there is an empty space on the board
	public boolean movePossible() {
		return this.hasEmptyField(this.board.getBoard());
	}

	// places given figure at given coordinates on the board if possible
	public void setMove(final Data data, final Player player) throws InvalidMoveException {
		final int turnCount = data.getTurnCounter();
		if (turnCount == 9)
			this.isValidMove(this.secondMove(), player.getMyMove());
		else
			this.isValidMove(this.board.getBoard(), player.getMyMove());
		if (turnCount < 7)
			this.block(player.getMyMove());
		else
			this.setMove(player.getMyMove(), player.getFigure());
		Turn.capture(this.board.getBoard(), player.getMyMove(), player.getFigure(), data.getEnemyFigure());
		if (turnCount == 8)
			Board.printBoard(this.secondMove());
	}

	private void block(final Point coordinates) {
		this.setMove(coordinates, 'B');
	}

	private boolean has5InARow(final char figure, final Point coordinates) {
		return (this.longestRow(this.board.getBoard(), figure, coordinates) >= 5);
	}

	private boolean hasEmptyField(final char[][] board) {
		for (final char[] element : this.board.getBoard())
			for (var y = 0; y < this.board.getBoard().length; y++)
				if (element[y] == ' ')
					return true;
		return false;
	}

	// checks if space is empty
	private boolean isValidMove(final char[][] board, final Point coordinates) throws InvalidMoveException {
		if (board[coordinates.x][coordinates.y] == ' ')
			return true;
		throw new InvalidMoveException("Field is not empty!");
	}

	private char[][] secondMove() {
		return new SecondMove(this.board.getBoard()).run();
	}

	// access through setMove(Point, char, int), block(Point), and secondMove(Point)
	private void setMove(final Point coordinates, final char figure) {
		ConsoleOutput.debugInformation("places figure on: " + coordinates.x + ", " + coordinates.y);
		this.board.getBoard()[coordinates.x][coordinates.y] = figure;
	}
}
