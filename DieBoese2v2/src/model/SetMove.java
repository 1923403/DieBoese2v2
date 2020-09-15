package model;

import java.awt.Point;

import exceptions.InvalidMoveException;
import io.localization.ConsoleOutput;
import player.Player;

public class SetMove {

	private final char[][] board;
	private final char enemyFigure;
	private final char myFigure;
	private final Point myMove;
	private final int turnCount;

	public SetMove(final Player player, final Data data) {
		this.board = data.getBoard().getBoard();
		this.enemyFigure = data.getEnemyFigure();
		this.myFigure = player.getFigure();
		this.myMove = player.getMyMove();
		this.turnCount = data.getTurnCounter();
	}

	// places given figure at given coordinates on the board if possible
	public void run() throws InvalidMoveException {
		this.checkMove();
		this.performMove();
		this.capture();
		this.printBoardForSecondMove();
	}

	private void block() {
		this.setMove('B');
	}

	private void capture() {
		Turn.capture(this.board, this.myMove, this.myFigure, this.enemyFigure);
	}

	private void checkMove() throws InvalidMoveException {
		if (this.turnCount == 9)
			this.isValidMove(this.secondMove());
		else
			this.isValidMove(this.board);
	}

	private boolean fieldIsEmpty(final char[][] board) {
		return board[this.myMove.x][this.myMove.y] == ' ';
	}

	// checks if space is empty
	private boolean isValidMove(final char[][] board) throws InvalidMoveException {
		if (this.fieldIsEmpty(board))
			return true;
		throw new InvalidMoveException("Field is not empty!");
	}

	private void performMove() {
		if (this.turnCount < 7)
			this.block();
		else
			this.setMove(this.myFigure);
	}

	private void printBoardForSecondMove() {
		if (this.turnCount == 8)
			Board.printBoard(this.secondMove());
	}

	private char[][] secondMove() {
		return new SecondMove(this.board).run();
	}

	private void setMove(final char figure) {
		ConsoleOutput.printCoordinates(board.length, myMove, ": '" + figure + "' got placed");

		this.board[this.myMove.x][this.myMove.y] = figure;
	}
}
