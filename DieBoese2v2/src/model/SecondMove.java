package model;

public class SecondMove {

	private final char[][] board;

	public SecondMove(final Board board) {
		this.board = board.copyBoard();
	}

	public char[][] run() {
		this.secondMoveBlockTop();
		this.secondMoveBlockBottom();
		this.secondMoveBlockLeft();
		this.secondMoveBlockRight();
		this.secondMoveBlockSquare();
		return this.board;
	}

	private boolean hasEvenLength() {
		return (this.board.length % 2) == 0;
	}

	private void secondMoveBlockBottom() {
		this.secondMoveBlockFields(this.board.length / 2, this.board.length - 1);
		if (this.hasEvenLength())
			this.secondMoveBlockFields((this.board.length / 2) - 1, this.board.length - 1);
	}

	private void secondMoveBlockFields(final int x, final int y) {
		this.board[y][x] = 'B';
	}

	private void secondMoveBlockLeft() {
		this.secondMoveBlockFields(0, this.board.length / 2);
		if (this.hasEvenLength())
			this.secondMoveBlockFields(0, (this.board.length / 2) - 1);
	}

	private void secondMoveBlockRight() {
		this.secondMoveBlockFields(this.board.length - 1, this.board.length / 2);
		if (this.hasEvenLength())
			this.secondMoveBlockFields(this.board.length - 1, (this.board.length / 2) - 1);
	}

	private void secondMoveBlockSquare() {
		for (var x = 1; x < (this.board.length - 1); x++)
			for (var y = 1; y < (this.board.length - 1); y++)
				this.secondMoveBlockFields(x, y);
	}

	private void secondMoveBlockTop() {
		this.secondMoveBlockFields(this.board.length / 2, 0);
		if (this.hasEvenLength())
			this.secondMoveBlockFields((this.board.length / 2) - 1, 0);
	}
}
