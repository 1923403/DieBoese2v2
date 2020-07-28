package model;

public class Board {

	// for testing only
	public static void main(String[] args) {

		var testBoard = new Board(15);
		testBoard.board[0][0] = 'X';
		testBoard.printBoard();

	}

	private char[][] board;

	public Board(int boardSize) {
		this.board = new char[boardSize][boardSize];
	}

	/**
	 *
	 * @return current board
	 */
	public char[][] getBoard() {
		return this.board;
	}

	/**
	 * prints board, adds chars for rows, adds numbers for column
	 */
	public void printBoard() {
		for (int y = 0; y <= (this.board.length + 1); y++) {
			System.out.println();
			if ((y != 0) && (y != (this.board.length + 1))) {
				if (y > 6)
					System.out.print(" ");
				System.out.print(((this.board.length - y) + 1)); // numbers on the left
			}
			for (int x = 0; x < this.board.length; x++) {
				if ((y == 0) || (y == (this.board.length + 1))) {
					if (x == 0)
						System.out.print(" ");
					System.out.print("  " + (char) (97 + x));// chars in row
				} else
					System.out.print("[" + this.board[x][y - 1] + "]");
			}
			if ((y != 0) && (y != (this.board.length + 1)))
				System.out.print((this.board.length - y) + 1);// numbers on the right
		}
		System.out.println("\n"); // empty line after every board
	}

	// DEFAULT FOR TESTING
	// makes a copy of the original board
	char[][] copyBoard() {
		final var copy = new char[this.board.length][this.board.length];

		for (var x = 0; x < copy.length; x++)
			for (var y = 0; y < copy.length; y++) {
				final var tmp = this.board[x][y];
				copy[x][y] = tmp;
			}

		return copy;
	}
}
