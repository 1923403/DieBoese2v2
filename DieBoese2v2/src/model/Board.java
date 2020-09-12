package model;

public class Board {

	private final char[][] board;

//	for testing only
//	public static void main(String[] args) {
//		var testBoard = new Board(15);
//		testBoard.board[0][0] = 'X';
//		testBoard.printBoard();
//	}

	public static void printBoard(final char[][] board) {
		for (int y = 0; y <= (board.length + 1); y++) {
			System.out.println();
			Board.printNumbersLeft(board, y);
			Board.printFieldsAndLetters(board, y);
			Board.printNumbersRight(board, y);
		}
		System.out.println("\n");
	}

	private static void alignNumbers(final char[][] board, final int y) {
		if ((board.length - y) < 9)
			System.out.print(" ");
	}

	private static boolean isTopOrBottomRow(final char[][] board, final int y) {
		return (y == 0) || (y == (board.length + 1));
	}

	private static void printFields(final char[][] board, final int x, final int y) {
		System.out.print("[" + board[x][y - 1] + "]");
	}

	private static void printFieldsAndLetters(final char[][] board, final int y) {
		for (int x = 0; x < board.length; x++)
			if (Board.isTopOrBottomRow(board, y))
				Board.printLetters(x);
			else
				Board.printFields(board, x, y);
	}

	private static void printLetters(final int x) {
		if (x == 0)
			System.out.print(" ");
		System.out.print("  " + (char) (97 + x));
	}

	private static void printNumbersLeft(final char[][] board, final int y) {
		if (!Board.isTopOrBottomRow(board, y)) {
			Board.alignNumbers(board, y);
			System.out.print(((board.length - y) + 1));
		}
	}

	private static void printNumbersRight(final char[][] board, final int y) {
		if (!Board.isTopOrBottomRow(board, y))
			System.out.print((board.length - y) + 1);
	}

	public Board(final int boardSize) {
		this.board = new char[boardSize][boardSize];
		this.initialize();
	}

	/**
	 * creates a copy of the original board
	 */
	public char[][] copyBoard() {
		final char[][] copy = new char[this.board.length][this.board.length];
		for (int x = 0; x < this.board.length; x++)
			for (int y = 0; y < this.board.length; y++)
				copy[x][y] = this.board[x][y];
		return copy;
	}

	/**
	 * @return current board
	 */
	public char[][] getBoard() {
		return this.board;
	}

	/**
	 * prints board, adds chars for rows, adds numbers for column
	 */
	public void printBoard() {
		Board.printBoard(this.board);
	}

	private void initialize() {
		for (int y = 0; y < this.board.length; y++)
			for (int x = 0; x < this.board.length; x++)
				this.board[x][y] = ' ';
	}
}
