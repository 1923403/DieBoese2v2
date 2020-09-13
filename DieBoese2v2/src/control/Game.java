package control;

import io.localization.ConsoleOutput;
import model.CheckWin;
import model.Data;
import model.Menu;
import model.Settings;
import player.AI;
import player.HumanPlayer;
import player.Player;

public class Game {

	private Data data;
	private boolean DEBUG = false;
	private Menu menu;
	private Player player1;
	private boolean player1Next = true;
	private Player player2;
	private Settings settings;

	protected Game() {
		this.settings = new Settings();
		this.menu = new Menu(this.settings);
		this.menu.chooseSettings();
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
		this.data = new Data(this.settings.getBoardSize());
		this.player1 = new HumanPlayer('X', this.data);

		if (this.settings.isPvp())
			this.player2 = new HumanPlayer('O', this.data);
		else {
			this.player2 = new AI('O', this.data);

			if (this.settings.getStart()) {
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
			return !(new CheckWin(this.player2.getMyMove(), this.data.getBoard().getBoard(), this.player2.getFigure())
					.run());
		return !(new CheckWin(this.player1.getMyMove(), this.data.getBoard().getBoard(), this.player1.getFigure())
				.run());
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
