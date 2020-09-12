package model;

public class SecondMove {

	public char[][] run(final Board board) {
		final var tmpBoard = board.copyBoard();
		this.secondMoveBlockTop(tmpBoard);
		this.secondMoveBlockBottom(tmpBoard);
		this.secondMoveBlockLeft(tmpBoard);
		this.secondMoveBlockRight(tmpBoard);
		this.secondMoveBlockSquare(tmpBoard);
		return tmpBoard;
	}

	private boolean hasEvenLength(final char[][] board) {
		return (board.length % 2) == 0;
	}

	private void secondMoveBlockBottom(final char[][] board) {
		this.secondMoveBlockFields(board, board.length / 2, board.length - 1);
		if (this.hasEvenLength(board))
			this.secondMoveBlockFields(board, (board.length / 2) - 1, board.length - 1);
	}

	private void secondMoveBlockFields(final char[][] board, final int x, final int y) {
		board[y][x] = 'B';
	}

	private void secondMoveBlockLeft(final char[][] board) {
		this.secondMoveBlockFields(board, 0, board.length / 2);
		if (this.hasEvenLength(board))
			this.secondMoveBlockFields(board, 0, (board.length / 2) - 1);
	}

	private void secondMoveBlockRight(final char[][] board) {
		this.secondMoveBlockFields(board, board.length - 1, board.length / 2);
		if (this.hasEvenLength(board))
			this.secondMoveBlockFields(board, board.length - 1, (board.length / 2) - 1);
	}

	private void secondMoveBlockSquare(final char[][] board) {
		for (var x = 1; x < (board.length - 1); x++)
			for (var y = 1; y < (board.length - 1); y++)
				this.secondMoveBlockFields(board, x, y);
	}

	private void secondMoveBlockTop(final char[][] board) {
		this.secondMoveBlockFields(board, board.length / 2, 0);
		if (this.hasEvenLength(board))
			this.secondMoveBlockFields(board, (board.length / 2) - 1, 0);
	}
}
