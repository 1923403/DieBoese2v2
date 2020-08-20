package control;

import io.localization.ConsoleOutput;
import model.Board;
import model.Data;
import model.Menu;
import model.Move;
import player.AI;
import player.HumanPlayer;
import player.Player;

public class Game {

	private boolean DEBUG = false;
	private Menu menu = new Menu();
	private Move move;
	private Player player1;
	private boolean player1Next = true;
	private Player player2;
	private Data data;

	protected Game() {
		do {
			// wait
		} while (!this.menu.settingsChoosen()); // if no changes were made (0 pressed)
		this.getSettings();
		this.move = new Move(this.data.getBoard());
	}

	/**
	 * loads settings (default / new)
	 */
	private void getSettings() {
		var board = new Board(this.menu.getBoardSize());
		this.data = new Data(board);
		this.player1 = new HumanPlayer('X', data);

		if (this.menu.isPvp())
			this.player2 = new HumanPlayer('O', data);
		else {
			this.player2 = new AI('O', data);

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
			return !this.move.hasWon(this.player2.getFigure(), this.player2.getMyMove());
		return !this.move.hasWon(this.player1.getFigure(), this.player1.getMyMove());
	}

	public void runGame() {
		while (this.isRunning()) {
			boolean moveIsValid;
			this.data.incTurnCounter();
			if (this.data.getTurnCounter() != 9)
				this.data.getBoard().printBoard(); // for second move

			if (this.player1Next) {
				do {
					ConsoleOutput.printWhoIsNext(1);
					this.player1.move();
					moveIsValid = this.move.setMove(this.player1.getMyMove(), this.player1.getFigure(), this.data);
				} while (!moveIsValid);
				this.data.load(player1.getFigure(), player1.getMyMove());
			} else {
				do {
					ConsoleOutput.printWhoIsNext(2);
					this.player2.move();
					moveIsValid = this.move.setMove(this.player2.getMyMove(), this.player2.getFigure(), this.data);
				} while (!moveIsValid);
				this.data.load(player2.getFigure(), player2.getMyMove());
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
	 * @return true if player1 has won, false if player2 has won
	 */
	private boolean whoWon() {
		if ((this.data.getTurnCounter() % 2) == 0)
			return false;
		return true;
	}
}
