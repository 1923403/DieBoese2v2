package player;

import java.awt.Point;

import model.Data;
import model.InvalidMoveException;

public class AI extends Player {

	public AI(final char figure, Data data) {
		super(figure, data);
	}

	@Override
	public void move() {
		// creates random move
		if (this.data.getTurnCounter() < 9)
			this.setMyMove(this.randomMove());// first 4 moves
		else {
			// ai logic
			System.out.println("minimax");
			this.setMyMove(this.randomMove());// replace with minimax
		}
		try {
			this.data.getMove().setMove(this.data, this);
		} catch (InvalidMoveException e) {
			System.out.println(e.getMessage());
			this.move();
		}
		this.data.load(this.getFigure(), this.getMyMove());
	}

	private Point randomMove() {
		Point randomPoint = new Point();
		randomPoint.x = (int) ((Math.random() * (this.data.getBoardSize())));
		randomPoint.y = (int) ((Math.random() * (this.data.getBoardSize())));
		return randomPoint;
	}
}
