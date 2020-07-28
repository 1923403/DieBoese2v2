package player;

import java.awt.Point;

public class AI extends Player {

	private Point enemyMove;

	public AI(final char figure) {
		super(figure);
	}

	public void setEnemyMove(final Point move) {
		this.enemyMove = move;
	}
	
	@Override
	public void move(final int boardSize) {
		//creates random move
//		do {
//			this.enemyMove.x = (int)(Math.random()*(boardSize-1));
//			this.enemyMove.y = (int)(Math.random()*(boardSize-1));
//		}while();
	}
}
