package player;

import java.awt.Point;

import model.Data;

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

	public abstract void move(final Data data);

	protected void setMyMove(final Point move) {
		this.myMove = move;
	}
}
