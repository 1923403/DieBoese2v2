package player;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

import model.Board;
import model.Data;
import model.InvalidMoveException;

public class AI extends Player {
	private final int analyzingRange = 5;
	private final int wantedDepth = 3; // could be increased during the game
	private Deque<Point> lastEnemyMoves;
	private Deque<Point> myLastMoves;

	public AI(final char figure, Data data) {
		super(figure, data);
//		lastEnemyMoves = new ArrayDeque<Point>();
//		myLastMoves = new ArrayDeque<Point>();
	}

	@Override
	public void move() {
		// creates random move
		boolean exceptionThrown;
//		addMove();
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

	private void addMove() {
		lastEnemyMoves.addFirst(this.data.getEnemyMove());
		myLastMoves.addFirst(getMyMove());

		while (lastEnemyMoves.size() > 2) {
			lastEnemyMoves.removeLast();
		}

		while (myLastMoves.size() > 2) {
			myLastMoves.removeLast();
		}
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

	private ArrayList<Point> reducePoints(char[][] board, ArrayList<Point> myMoves, ArrayList<Point> enemyMoves) {
		var reducedList = new ArrayList<Point>();
		var evaluatedList = preMoveEvaluation(board);
		for (var point : evaluatedList) {
			if (evaluatedList.indexOf(point) > 4) {
				if (myMoves.contains(point) || enemyMoves.contains(point)) {
					reducedList.add(point);
				}
			} else {
				reducedList.add(point);
			}
		}
		System.out.println("ReducedList: " + reducedList.size());
		return reducedList;
	}

	private Point bestMove() {
		var board = this.data.getBoard().copyBoard();
		var depth = this.wantedDepth;
		var bestPoint = new Point(-1, -1);
		int bestValue = Integer.MIN_VALUE;

		// check if win possible
		var myMoves = this.addSquare(this.getMyMove());
		var enemyMoves = this.addSquare(this.data.getEnemyMove());
		System.out.println("MyMoves: " + myMoves.size());
		System.out.println("EnemyMoves: " + enemyMoves.size());

		//
		var points = reducePoints(board, myMoves, enemyMoves);
		for (var point : points) {
			if (board[point.x][point.y] == ' ') {
				board[point.x][point.y] = this.getFigure();
				var capturedPos = this.data.getTurn().capture(board, point, getFigure(), data.getEnemyFigure());// capture
																												// not
																												// working
																												// yet
				myMoves.addAll(capturedPos);
				var value = this.minimax(board, point, this.data.getEnemyMove(), myMoves, depth - 1, false, bestValue,
						Integer.MAX_VALUE);
				resetCapture(board, capturedPos, this.data.getEnemyFigure());
				// System.out.println("VALUE: " + value + ", POINT: " + point);
				board[point.x][point.y] = ' ';
				if (value > bestValue) {
					bestValue = value;
					bestPoint = point;
				}

			}
		}
		if (bestPoint.x == -1)
			return this.randomMove(); // no good move found...has to be changed
		return bestPoint;
	}

	private void resetCapture(char[][] board, ArrayList<Point> capturedPos, char figure) {
		for (var pos : capturedPos) {
			if (pos != null)
				board[pos.x][pos.y] = figure;
		}
	}

	private void drawPoints(ArrayList<Point> points) {// for debugging
		// for debugging
		char[][] squareBoard = this.data.getBoard().copyBoard();
		for (var point : points) {
			squareBoard[point.x][point.y] = 'S';
		}
		Board.printBoard(squareBoard);
	}

	/**
	 * should evaluate the board for minimax algorithm !!!absolutely not final
	 * yet!!! just for testing
	 *
	 * @param board         boardstate after several recursions
	 * @param myLastMove    last point ai placed a figure
	 * @param enemyLastMove last point enemy placed a figure
	 * @param isMaximizing  if minimax is maximizing or minimizing
	 * @return calculated evaluation of board
	 */
	private ArrayList<Point> preMoveEvaluation(char[][] board) {
		HashSet<Point> winPossible = winPossible(board);
		var map = new HashMap<Point, Integer>();
		// longest row with blank berücksichtigung
		for (var point : winPossible) {
			if (board[point.x][point.y] == ' ') {
				map.put(point, Math.max(this.data.getTurn().longestRow(board, getFigure(), point),
						this.data.getTurn().longestRow(board, data.getEnemyFigure(), point)));

			}
		}
		System.out.println(winPossible);
		System.out.println("Map: " + map.size());
		return sortPoints(map);
	}

	private int evaluateBoard(char[][] board, boolean isMaximizing) {
		HashSet<Point> winPossible = winPossible(board);
		int enemyRow = 0;
		int myRow = 0;
		for (var point : winPossible) {
			if (board[point.x][point.y] == getFigure()) {
				myRow = Math.max(this.data.getTurn().longestRow(board, getFigure(), point), myRow);
			} else if (board[point.x][point.y] == data.getEnemyFigure()) {
				enemyRow = Math.max(this.data.getTurn().longestRow(board, data.getEnemyFigure(), point), enemyRow);
			}
		}

		if (isMaximizing) {
			if (enemyRow < myRow)
				return myRow;
			return -enemyRow;
		}
		if (enemyRow <= myRow)
			return myRow;
		return -enemyRow;

	}

	private ArrayList<Point> sortPoints(HashMap<Point, Integer> map) {
		var sortedPoints = new ArrayList<Point>();
		for (int rowLength = 5; rowLength >= 0; rowLength--) {
			for (var point : map.keySet()) {
				if (map.get(point) == rowLength) {
					sortedPoints.add(point);
					// map.remove(point);
				}
			}
		}
		System.out.println("SortedPoints: " + sortedPoints.size());
		return sortedPoints;
	}

	private HashSet<Point> winPossible(char[][] board) {
		var winPossible = new HashSet<Point>();
		for (var x = 0; x < board.length; x++) {
			for (var y = 0; y < board.length; y++) {
				if (this.data.getTurn().longestRow(board, getFigure(), ' ', new Point(x, y)) > 4)
					winPossible.add(new Point(x, y));
				if (this.data.getTurn().longestRow(board, data.getEnemyFigure(), ' ', new Point(x, y)) > 4)
					winPossible.add(new Point(x, y));
			}
		}
		return winPossible;
	}

	private int minimax(char[][] board, Point myMove, Point enemyMove, ArrayList<Point> previousMoves, int depth,
			boolean isMaximizing, int alpha, int beta) {
		int value;
		int bestValue;
		ArrayList<Point> points;

		if (depth == 0)
			return this.evaluateBoard(board, !isMaximizing);

		if (isMaximizing) {
			if (this.data.getTurn().longestRow(board, this.data.getEnemyFigure(), enemyMove) == 5)
				return Integer.MIN_VALUE;
			value = bestValue = Integer.MIN_VALUE;
			var myMoves = this.addSquare(myMove);
			points = this.addPoints(myMoves, previousMoves);
			for (var point : points) {
				if (board[point.x][point.y] == ' ') {
					board[point.x][point.y] = this.getFigure();
					var capturedPos = this.data.getTurn().capture(board, point, getFigure(), data.getEnemyFigure());
					myMoves.addAll(capturedPos);
					value = this.minimax(board, point, enemyMove, myMoves, depth - 1, false, bestValue, beta);
					resetCapture(board, capturedPos, this.data.getEnemyFigure());
					board[point.x][point.y] = ' ';
					bestValue = Math.max(value, bestValue);
					if (bestValue < beta)
						break;
				}
			}
		} else {
			if (this.data.getTurn().longestRow(board, this.getFigure(), myMove) == 5)
				return Integer.MAX_VALUE;
			value = bestValue = Integer.MAX_VALUE;
			var enemyMoves = this.addSquare(enemyMove);
			points = this.addPoints(previousMoves, enemyMoves);
			for (var point : points) {
				if (board[point.x][point.y] == ' ') {
					board[point.x][point.y] = this.data.getEnemyFigure();
					var capturedPos = this.data.getTurn().capture(board, point, data.getEnemyFigure(), getFigure());
					enemyMoves.addAll(capturedPos);
					value = this.minimax(board, myMove, point, enemyMoves, depth - 1, true, alpha, bestValue);
					resetCapture(board, capturedPos, this.getFigure());
					board[point.x][point.y] = ' ';
					bestValue = Math.min(value, bestValue);
					if (bestValue < alpha)
						break;
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
