package control;

import io.localization.ConsoleOutput;
import model.Data;
import model.Menu;
import player.AI;
import player.HumanPlayer;
import player.Player;

public class Game {

	private Data data;
	private boolean DEBUG = false;
	private Menu menu = new Menu();
	private Player player1;
	private boolean player1Next = true;
	private Player player2;

	protected Game() {
		do {
			// wait
		} while (!this.menu.settingsChoosen()); // if no changes were made (0 pressed)
		this.getSettings();
		this.runGame();
	}

	public void runGame() {
		while (this.isRunning()) {
			this.data.incTurnCounter();

			if (this.data.getTurnCounter() != 9)
				this.data.getBoard().printBoard(); // for second move

			if (this.player1Next) {
				ConsoleOutput.printWhoIsNext(1);
				this.player1.move();
			} else {
				ConsoleOutput.printWhoIsNext(2);
				this.player2.move();
			}
			this.player1Next = !this.player1Next;
		}

		this.data.getBoard().printBoard();

		if (this.whoWon())
			ConsoleOutput.printWhoWon(1);
		else
			ConsoleOutput.printWhoWon(2);
	}

	/**
	 * loads settings (default / new)
	 */
	private void getSettings() {
		this.data = new Data(this.menu.getBoardSize());
		this.player1 = new HumanPlayer('X', this.data);

		if (this.menu.isPvp())
			this.player2 = new HumanPlayer('O', this.data);
		else {
			this.player2 = new AI('O', this.data);

			if (this.menu.getStart()) {
				this.player1Next = true;
				this.data.setEnemyFigure(this.player2.getFigure());
			} else {
				this.player1Next = false;
				this.data.setEnemyFigure(this.player1.getFigure());
			}
		}
	}

	private boolean isRunning() {
		if (this.player1Next)
			return !this.data.getMove().hasWon(this.player2.getFigure(), this.player2.getMyMove());
		return !this.data.getMove().hasWon(this.player1.getFigure(), this.player1.getMyMove());
	}

	/**
	 * @return true if player1 has won, false if player2 has won
	 */
	private boolean whoWon() {
		if ((this.data.getTurnCounter() % 2) == 0)
			return false;
		return true;
	}
}
