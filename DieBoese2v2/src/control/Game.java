package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.Board;
import model.Menu;
import model.Move;
import player.AI;
import player.Player;

public class Game {

	private Board board;

	private final boolean DEBUG = false;
	private int debugRun = 5;
	private Menu menu = new Menu();
	private Move move;
	private Player player1;
	private boolean player1Next = true;
	private Player player2;
	private int turnCount = 0;

	public static String readInput() throws IOException {
		var in = new BufferedReader(new InputStreamReader(System.in));
		var input = in.readLine();
		return input;
	}

	protected Game() {
		do {
			// wait
		} while (!this.menu.settingsChoosen()); // if no changes are made (0 pressed)
		this.getSettings();
		this.move = new Move(this.board);
		this.runGame();
	}

	/**
	 * loads settings (default / new)
	 */
	private void getSettings() {
		this.board = new Board(this.menu.getBoardSize());
		if (this.menu.isPvp()) {
			this.player1 = new Player('X');
			this.player2 = new Player('O');
		} else {
			if (this.menu.getStart()) {// if order changes
				this.player1 = new Player('X');
				this.player2 = new AI('O');
			} else {
				this.player1 = new AI('O');
				this.player2 = new Player('X');
			}
		}
	}

	private boolean isRunning() {
		if (this.DEBUG)
			return 0 < this.debugRun--;
		return this.move.hasWon();
	}

	private void runGame() {
		while (this.isRunning()) {
			this.board.printBoard();
			if (this.player1Next) {
				this.player1.move(this.menu.getBoardSize());
			} else {
				this.player2.move(this.menu.getBoardSize());
			}
			this.player1Next = !this.player1Next;
			this.turnCount++;

		}
		if (this.whoWon())
			System.out.println("Spieler1 hat gewonnen....."); // toDO
		else
			System.out.println("Spieler2 hat gewonnen....");// to do
	}

	/**
	 *
	 * @return true if player1 has won, false if player2 has won
	 */
	private boolean whoWon() {
		if ((this.turnCount % 2) == 0)
			return false;
		else
			return true;
	}
}
