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

	public static String readInput() {
		var in = new BufferedReader(new InputStreamReader(System.in));
		var input = "";

		try {
			input = in.readLine();
		} catch (IOException e) {
			System.err.println("There seems to be a problem with your input device!");
			System.exit(0);
		}

		return input;
	}

	private Board board;
	private final boolean DEBUG = false;
	private Menu menu = new Menu();
	private Move move;
	private Player player1;
	private boolean player1Next = true;
	private Player player2;

	private int turnCount = 0;

	protected Game() {
		do {
			// wait
		} while (!this.menu.settingsChoosen()); // if no changes were made (0 pressed)
		this.getSettings();
		this.move = new Move(this.board);
		this.runGame();
	}

	/**
	 * loads settings (default / new)
	 */
	private void getSettings() {
		this.board = new Board(this.menu.getBoardSize());
		this.player1 = new AI('X');
		if (this.menu.isPvp()) {
			this.player2 = new Player('O');
		} else {
			this.player2 = new AI('O');
			if (this.menu.getStart()) {// if order changes
				this.player1Next = true;
			} else {
				this.player1Next = false;
			}
		}
	}

	private boolean isRunning() {
		if (this.player1Next)
			return !this.move.hasWon(this.player2.getFigure(), this.player2.getMyMove());
		else
			return !this.move.hasWon(this.player1.getFigure(), this.player1.getMyMove());
	}

	private void runGame() {
		while (this.isRunning()) {
			this.board.printBoard();
			this.turnCount++;
			if (this.player1Next) {
				do {
					System.out.println("Player 1: ");
					this.player1.move(this.menu.getBoardSize(), this.player2.getFigure(), this.turnCount);
					this.move.setMove(this.player1.getMyMove(), this.player1.getFigure(), this.player2.getFigure(),
							this.turnCount);
				} while (!this.move.setMove(this.player1.getMyMove(), this.player1.getFigure(),
						this.player2.getFigure(), this.turnCount));
			} else {
				System.out.println("Player 2: ");
				this.player2.move(this.menu.getBoardSize(), this.player1.getFigure(), this.turnCount);
			}
			this.player1Next = !this.player1Next;

		}
		this.board.printBoard();
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
