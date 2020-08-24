package player;

import java.awt.Point;
import java.util.ArrayList;

import model.Board;
import model.Data;
import model.InvalidMoveException;

public class AI extends Player {
	private final int analyzingRange = 4;
	private final int wantedDepth = 4; // could be increased during the game

	public AI(final char figure, Data data) {
		super(figure, data);
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

				this.setMyMove(this.bestMove());
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

	// for removing duplicates
	private ArrayList<Point> addPoints(ArrayList<Point> myPoints, ArrayList<Point> enemyPoints) {
		var allPoints = new ArrayList<Point>();
		allPoints.addAll(myPoints);
		for (var point : enemyPoints) {
			if (!allPoints.contains(point))
				allPoints.add(point);
		}
		return allPoints;
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
		var bestPoint = new Point(-1, -1);
		int bestValue = Integer.MIN_VALUE;
		var myMoves = this.addSquare(this.getMyMove());
		var enemyMoves = this.addSquare(this.data.getEnemyMove());
		var points = this.addPoints(myMoves, enemyMoves);
		for (var point : points) {
			if (board[point.x][point.y] == ' ') {
				board[point.x][point.y] = this.getFigure();
				var value = this.minimax(board, point, this.data.getEnemyMove(), myMoves, depth - 1, false);
				// System.out.println("VALUE: " + value + ", POINT: " + point);
				board[point.x][point.y] = ' ';
				if (value > bestValue) {
					bestValue = value;
					bestPoint = point;
				}
			}
		}
		return bestPoint;
	}

	private void drawPoints(ArrayList<Point> points) {// for debugging
		// for debugging
		char[][] squareBoard = this.data.getBoard().copyBoard();
		for (var point : points) {
			squareBoard[point.x][point.y] = 'S';
		}
		Board.printBoard(squareBoard);
	}

	private int minimax(char[][] board, Point myMove, Point enemyMove, ArrayList<Point> previousMoves, int depth,
			boolean isMaximizing) {
		int value;
		int bestValue;
		ArrayList<Point> points;

		if (isMaximizing) {
			if (this.data.getTurn().longestRow(board, this.data.getEnemyFigure(), enemyMove) == 5)
				return Integer.MIN_VALUE;
			if (depth == 0) {
				return (this.data.getTurn().longestRow(board, this.getFigure(), myMove));
			}
			value = bestValue = Integer.MIN_VALUE;
			var myMoves = this.addSquare(myMove);
			points = this.addPoints(myMoves, previousMoves);
			for (var point : points) {
				if (board[point.x][point.y] == ' ') {
					board[point.x][point.y] = this.getFigure();
					value = this.minimax(board, point, enemyMove, myMoves, depth - 1, false);
					board[point.x][point.y] = ' ';
					bestValue = Math.max(value, bestValue);
				}
			}
		} else {
			if (this.data.getTurn().longestRow(board, this.getFigure(), myMove) == 5)
				return Integer.MAX_VALUE;
			if (depth == 0) {
				return this.data.getTurn().longestRow(board, this.getFigure(), myMove);
			}
			value = bestValue = Integer.MAX_VALUE;
			var enemyMoves = this.addSquare(enemyMove);
			points = this.addPoints(previousMoves, enemyMoves);
			for (var point : points) {
				if (board[point.x][point.y] == ' ') {
					board[point.x][point.y] = this.data.getEnemyFigure();
					value = this.minimax(board, myMove, point, enemyMoves, depth - 1, true);
					board[point.x][point.y] = ' ';
					bestValue = Math.min(value, bestValue);
				}
			}
		}
		return bestValue;
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
				secondMovePoint = new Point(this.data.getBoardSize() - 1,
						(int) (Math.random() * this.data.getBoardSize()));
				break;
			case 2:
				secondMovePoint = new Point((int) (Math.random() * this.data.getBoardSize()), 0);
				break;
			case 3:
				secondMovePoint = new Point((int) (Math.random() * this.data.getBoardSize()),
						this.data.getBoardSize() - 1);
				break;
		}
		return secondMovePoint;
	}
}
