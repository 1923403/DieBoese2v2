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
				if (board[x][y] != ' ')
					return false;
			}
		}
		return true;
	}

	public char[][] getBoard() {
		return board;
	}

	public void printBoard() {
		for (int y = 0; y <= board.length+1; y++) {
			System.out.println();
			if(y != 0 && y != board.length + 1) System.out.print((char)(97+board.length-(y)));
			for (int x = 0; x < board.length; x++) {
				if(y == 0 || y== board.length +1) { 
					if(x == 0) System.out.print(" ");
					if(x < 10) System.out.print(" ");
					System.out.print((x+1)+" ");
				}
				else System.out.print("["+board[x][y-1] + "]");
			}
			if(y != 0 && y != board.length + 1) System.out.print((char)(97+board.length-(y)));
		}
	}

	
	//for testing only
	public static void main(String[] args) {
		new Board(10).printBoard();
		System.out.println();
		new Board(15).printBoard();

	}
}
