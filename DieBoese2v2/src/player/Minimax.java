package player;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class Minimax {
	private final int availibleThreads = Runtime.getRuntime().availableProcessors();
	private final int analyzingRange = 5;
	private final int wantedDepth = 3; // could be increased during the game
	private BoardEvaluation evaluation;

	public Minimax() {
		evaluation = new BoardEvaluation();
	}

	private Point parallelizedSearch(ArrayList<ArrayList<Point>> threadLists) {
		for (var i = 0; i < availibleThreads; i++) {
			var list = threadLists.get(i);
			new Thread(() -> {
				bestMove(list);
			}).start();
		}
		return null;
	}

	private Point bestMove(ArrayList<Point> allMoves) {
		return null;
	}

	/**
	 * 
	 * @param board
	 * @return best point calculated by minimax
	 */
	public Point createMove(char[][] board) {
		var pointList = createPoints(board);
		var evaluatedPoints = evaluation.evaluatePoints(pointList);
		var sortedPoints = sortPoints(evaluatedPoints);
		var threadList = createThreadList(sortedPoints);
		return parallelizedSearch(threadList);
	}

	/**
	 * sorts all points from guessed best to worst move
	 * 
	 * @param pointList
	 */
	private ArrayList<Point> sortPoints(HashMap<Point, Integer> evaluatedPoints) {
		// sort
		var sortedList = new ArrayList<Point>();

		return sortedList;
	}

	private ArrayList<Point> createPoints(char[][] board) {
		return null;
	}

	/**
	 * splits points to all threads starting with the best move
	 * 
	 * @param allMoves
	 * @return
	 */
	private ArrayList<ArrayList<Point>> createThreadList(ArrayList<Point> allMoves) {
		var threadList = new ArrayList<ArrayList<Point>>();
		for (var i = 0; i < availibleThreads; i++) {
			threadList.add(new ArrayList<>());
		}
		for (var point : allMoves) {
			var threadNumber = allMoves.indexOf(point) % availibleThreads;
			ArrayList<Point> currentList = threadList.get(threadNumber);
			currentList.add(point);
		}
		return threadList;
	}

	public static void main(String[] args) {
		var minimax = new Minimax();
		var list = new ArrayList<Point>();
		for (int i = 0; i < 24; i++) {
			list.add(new Point((int) (Math.random() * 100), (int) (Math.random() * 100)));
		}
		System.out.println(minimax.createThreadList(list));
	}
}
