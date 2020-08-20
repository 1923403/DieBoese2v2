package control;

import io.localization.ConsoleOutput;
import model.Board;
import model.Menu;
import model.Move;
import player.AI;
import player.HumanPlayer;
import player.Player;

public class Game {

	private Board board;
	private boolean DEBUG = false;
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

		if (this.menu.isPvp())
			this.player2 = new HumanPlayer('O');
		else {
			this.player2 = new AI('O');

			if (this.menu.getStart())
				this.player1Next = true;
			else
				this.player1Next = false;
		}
	}

	private boolean isRunning() {
		if (this.player1Next)
			return !this.move.hasWon(this.player2.getFigure(), this.player2.getMyMove());
		return !this.move.hasWon(this.player1.getFigure(), this.player1.getMyMove());
	}

	private void runGame() {
		while (this.isRunning()) {
			boolean moveIsValid;
			this.turnCount++;
			if (this.turnCount != 9)
				this.board.printBoard(); // for second move

			if (this.player1Next)
				do {
					ConsoleOutput.printWhoIsNext(1);
					this.player1.move(this.menu.getBoardSize());
					moveIsValid = this.move.setMove(this.player1.getMyMove(), this.player1.getFigure(),
							this.player2.getFigure(), this.turnCount);
				} while (!moveIsValid);
			else
				do {
					ConsoleOutput.printWhoIsNext(2);
					this.player2.move(this.menu.getBoardSize());
					moveIsValid = this.move.setMove(this.player2.getMyMove(), this.player2.getFigure(),
							this.player1.getFigure(), this.turnCount);
				} while (!moveIsValid);

			this.player1Next = !this.player1Next;
		}

		this.board.printBoard();

		if (this.whoWon())
			ConsoleOutput.printWhoWon(1);
		else
			ConsoleOutput.printWhoWon(2);
	}

	/**
	 * @return true if player1 has won, false if player2 has won
	 */
	private boolean whoWon() {
		if ((this.turnCount % 2) == 0)
			return false;
		return true;
	}
}
