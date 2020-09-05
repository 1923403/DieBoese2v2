package player;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import model.Board;

public class Minimax {
	private final int availibleThreads = Runtime.getRuntime().availableProcessors();
	private volatile HashMap<Point, Integer> bestMoves;
	private final char enemyFigure;
	private volatile BoardEvaluation evaluation;
	private final char myFigure;
	private ArrayList<Point> sortedPoints;
	private final int squareSize = 2;
	private final int wantedDepth = 4; // could be increased during the game

	public Minimax(char myFigure, char enemyFigure) {
		this.evaluation = new BoardEvaluation(myFigure, enemyFigure);
		this.myFigure = myFigure;
		this.enemyFigure = enemyFigure;
	}

	/**
	 *
	 * @param board
	 * @return best point calculated by minimax
	 */
	public Point createMove(Board board) {
		var pointList = this.createPoints(board.getBoard());
		var evaluatedPoints = this.evaluation.evaluatePoints(board.getBoard(), pointList);
		this.sortedPoints = this.sortPoints(evaluatedPoints);
		var threadList = this.createThreadList(this.sortedPoints);
		return this.parallelizedSearch(board, threadList);
	}

	private void addSquare(char[][] board, Point center, ArrayList<Point> list) {
		var square = new ArrayList<Point>();
		for (int x = center.x - this.squareSize; x <= (center.x + this.squareSize); x++) {
			for (int y = center.y - this.squareSize; y <= (center.y + this.squareSize); y++) {
				if (((x >= 0) && (x < board.length)) && ((y >= 0) && (y < board.length))) {
					if (board[x][y] == ' ') {
						square.add(new Point(x, y));
					}
				}
			}
		}
		for (var point : square) {
			if (!list.contains(point))
				list.add(point);
		}
	}

	private void bestMove(char[][] board, ArrayList<Point> allMoves) {
		var bestMove = new Point(-1, -1);
		var bestValue = Integer.MIN_VALUE;
		var worstValue = Integer.MAX_VALUE;
		Point previousMoves[] = new Point[wantedDepth];
		var clonedMoves = this.cloneList(this.sortedPoints);
		for (var move : allMoves) {
			previousMoves[0] = new Point(move.x, move.y);
			var value = this.setFigure(board, previousMoves, true, clonedMoves, this.wantedDepth);
//			System.out.println("BestMove: " + move + " " + value);
			worstValue = Math.min(value, bestValue);
			if (value > bestValue) {
				bestValue = value;
				bestMove = move;
			}
		}
		System.out.println(bestMove + ": " + bestValue);
//		System.out.println("Worst value: " + worstValue);
		if (bestMove.x == -1) {
			System.out.println("random move...");
			this.bestMoves.put(this.randomMove(allMoves), Integer.MIN_VALUE);
		} else
			this.bestMoves.put(bestMove, bestValue);
	}

	private ArrayList<Point> cloneList(ArrayList<Point> list) {
		var clonedList = new ArrayList<Point>();
		for (var element : list) {
			clonedList.add(element);
		}
		return clonedList;
	}

	/**
	 * creates list of points which might be good moves for minimax algorithm
	 *
	 * @param board
	 * @return list of points
	 */
	private ArrayList<Point> createPoints(char[][] board) {
		var examinePoints = new ArrayList<Point>();
		for (var x = 0; x < board.length; x++) {
			for (var y = 0; y < board.length; y++) {
				if ((board[x][y] == this.myFigure) || (board[x][y] == this.enemyFigure)) {
					this.addSquare(board, new Point(x, y), examinePoints);
				}
			}
		}
		return examinePoints;
	}

	/**
	 * splits points to all threads starting with the best move
	 *
	 * @param allMoves
	 * @return
	 */
	private ArrayList<ArrayList<Point>> createThreadList(ArrayList<Point> allMoves) {
		var threadList = new ArrayList<ArrayList<Point>>();
		for (var i = 0; i < this.availibleThreads; i++) {
			threadList.add(new ArrayList<>());
		}
		for (var point : allMoves) {
			var threadNumber = allMoves.indexOf(point) % this.availibleThreads;
			ArrayList<Point> currentList = threadList.get(threadNumber);
			currentList.add(point);
		}
		// this.printThreadList(threadList);
		return threadList;
	}

	private int minimax(char[][] board, Point[] previousMoves, boolean isMaximizing,
			ArrayList<Point> possibleMoves, int depth) {
		int bestValue = Integer.MAX_VALUE;
		ArrayList<Point> allMoves = possibleMoves;
		if (this.evaluation.hasWon(board, previousMoves[wantedDepth - (depth + 1)])) {
			if (isMaximizing) {
				return Integer.MIN_VALUE;
			} else
				return Integer.MAX_VALUE;
		}
		if (depth == 0)
			return this.evaluation.evaluateBoard(board, previousMoves, isMaximizing);
		if (isMaximizing)
			bestValue = Integer.MIN_VALUE;
		for (var move : allMoves) {
			if (board[move.x][move.y] == ' ') {
				previousMoves[wantedDepth - depth] = new Point(move.x, move.y);
				var value = this.setFigure(board, previousMoves, isMaximizing, possibleMoves, depth);
//			if (depth == 1)
//				System.out.println(move + " Value: " + value);
				if (isMaximizing)
					bestValue = Math.max(value, bestValue);
				else
					bestValue = Math.min(value, bestValue);
			}
		}

		return bestValue;
	}

	/**
	 * takes the already split lists and starts different threads which are
	 * executing the bestMove method / minimax
	 *
	 * @param board
	 * @param threadLists list which contains the lists with points for each thread
	 * @return returns best overall move generated by all threads
	 */
	private Point parallelizedSearch(Board realBoard, ArrayList<ArrayList<Point>> threadLists) {
		this.bestMoves = new HashMap<>();
		Thread threads[] = new Thread[this.availibleThreads];

		for (var i = 0; i < this.availibleThreads; i++) {
			var allMoves = threadLists.get(i);
			threads[i] = new Thread(() -> {

				this.bestMove(realBoard.copyBoard(), allMoves);
			});
			threads[i].start();
		}

		for (var thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				System.err.println("Thread error");
			}
		}
		System.out.println("ev min: " + this.evaluation.minValue);
		System.out.println("ev max: " + this.evaluation.maxValue);
		return this.sortPoints(this.bestMoves).get(0); // returns best evaluated point
	}

	private void printThreadList(ArrayList<ArrayList<Point>> threadlist) {
		for (var list : threadlist) {
			System.out.println("Thread " + threadlist.indexOf(list));
			for (var point : list) {
				System.out.println(point);
			}
			System.out.println();
		}
	}

	private Point randomMove(ArrayList<Point> bestMoves) {
		var random = (int) (Math.random() * bestMoves.size());
		return bestMoves.get(random);
	}

	/**
	 * places figure on board, executes minimax algorithm and deletes the figure
	 * afterwards
	 *
	 * @param board
	 * @param move         the point where the figure should be placed
	 * @param isMaximizing
	 * @param depth        current depth
	 * @return value generated by the minimax algorithm
	 */
	private int setFigure(char[][] board, Point[] previousMoves, boolean isMaximizing,
			ArrayList<Point> possibleMoves, int depth) {
		if (previousMoves[wantedDepth - depth] == null)
			System.exit(6);
		var x = previousMoves[wantedDepth - depth].x;
		var y = previousMoves[wantedDepth - depth].y;
		var allMoves = this.cloneList(possibleMoves);
		this.addSquare(board, new Point(x, y), allMoves);
		if (isMaximizing)
			board[x][y] = this.myFigure;
		else
			board[x][y] = this.enemyFigure;
		var value = this.minimax(board, previousMoves, !isMaximizing, allMoves, depth - 1);
		board[x][y] = ' ';
		return value;
	}

	/**
	 * sorts all points from evaluated best to worst move
	 *
	 * @param pointList
	 */
	private ArrayList<Point> sortPoints(HashMap<Point, Integer> evaluatedPoints) {
		var sortedList = new ArrayList<Point>();
		var sorted = false;
		int highestValue;
		Point bestPoint = new Point(-1, -1);
		while (!sorted) {
			highestValue = Integer.MIN_VALUE;
			sorted = true;
			for (var point : evaluatedPoints.keySet()) {
				if (evaluatedPoints.get(point) >= highestValue) {
					bestPoint = point;
					highestValue = evaluatedPoints.get(point);
					sorted = false;
				}
			}
			if (!sorted)
				sortedList.add(bestPoint);
			evaluatedPoints.remove(bestPoint);
		}
		System.out.println(sortedList);
		return sortedList;
	}
}
