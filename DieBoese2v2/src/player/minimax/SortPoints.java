package player.minimax;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class SortPoints {

	private Point bestPoint;
	private HashMap<Point, Integer> evaluatedPoints;
	private int highestValue;
	private boolean sorted;
	private ArrayList<Point> sortedList;

	public SortPoints(HashMap<Point, Integer> evaluatedPoints) {
		this.bestPoint = new Point(-1, -1);
		this.evaluatedPoints = evaluatedPoints;
		this.highestValue = Integer.MIN_VALUE;
		this.sorted = false;
		this.sortedList = new ArrayList<>();
	}

	public ArrayList<Point> run() {
		while (!this.sorted) {
			this.resetLoopConditions();
			this.getBestPoint();
			this.writeToSortedList();
		}
		System.out.println(this.sortedList);
		return this.sortedList;
	}

	private void getBestPoint() {
		for (var point : this.evaluatedPoints.keySet()) {
			if (this.evaluatedPoints.get(point) >= this.highestValue) {
				this.bestPoint = point;
				this.highestValue = this.evaluatedPoints.get(point);
				this.sorted = false;
			}
		}
	}

	private void resetLoopConditions() {
		this.highestValue = Integer.MIN_VALUE;
		this.sorted = true;
	}

	private void writeToSortedList() {
		if (!this.sorted)
			this.sortedList.add(this.bestPoint);
		this.evaluatedPoints.remove(this.bestPoint);
	}
}
