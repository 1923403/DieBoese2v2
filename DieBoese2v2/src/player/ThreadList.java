package player;

import java.awt.Point;
import java.util.ArrayList;

public class ThreadList {
	public static ArrayList<ArrayList<Point>> getThreadList(ArrayList<Point> allMoves, int availableThreads) {
		var threadList = new ArrayList<ArrayList<Point>>();
		// adds i lists to threadList, i = availableThreads
		for (var i = 0; i < availableThreads; i++) {
			threadList.add(new ArrayList<>());
		}
		for (var point : allMoves) {
			var threadNumber = allMoves.indexOf(point) % availableThreads;
			ArrayList<Point> currentList = threadList.get(threadNumber);
			currentList.add(point);
		}
		return threadList;
	}
}
