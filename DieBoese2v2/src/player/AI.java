package player;

import java.awt.Point;

public class AI extends Player {

	private Point enemyMove;

	public AI(final char figure) {
		super(figure);
	}

	@SuppressWarnings("unused")
	@Override
	public void move(final int boardSize) {
		// creates random move
		if(true) this.setMyMove(randomMove(boardSize));//first 4 moves
		else {
			//ai logic
		}
	}

	public void setEnemyMove(final Point move) {
		this.enemyMove = move;
	}

	private Point randomMove(final int boardSize) {
		Point randomPoint = new Point();
		randomPoint.x = (int) ((Math.random() * (boardSize)));
		randomPoint.y = (int) ((Math.random() * (boardSize)));
		return randomPoint;
	}
}
