package model;

import java.awt.Point;

public class Data {
	private Board board;
	private char enemyFigure;
	private Point enemyMove;
	private int turnCounter = 0;

	public Data(Board board) {
		this.board = board;
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

	public int getTurnCounter() {
		return this.turnCounter;
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
