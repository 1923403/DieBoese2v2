package player;

import java.awt.Point;

import model.Data;

public class AI extends Player {

	public AI(final char figure, Data data) {
		super(figure, data);
	}

	@Override
	public void move() {
		// creates random move
		if (data.getTurnCounter() < 9)
			this.setMyMove(randomMove(data.getBoardSize()));// first 4 moves
		else {
			// ai logic
			System.out.println("minimax");
			this.setMyMove(randomMove(data.getBoardSize()));// replace with minimax
		}
	}

	private Point randomMove(final int boardSize) {
		Point randomPoint = new Point();
		randomPoint.x = (int) ((Math.random() * (boardSize)));
		randomPoint.y = (int) ((Math.random() * (boardSize)));
		return randomPoint;
	}
}
