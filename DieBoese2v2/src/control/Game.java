package control;

import model.Board;
import model.Menu;
import model.Move;
import player.AI;
import player.Player;

public class Game {

	private Board board;
	private Menu menu = new Menu();
	private Move move;
	private Player player1;
	private boolean player1Next = true;
	private Player player2;
	private int turnCount = 0;

	protected Game() {
		do {
			// wait
		} while (!menu.settingsChoosen());
		getSettings();
		
		while(isRunning()) {
			board.printBoard();
			if(player1Next) {
				player1.move(menu.getBoardSize());
			} else {
				player2.move(menu.getBoardSize());
			}
			player1Next = !player1Next;
			turnCount++;
			
		}
	}
	/**
	 * loads settings (default / new)
	 */
	private void getSettings() {
		board = new Board(menu.getBoardSize());
		if (menu.isPvp()) {
			player1 = new Player('X');
			player2 = new Player('O');
		} else {
			if (menu.getStart()) {//if order changes
				player1 = new Player('X');
				player2 = new AI('O');
			} else {
				player1 = new AI('O');
				player2 = new Player('X');
			}
		}
	}

	private boolean isRunning() {
		// to do
		return true;
	}

	/**
	 * 
	 * @return	true if player1 has won, false if player2 has won
	 * @throws 	Exception if game is still running
	 */
	private boolean whoWon() throws Exception {
		if (this.isRunning())
			throw new Exception();
		if (turnCount % 2 == 0)
			return false;
		else
			return true;
	}
}
