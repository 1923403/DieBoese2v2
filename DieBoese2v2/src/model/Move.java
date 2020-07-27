package model;

public class Move {

	private Board board;
	private int[] enemyMove;
	private int[] myMove;

	public Move(Board board) {
		this.board = board;
	}

	private void setEnemyMove(final int[] move) {
		this.enemyMove = move;
	}

	private void setMyMove(final int[] move) {
		this.myMove = move;
	}
}
