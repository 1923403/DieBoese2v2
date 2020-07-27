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
	private boolean player1Next;
	private Player player2;
	private int turnCount;

	protected Game() {
		do {
			// wait
		} while (!menu.settingsChoosen());
		board = new Board(menu.getBoardSize());
		if (menu.isPvp()) {
			player1 = new Player('X');
			player2 = new Player('O');
		} else {
			if (menu.getStart()) {
				player1 = new Player('X');
				player2 = new AI('O');
			} else {
				player1 = new Player('O');
				player2 = new AI('X');
			}
		}

	}
}
