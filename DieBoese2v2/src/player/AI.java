package player;

import java.awt.Point;

import model.Data;
import model.InvalidMoveException;

public class AI extends Player {
	private Minimax minimax;

	public AI(final char figure, Data data) {
		super(figure, data);
		minimax = new Minimax(this.getFigure(), data.getEnemyFigure());
	}

	@Override
	public void move() {
		// creates random move
		boolean exceptionThrown;
		do {
			exceptionThrown = false;

			if ((this.data.getTurnCounter() < 9))
				this.setMyMove(this.randomMove());// first 4 moves
			else if (this.data.getTurnCounter() == 9) {
				this.setMyMove(this.secondMove()); // secondMove
			} else {
				// ai logic
				this.setMyMove(minimax.createMove(this.data.getBoard().getBoard()));
			}
			try {
				this.data.getTurn().setMove(this.data, this);
			} catch (InvalidMoveException e) {
				System.out.println(e.getMessage());
				exceptionThrown = true;
			}
		} while (exceptionThrown);
		this.data.load(this.getFigure(), this.getMyMove());
	}

	private Point randomMove() {
		Point randomPoint = new Point();
		randomPoint.x = (int) ((Math.random() * (this.data.getBoardSize())));
		randomPoint.y = (int) ((Math.random() * (this.data.getBoardSize())));
		return randomPoint;
	}

	private Point secondMove() {
		Point secondMovePoint = null;
		int side = (int) (Math.random() * 4);
		switch (side) {
		case 0:
			secondMovePoint = new Point(0, (int) (Math.random() * this.data.getBoardSize()));
			break;
		case 1:
			secondMovePoint = new Point(this.data.getBoardSize() - 1, (int) (Math.random() * this.data.getBoardSize()));
			break;
		case 2:
			secondMovePoint = new Point((int) (Math.random() * this.data.getBoardSize()), 0);
			break;
		case 3:
			secondMovePoint = new Point((int) (Math.random() * this.data.getBoardSize()), this.data.getBoardSize() - 1);
			break;
		}
		return secondMovePoint;
	}
}
