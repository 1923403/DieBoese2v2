package control;

import model.Board;
import model.Menu;
import model.Move;
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
			//wait
		}while(!menu.settingsChoosen());
		board = new Board(menu.getBoardSize());
	}
}
