package control;

import io.localization.ConsoleOutput;

public class Main {

	public static void main(String[] args) {
		var game = new Game();
		game.runGame();
		ConsoleOutput.printExit();
	}
}
