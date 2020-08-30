package player;

import java.awt.Point;
import java.util.ArrayList;

public class Minimax {
	private final int availibleThreads = Runtime.getRuntime().availableProcessors();
	private final int analyzingRange = 5;
	private final int wantedDepth = 3; // could be increased during the game

	private Point parallelizedSearch(ArrayList<ArrayList<Point>> threadLists) {
		for(var i = 0; i < availibleThreads; i++) {
			new Thread(new Runnable(){

				@Override
				public void run() {
					bestMove(threadLists.get(1));
					
				}
				
			}).start();
		}
		return null;
	}
	
	private Point bestMove(ArrayList<Point> allMoves) {
		return null;
	}

	public Point createMove(char[][] board) {
		var pointList = createPoints(board);
		sortPoints(pointList);
		var threadList = createThreadList(pointList);
		return parallelizedSearch(threadList);
	}
	
	/**
	 * sorts all points from guessed best to worst move
	 * 
	 * @param pointList
	 */
	private void sortPoints(ArrayList<Point> pointList) {
		//sort
	}
	
	private ArrayList<Point> createPoints(char[][] board){
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
