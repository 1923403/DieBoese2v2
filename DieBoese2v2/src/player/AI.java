package player;

import java.awt.Point;
import java.util.ArrayList;

import model.Data;
import model.InvalidMoveException;

public class AI extends Player {
	ArrayList<Point> points = new ArrayList<>();
	private final int analyzingRange = 4;
	private final int wantedDepth = 3; // could be increased during the game

	public AI(final char figure, Data data) {
		super(figure, data);
	}

	@Override
	public void move() {
		// creates random move
		boolean exceptionThrown;
		do {
			exceptionThrown = false;

			if (this.data.getTurnCounter() < 9)
				this.setMyMove(this.randomMove());// first 4 moves
			else {
				// ai logic
				System.out.println("minimax");

				this.setMyMove(this.bestMove());// replace with minimax
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

	private void addSquare(Point centerPoint) {
		for (int x = centerPoint.x - this.analyzingRange; x < (centerPoint.x + this.analyzingRange); x++) {
			for (int y = centerPoint.y - this.analyzingRange; y < (centerPoint.y + this.analyzingRange); y++) {
				if ((x >= 0) && (x < this.data.getBoardSize()) && (y >= 0) && (y < this.data.getBoardSize())) {
					this.points.add(new Point(x, y));
				}
			}
		}
	}

	private Point bestMove() {
		var board = this.data.getBoard().copyBoard();
		var depth = this.wantedDepth;
		var bestPoint = new Point();
		int bestValue = Integer.MIN_VALUE;
		this.moveAnalyzation();
		for (var point : this.points) {
			var value = this.minimax(point, depth, true);
			if (value > bestValue) {
				bestValue = value;
				bestPoint = point;
			}
		}
		return bestPoint;
	}

	private int minimax(Point point, int depth, boolean isMaximizing) {
		int value;
		if (isMaximizing) {
			value = Integer.MIN_VALUE;
		} else {
			value = Integer.MAX_VALUE;
		}
		return value;
	}

	private void moveAnalyzation() {
		this.points = new ArrayList<>();
		this.addSquare(this.data.getEnemyMove());
		this.addSquare(this.getMyMove());
	}

	private Point randomMove() {
		Point randomPoint = new Point();
		randomPoint.x = (int) ((Math.random() * (this.data.getBoardSize())));
		randomPoint.y = (int) ((Math.random() * (this.data.getBoardSize())));
		return randomPoint;
	}
}
