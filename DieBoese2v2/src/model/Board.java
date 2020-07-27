package model;

import java.awt.Point;

public class Board {

	private char[][] board;

	public Board(int boardSize) {
		board = new char[boardSize][boardSize];
	}

	public void setMove(Point coordinates, char figure) {
		this.board[coordinates.x][coordinates.y] = figure;
	}

	public boolean isFull() {
		for (var x = 0; x < board.length; x++) {
			for (var y = 0; y < board.length; y++) {
				if (board[x][y] != ' ') // at least one empty field left
					return false;
			}
		}
		return true;
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
		for (int y = 0; y <= board.length + 1; y++) {
			System.out.println();
			if (y != 0 && y != board.length + 1) {
				if (y > 6)
					System.out.print(" ");
				System.out.print((board.length - y + 1)); // numbers on the left
			}
			for (int x = 0; x < board.length; x++) {
				if (y == 0 || y == board.length + 1) {
					if (x == 0)
						System.out.print(" ");
					System.out.print("  " + (char) (97 + x));// chars in row
				} else
					System.out.print("[" + board[x][y - 1] + "]");
			}
			if (y != 0 && y != board.length + 1)
				System.out.print(board.length - y + 1);// numbers on the right
		}
		System.out.println("\n"); // empty line after every board
	}

	// for testing only
	public static void main(String[] args) {

		var testBoard = new Board(15);
		testBoard.board[0][0] = 'X';
		testBoard.printBoard();

	}
}
