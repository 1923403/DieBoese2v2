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
}
