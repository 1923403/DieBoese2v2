package player.minimax;

import java.awt.Point;
import java.util.ArrayList;

public class ThreadList {

	private static final int availableThreads = Runtime.getRuntime().availableProcessors();
	private static ArrayList<ArrayList<Point>> threadList;

	public static ArrayList<ArrayList<Point>> getThreadList(final ArrayList<Point> allMoves) {
		ThreadList.createThreadList();
		ThreadList.addThreads(allMoves);
		return ThreadList.threadList;
	}

	private static void addThreads(final ArrayList<Point> allMoves) {
		for (final var point : allMoves) {
			final var threadNumber = allMoves.indexOf(point) % ThreadList.availableThreads;
			ThreadList.threadList.get(threadNumber).add(point);
		}
	}

	private static void createThreadList() {
		ThreadList.threadList = new ArrayList<>();
		for (var i = 0; i < ThreadList.availableThreads; i++)
			ThreadList.threadList.add(new ArrayList<>());
	}
}
