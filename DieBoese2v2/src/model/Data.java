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
		return board;
	}
	
	public int getBoardSize() {
		return board.getBoard().length;
	}
	
	public void incTurnCounter() {
		turnCounter++;
	}
	
	public int getTurnCounter() {
		return turnCounter;
	}
	
	public void load(char myFigure, Point myMove) {
		setEnemyFigure(myFigure);
		this.enemyMove = myMove;
	}
	
	public char getEnemyFigure() {
		return enemyFigure;
	}
	
	public void setEnemyFigure(char enemyFigure) {
		this.enemyFigure = enemyFigure;
	}
	
	public Point getEnemyMove() {
		return enemyMove;
	}
}
