package model;

import java.awt.Point;

public class Data {
	private Board board;
	private char enemyFigure;
	private Point enemyMove;
	private Turn move;
	private int turnCounter = 0;
	private StringValidation validation;

	public Data(int boardSize) {
		this.board = new Board(boardSize);
		this.move = new Turn();
		this.validation = new StringValidation();
	}

	public Board getBoard() {
		return this.board;
	}

	public int getBoardSize() {
		return this.board.getBoard().length;
	}

	public char getEnemyFigure() {
		return this.enemyFigure;
	}

	public Point getEnemyMove() {
		return this.enemyMove;
	}

	public Turn getTurn() {
		return this.move;
	}

	public int getTurnCounter() {
		return this.turnCounter;
	}

	public StringValidation getValidation() {
		return this.validation;
	}

	public void incTurnCounter() {
		this.turnCounter++;
	}

	public void load(char myFigure, Point myMove) {
		this.setEnemyFigure(myFigure);
		this.enemyMove = myMove;
	}

	public void setEnemyFigure(char enemyFigure) {
		this.enemyFigure = enemyFigure;
	}
}
