package model;

public class SecondMove {

	private final char[][] board;

	public SecondMove(final Board board) {
		this.board = board.copyBoard();
	}

	public char[][] run() {
		this.blockTopRow();
		this.blockBottomRow();
		this.blockLeftRow();
		this.blockRightRow();
		this.blockSquare();
		return this.board;
	}

	private void blockBottomRow() {
		this.blockField(this.board.length / 2, this.board.length - 1);
		if (this.hasEvenLength())
			this.blockField((this.board.length / 2) - 1, this.board.length - 1);
	}

	private void blockField(final int x, final int y) {
		this.board[x][y] = 'B';
	}

	private void blockLeftRow() {
		this.blockField(0, this.board.length / 2);
		if (this.hasEvenLength())
			this.blockField(0, (this.board.length / 2) - 1);
	}

	private void blockRightRow() {
		this.blockField(this.board.length - 1, this.board.length / 2);
		if (this.hasEvenLength())
			this.blockField(this.board.length - 1, (this.board.length / 2) - 1);
	}

	private void blockSquare() {
		for (var x = 1; x < (this.board.length - 1); x++)
			for (var y = 1; y < (this.board.length - 1); y++)
				this.blockField(x, y);
	}

	private void blockTopRow() {
		this.blockField(this.board.length / 2, 0);
		if (this.hasEvenLength())
			this.blockField((this.board.length / 2) - 1, 0);
	}

	private boolean hasEvenLength() {
		return (this.board.length % 2) == 0;
	}
}
