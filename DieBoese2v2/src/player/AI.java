package player;

import java.awt.Point;

import model.Data;

public class AI extends Player {

	private Point enemyMove;

	public AI(final char figure) {
		super(figure);
	}

	@Override
	public void move(final Data data) {
		// creates random move
		if (data.getTurnCounter() < 9)
			this.setMyMove(randomMove(data.getBoardSize()));// first 4 moves
		else {
			// ai logic
			System.out.println("minimax");
			this.setMyMove(randomMove(data.getBoardSize()));// replace with minimax
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
