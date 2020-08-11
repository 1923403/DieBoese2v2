package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import localization.Language;
import model.Board;
import model.Menu;
import model.Move;
import player.AI;
import player.HumanPlayer;
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
		this.player1 = new HumanPlayer('X');

		if (this.menu.isPvp()) {
			this.player2 = new HumanPlayer('O');
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
			this.turnCount++;
			if(turnCount != 9)this.board.printBoard(); //for second move

			if (this.player1Next) {
				do {
					Language.printWhoIsNext(1);
					this.player1.move(this.menu.getBoardSize());
				} while (!this.move.setMove(this.player1.getMyMove(), this.player1.getFigure(),
						this.player2.getFigure(), this.turnCount));
			} else {
				do {
					Language.printWhoIsNext(2);
					this.player2.move(this.menu.getBoardSize());
				} while (!this.move.setMove(this.player2.getMyMove(), this.player2.getFigure(),
						this.player1.getFigure(), this.turnCount));
			}

			this.player1Next = !this.player1Next;
		}

		this.board.printBoard();

		if (this.whoWon())
			Language.printWhoWon(1);
		else
			Language.printWhoWon(2);
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
