package player;

import java.awt.Point;

import model.InvalidMoveException;
import model.Move;

public class AI extends Player {

	private Point enemyMove;

	public AI(final char figure) {
		super(figure);
	}

	public void setEnemyMove(final Point move) {
		this.enemyMove = move;
	}

	@Override
	public void move(final int boardSize, Move move, char enemyFigure, int turnCount) {
		// creates random move
		System.out.println("AI starts move... boardSize: "+ boardSize);
		Point aiMove = new Point();
		aiMove.x = (int) ((Math.random() * (boardSize)));
		aiMove.y = (int) ((Math.random() * (boardSize)));
		System.out.println("AI: "+aiMove.toString());
		try {
			move.setMove(aiMove, this.getFigure(), enemyFigure, turnCount);
		} catch (InvalidMoveException e) {
			move(boardSize, move, enemyFigure, turnCount);
		}

	}
}
