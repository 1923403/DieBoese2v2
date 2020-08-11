package localization;

public class Language { //rename to Output?
	private static String language = "english";
	private static final String[] supportedLanguages = {"english", "deutsch"}; 
	
	public static void changeLanguage(String language) {
		var languageSupported = false;
		for(var l : supportedLanguages) {
			if(l == language) {
				languageSupported = true;
				Language.language = language;
			}
		}
		if(!languageSupported) {
			System.err.println(language + " is not supported! Language changed to default (english)");
			Language.language = "english";
		}
	}
	
	public static String[] getSupportedLanguages() {
		return supportedLanguages;
	}
	
	public static void printMenuOptions(int boardSize, int difficulty, boolean start, boolean pvp) {
		if(language == "deutsch") {
			System.out.println("MENÜ:"
					+ "\n0: Spielstart"
					+ "\n1: Spielfeldgöße ändern ("+boardSize+")"
					+ "\n2: Schwierigkeit ändern ("+difficulty+")"
					+ "\n3: Wer soll beginnen? (Spieler1 startet = "+start+")"
					+ "\n4: PvP / PvKI? (PvP = "+pvp+")"
							+ "\n5: Wähle die Sprache");
		} else {
			System.out.println("MENU:"
					+ "\n0: start game"
					+ "\n1: change boardsize ("+boardSize+")"
					+ "\n2: change difficulty ("+difficulty+")"
					+ "\n3: who should start? (Player1 starts = "+start+")"
					+ "\n4: PvP / PvAI? (PvP = "+pvp+")"
							+ "\n5: choose language");
		}
	}
	
	public static void printNoValidInput() {
		if(language == "deutsch") {
			System.out.println("Falsche Eingabe! Bitte erneut versuchen.");
		} else {
			System.out.println("Wrong input! Please try again.");
		}
	}
	
	public static void printBoardSize() {
		if(language == "deutsch") {
			System.out.println("Wähle eine Spielfeldgröße zwischen 15 und 19.");
		} else {
			System.out.println("Choose a boardsize between 15 and 19.");
		}
	}
	
	public static void printBoardSizeError() {
		if(language == "deutsch") {
			System.out.println("Spielfeldgröße nur zwischen 15 und 19 erlaubt!");
		} else {
			System.out.println("Boardsize can only be between 15 and 19!");
		}
	}
	public static void printDifficulty() {
		if(language == "deutsch") {
			System.out.println("Wähle die Schwierigkeit der KI zwischen 0 und 2.");
		} else {
			System.out.println("Choose AI difficulty between 0 and 2.");
		}
	}
	
	public static void printDifficultyError() {
		if(language == "deutsch") {
			System.out.println("Schwierigkeit nur zwischen 0 und 2 erlaubt!");
		} else {
			System.out.println("Difficulty can only be between 0 and 2!");
		}
	}
	
	public static void printStart() {
		if(language == "deutsch") {
			System.out.println("Drücke '0' damit Spieler1 beginnt oder '1' für Spieler2");
		} else {
			System.out.println("Press '0' for if Player1 should start or '1' if Player2 should start");
		}
	}
	
	public static void printStartError() {
		if(language == "deutsch") {
			System.out.println("Nur 0 oder 1 erlaubt!");
		} else {
			System.out.println("Only 0 or 1 permitted!");
		}
	}
	
	public static void printPvp() {
		if(language == "deutsch") {
			System.out.println("Drücke 0 für PvP oder 1 für PvKI");
		} else {
			System.out.println("Press 0 for PvP or 1 for PvAI");
		}
	}
	
	public static void printPvpError() {
		if(language == "deutsch") {
			System.out.println("Nur 0 oder 1 erlaubt!");
		} else {
			System.out.println("Only 0 or 1 permitted!");
		}
	}
	
	public static void printLanguageOptions() {
		if(language == "deutsch") {
			System.out.println("Welche Sprache soll verwendet werden? Drücke:");
			for(int i=0; i< supportedLanguages.length; i++) {
				System.out.println(i+": für "+supportedLanguages[i]);
			}
		} else {
			System.out.println("Which language should be used? Press:");
			for(int i=0; i< supportedLanguages.length; i++) {
				System.out.println(i+": for "+supportedLanguages[i]);
			}
		}
	}
	
	public static void printLanguageOptionsError() {
		if(language == "deutsch") {
			System.out.println("Eingabe nur zwischen 0 und "+ supportedLanguages.length + " erlaubt!");
		} else {
			System.out.println("Input only between 0 and "+ supportedLanguages.length + " permitted!");

		}
	}
}