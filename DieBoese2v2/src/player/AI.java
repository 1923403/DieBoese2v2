package player;

import java.awt.Point;
import java.util.ArrayList;

import model.Data;
import model.InvalidMoveException;

public class AI extends Player {
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

	private ArrayList<Point> addSquare(Point centerPoint) {
		var points = new ArrayList<Point>();
		for (int x = centerPoint.x - this.analyzingRange; x < (centerPoint.x + this.analyzingRange); x++) {
			for (int y = centerPoint.y - this.analyzingRange; y < (centerPoint.y + this.analyzingRange); y++) {
				if ((x >= 0) && (x < this.data.getBoardSize()) && (y >= 0) && (y < this.data.getBoardSize())) {
					points.add(new Point(x, y));
				}
			}
		}
		return points;
	}

	private Point bestMove() {
		var board = this.data.getBoard().copyBoard();
		var depth = this.wantedDepth;
		var bestPoint = new Point();
		int bestValue = Integer.MIN_VALUE;
		var myMoves = this.addSquare(this.getMyMove());
		var enemyMoves = this.addSquare(this.data.getEnemyMove());
		var points = new ArrayList<Point>();
		points.addAll(myMoves);
		points.addAll(enemyMoves);
		for (var point : points) {
			if (board[point.x][point.y] == ' ') {
				board[point.x][point.y] = this.getFigure();
				var value = this.minimax(board, point, this.data.getEnemyMove(), enemyMoves, depth - 1, true);
				board[point.x][point.y] = ' ';
				if (value > bestValue) {
					bestValue = value;
					bestPoint = point;
				}
			}
		}
		return bestPoint;
	}

	private int minimax(char[][] board, Point myMove, Point enemyMove, ArrayList<Point> previousMoves, int depth,
			boolean isMaximizing) {
		int value;
		int bestValue;
		var bestPoint = new Point();
		var points = new ArrayList<Point>();
		// if() won return max/min value;
		if (depth == 0) {
			// board evaluation
		}
		if (isMaximizing) {
			value = bestValue = Integer.MIN_VALUE;
			var myMoves = this.addSquare(myMove);
			points.addAll(myMoves);
			points.addAll(previousMoves);
			for (var point : points) {
				if (board[point.x][point.y] == ' ') {
					board[point.x][point.y] = this.getFigure();
					value = this.minimax(board, point, enemyMove, myMoves, depth - 1, false);
					board[point.x][point.y] = ' ';
					if (value > bestValue) {
						bestValue = value;
						bestPoint = point;
					}
				}
			}
		} else {
			value = Integer.MAX_VALUE;
		}
		return value;
	}

	private Point randomMove() {
		Point randomPoint = new Point();
		randomPoint.x = (int) ((Math.random() * (this.data.getBoardSize())));
		randomPoint.y = (int) ((Math.random() * (this.data.getBoardSize())));
		return randomPoint;
	}
}
