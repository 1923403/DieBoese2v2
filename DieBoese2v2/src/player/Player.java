package player;

import java.awt.Point;

public abstract class Player {
	private final char figure;
	private Point myMove;

	public Player(final char figure) {
		this.figure = figure;
	}

	public char getFigure() {
		return this.figure;
	}

	public Point getMyMove() {
		return this.myMove;
	}

	public abstract void move(final int boardSize);

	protected void setMyMove(final Point move) {
		this.myMove = move;
	}
}
