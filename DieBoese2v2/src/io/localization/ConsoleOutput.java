package io.localization;

import model.Settings;

public class ConsoleOutput {
	private static String language = "english"; // default
	private static final String[] supportedLanguages = { "english", "deutsch" };

	public static void changeLanguage(String language) {
		var languageSupported = false;
		for (var l : ConsoleOutput.supportedLanguages) {
			if (l == language) {
				languageSupported = true;
				ConsoleOutput.language = language;
			}
		}
		if (!languageSupported) {
			System.err.println(language + " is not supported! Language changed to default (english)");
			ConsoleOutput.language = "english";
		}
	}

	public static void debugInformation(String information) {
		System.err.println(information);
	}

	public static String[] getSupportedLanguages() {
		return ConsoleOutput.supportedLanguages;
	}

	public static void printBoardSize() {
		switch (Settings.getCurrenLanguage()) {
			case DE:
				System.out.println("Wähle eine Spielfeldgröße zwischen 15 und 19.");
				break;
			case EN:
				System.out.println("Choose a boardsize between 15 and 19.");
				break;
		}
	}

	public static void printBoardSizeError() {
		switch (Settings.getCurrenLanguage()) {
			case DE:
				System.err.println("Spielfeldgröße nur zwischen 15 und 19 erlaubt!");
				break;
			case EN:
				System.err.println("Boardsize can only be between 15 and 19!");
				break;
		}
	}

	public static void printCapture(char figure) {
		switch (Settings.getCurrenLanguage()) {
			case DE:
				System.out.println("Zwei Figuren von " + figure + " wurden gerade geschlagen!");
				break;
			case EN:
				System.out.println("Two figures from " + figure + " just got captured!");
				break;
		}
	}

	public static void printCoordinateInput() {
		switch (Settings.getCurrenLanguage()) {
			case DE:
				System.out.println("Bitte Koordinaten eingeben!");
				break;
			case EN:
				System.out.println("Please enter coordinates:");
				break;
		}
	}

	public static void printDifficulty() {
		switch (Settings.getCurrenLanguage()) {
			case DE:
				System.out.println("Wähle die Schwierigkeit der KI zwischen 0 und 2.");
				break;
			case EN:
				System.out.println("Choose AI difficulty between 0 and 2.");
				break;
		}
	}

	public static void printDifficultyError() {
		switch (Settings.getCurrenLanguage()) {
			case DE:
				System.err.println("Schwierigkeit nur zwischen 0 und 2 erlaubt!");
				break;
			case EN:
				System.err.println("Difficulty can only be between 0 and 2!");
				break;
		}
	}

	public static void printExit() {
		switch (Settings.getCurrenLanguage()) {
			case DE:
				System.out.println("Vielen Dank für´s Spielen!\nBis nächstes Mal!");
				break;
			case EN:
				System.out.println("Thank you for playing!\nSee you next time!");
				break;
		}
	}

	public static void printLanguageOptions() {
		switch (Settings.getCurrenLanguage()) {
			case DE:
				System.out.println("Welche Sprache soll verwendet werden? Drücke:");
				for (int i = 0; i < ConsoleOutput.supportedLanguages.length; i++) {
					System.out.println(i + ": für " + ConsoleOutput.supportedLanguages[i]);
				}
				break;
			case EN:
				System.out.println("Which language should be used? Press:");
				for (int i = 0; i < ConsoleOutput.supportedLanguages.length; i++) {
					System.out.println(i + ": for " + ConsoleOutput.supportedLanguages[i]);
				}
				break;
		}
	}

	public static void printLanguageOptionsError() {
		switch (Settings.getCurrenLanguage()) {
			case DE:
				System.err.println(
						"Eingabe nur zwischen 0 und " + (ConsoleOutput.supportedLanguages.length - 1) + " erlaubt!");
				break;
			case EN:
				System.err.println(
						"Input only between 0 and " + (ConsoleOutput.supportedLanguages.length - 1) + " permitted!");
				break;
		}
	}

	public static void printMenuOptions(int boardSize, int difficulty, boolean start, boolean pvp) {
		switch (Settings.getCurrenLanguage()) {
			case DE:
				System.out.println("MENÜ:");
				System.out.println("0: Spielstart");
				System.out.println("1: Spielfeldgöße ändern (" + boardSize + ")");
				System.out.println("2: Schwierigkeit ändern (" + difficulty + ")");
				System.out.print("3: Wer soll beginnen? ");
				if (start)
					System.out.println("(Spieler 1 beginnt)");
				else
					System.out.println("(Spieler 2 beginnt)");
				System.out.print("4: PvP / PvKI? ");
				if (pvp)
					System.out.println("(PvP)");
				else
					System.out.println("(PvKI)");
				System.out.println("5: Wähle die Sprache");
				break;
			case EN:
				System.out.println("MENU:");
				System.out.println("0: start game");
				System.out.println("1: change boardsize (" + boardSize + ")");
				System.out.println("2: change difficulty (" + difficulty + ")");
				System.out.print("3: who should start? ");
				if (start)
					System.out.println("(Player 1 starts)");
				else
					System.out.println("(Player 2 starts)");
				System.out.print("4: PvP / PvAI? ");
				if (pvp)
					System.out.println("(PvP)");
				else
					System.out.println("(PvAI)");
				System.out.println("5: choose language");
				break;
		}
	}

	public static void printNoValidInput() {
		switch (Settings.getCurrenLanguage()) {
			case DE:
				System.err.println("Falsche Eingabe! Bitte erneut versuchen.");
				break;
			case EN:
				System.err.println("Wrong input! Please try again.");
				break;
		}
	}

	public static void printPvp() {
		switch (Settings.getCurrenLanguage()) {
			case DE:
				System.out.println("Drücke 0 für PvP oder 1 für PvKI");
				break;
			case EN:
				System.out.println("Press 0 for PvP or 1 for PvAI");
				break;
		}
	}

	public static void printPvpError() {
		switch (Settings.getCurrenLanguage()) {
			case DE:
				System.err.println("Nur 0 oder 1 erlaubt!");
				break;
			case EN:
				System.err.println("Only 0 or 1 permitted!");
				break;
		}
	}

	public static void printStart() {
		switch (Settings.getCurrenLanguage()) {
			case DE:
				System.out.println("Drücke '0' damit Spieler1 beginnt oder '1' für Spieler2");
				break;
			case EN:
				System.out.println("Press '0' for if Player1 should start or '1' if Player2 should start");
				break;
		}
	}

	public static void printStartError() {
		switch (Settings.getCurrenLanguage()) {
			case DE:
				System.err.println("Nur 0 oder 1 erlaubt!");
				break;
			case EN:
				System.err.println("Only 0 or 1 permitted!");
				break;
		}
	}

	public static void printWhoIsNext(int player) {
		switch (Settings.getCurrenLanguage()) {
			case DE:
				System.out.println("Spieler " + player + " ist am Zug:");
				break;
			case EN:
				System.out.println("Player " + player + " is next:");
				break;
		}
	}

	public static void printWhoWon(int player) {
		switch (Settings.getCurrenLanguage()) {
			case DE:
				System.out.println("Spieler " + player + " hat gewonnen!\nHerzlichen Glückwunsch!");
				break;
			case EN:
				System.out.println("Player " + player + " has won!\nCongratulations!");
				break;
		}
	}
}
