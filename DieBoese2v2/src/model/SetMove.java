package model;

import java.awt.Point;

import exceptions.InvalidMoveException;
import io.localization.ConsoleOutput;
import player.Player;

public class SetMove {

	private final char[][] board;
	private final char enemyFigure;
	private final Player player;
	private final int turnCount;

	public SetMove(final Player player, final char enemyFigure, final char[][] board, final int turnCount) {
		this.player = player;
		this.enemyFigure = enemyFigure;
		this.board = board;
		this.turnCount = turnCount;
	}

	// places given figure at given coordinates on the board if possible
	public void run() throws InvalidMoveException {
		this.checkMove();
		this.performMove();
		this.capture();
		this.printSecondMoveBoard();
	}

	private void block() {
		this.setMove('B');
	}

	private void capture() {
		Turn.capture(this.board, this.player.getMyMove(), this.player.getFigure(), this.enemyFigure);
	}

	private void checkMove() throws InvalidMoveException {
		if (this.turnCount == 9)
			this.isValidMove(this.secondMove(), this.player.getMyMove());
		else
			this.isValidMove(this.board, this.player.getMyMove());
	}

	// checks if space is empty
	private boolean isValidMove(final char[][] board, final Point coordinates) throws InvalidMoveException {
		if (board[coordinates.x][coordinates.y] == ' ')
			return true;
		throw new InvalidMoveException("Field is not empty!");
	}

	private void performMove() {
		if (this.turnCount < 7)
			this.block();
		else
			this.setMove(this.player.getFigure());
	}

	private void printSecondMoveBoard() {
		if (this.turnCount == 8)
			Board.printBoard(this.secondMove());
	}

	private char[][] secondMove() {
		return new SecondMove(this.board).run();
	}

	private void setMove(final char figure) {
		ConsoleOutput
				.debugInformation("places figure on: " + this.player.getMyMove().x + ", " + this.player.getMyMove().y);
		this.board[this.player.getMyMove().x][this.player.getMyMove().y] = figure;
	}
}
