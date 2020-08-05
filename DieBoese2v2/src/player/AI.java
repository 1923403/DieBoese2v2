package player;

import java.awt.Point;

public class AI extends Player {

	private Point enemyMove;

	public AI(final char figure) {
		super(figure);
	}

	@Override
	public void move(final int boardSize, char enemyFigure, int turnCount) {
		// creates random move
		System.out.println("AI starts move... boardSize: " + boardSize);
		Point aiMove = new Point();
		aiMove.x = (int) ((Math.random() * (boardSize)));
		aiMove.y = (int) ((Math.random() * (boardSize)));
		System.out.println("AI: " + aiMove.toString());
		this.setMyMove(aiMove);
	}

	public void setEnemyMove(final Point move) {
		this.enemyMove = move;
	}

//	public static void main(String[] args) {
//		var testAI = new AI('O');
//		testAI.move(15, new Move(new Board(15)), 'X', 0);
//	}
}
