package model;

import java.awt.Point;
import java.util.ArrayList;

public class Capture {

	/**
	 * resets the captured figures (used in minimax algorithm)
	 *
	 * @param board
	 * @param capturedFigures
	 * @param figure          figure symbol
	 * @return returns reseted board
	 */
	public static char[][] resetCapture(char[][] board, ArrayList<Point> capturedFigures, char figure) {
		for (var pos : capturedFigures) {
			board[pos.x][pos.y] = figure;
		}
		return board;
	}

	private final char[][] board;
	private final ArrayList<Point> capturedPositions;
	private final Point coordinates;
	private final char enemyFigure;
	private final char figure;

	public Capture(final Point coordinates, final char[][] board, final char figure, final char enemyFigure) {
		this.capturedPositions = new ArrayList<>();
		this.coordinates = coordinates;
		this.board = board;
		this.figure = figure;
		this.enemyFigure = enemyFigure;
	}

	/**
	 * captures enemy figures if possible
	 *
	 * @return Point array of captured figures
	 */
	public ArrayList<Point> run() {
		this.checkBottom();
		this.checkBottomLeft();
		this.checkBottomRight();
		this.checkLeft();
		this.checkRight();
		this.checkTop();
		this.checkTopLeft();
		this.checkTopRight();
		return this.capturedPositions;
	}

	/**
	 * direction from capture, checks if capturing is possible in this specific
	 * direction
	 *
	 * @param direction
	 */
	private ArrayList<Point> captureDirections(final Point direction) {
		final var enemyFigure1 = new Point(this.coordinates.x + direction.x, this.coordinates.y + direction.y);
		final var enemyFigure2 = new Point(enemyFigure1.x + direction.x, enemyFigure1.y + direction.y);
		final var player = new Point(enemyFigure2.x + direction.x, enemyFigure2.y + direction.y);
		if (this.isOnBoard(player)) {
			if (this.isCorrectConstellation(enemyFigure1, enemyFigure2, player)) {
				// ConsoleOutput.printCapture(enemyFigure);
				this.deleteFigure(enemyFigure1);
				this.deleteFigure(enemyFigure2);
				return this.createCaptureList(enemyFigure1, enemyFigure2);
			}
		}
		// HIER ELSE?
		return new ArrayList<>();
	}

	private void checkBottom() {
		this.capturedPositions.addAll(this.captureDirections(new Point(0, -1)));
	}

	private void checkBottomLeft() {
		this.capturedPositions.addAll(this.captureDirections(new Point(-1, -1)));
	}

	private void checkBottomRight() {
		this.capturedPositions.addAll(this.captureDirections(new Point(1, -1)));
	}

	private void checkLeft() {
		this.capturedPositions.addAll(this.captureDirections(new Point(-1, 0)));
	}

	private void checkRight() {
		this.capturedPositions.addAll(this.captureDirections(new Point(1, 0)));
	}

	private void checkTop() {
		this.capturedPositions.addAll(this.captureDirections(new Point(0, 1)));
	}

	private void checkTopLeft() {
		this.capturedPositions.addAll(this.captureDirections(new Point(-1, 1)));
	}

	private void checkTopRight() {
		this.capturedPositions.addAll(this.captureDirections(new Point(1, 1)));
	}

	private ArrayList<Point> createCaptureList(final Point point1, final Point point2) {
		var capturedPositions = new ArrayList<Point>();
		capturedPositions.add(point1);
		capturedPositions.add(point2);
		return capturedPositions;
	}

	private void deleteFigure(final Point point) {
		this.board[point.x][point.y] = ' ';
	}

	private boolean isCorrectConstellation(final Point point1, final Point point2, final Point point3) {
		return (this.board[point1.x][point1.y] == this.enemyFigure)
				&& (this.board[point2.x][point2.y] == this.enemyFigure)
				&& (this.board[point3.x][point3.y] == this.figure);
	}

	private boolean isOnBoard(final Point point) {
		return (point.x < this.board.length) && (point.y < this.board.length) && (point.x >= 0) && (point.y >= 0);
	}
}
