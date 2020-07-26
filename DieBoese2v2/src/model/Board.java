package model;

public class Board {

	private char[][] board;

	public Board(int boardSize) {
		board = new char[boardSize][boardSize];
	}

	public void setMove(int[] coordinates, char figure) {
		var x = coordinates[0];
		var y = coordinates[1];
		this.board[x][y] = figure;
	}

	public boolean isFull() {
		for (var x = 0; x < board.length; x++) {
			for (var y = 0; y < board.length; y++) {
				if (board[x][y] != ' ')
					return false;
			}
		}
		return true;
	}

	public char[][] getBoard() {
		return board;
	}

	//not working yet
	public void printBoard() {
		for (int y = 0; y < board.length; y++) {
			System.out.println();
			for (int x = 0; x < board.length; x++) {
				System.out.print(board[x][y] + " ");
			}
		}
	}

	
	//for testing only
	public static void main(String[] args) {
		new Board(10).printBoard();
	}
}
