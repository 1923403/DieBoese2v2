package player;

import model.Move;

public class AI extends Player {

	private Move move;

	public AI(char figure, Move move) {
		super(figure);
		this.move = move;
	}
}
