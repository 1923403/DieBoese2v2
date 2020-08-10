package localization;

public class Language {
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
	
	public static String getMenuOptions() {
		if(language == "deutsch") {
			return "MENÜ:"
					+ "\n0: Spielstart"
					+ "\n1: Spielfeldgöße ändern"
					+ "\n2: Schwierigkeit ändern"
					+ "\n3: Wer soll beginnen?"
					+ "\n4: PvP / PvKI?";
		} else {
			return "MENU:"
					+ "\n0: start game"
					+ "\n1: change boardsize"
					+ "\n2: change difficulty"
					+ "\n3: who should start?"
					+ "\n4: PvP / PvAI?";
		}
	}
	
	public static String getNoValidInput() {
		if(language == "deutsch") {
			return "Falsche Eingabe! Bitte erneut versuchen.";
		} else {
			return "Wrong input! Please try again.";
		}
	}
	
	public static String getBoardSize() {
		if(language == "deutsch") {
			return "Wähle eine Spielfeldgröße zwischen 15 und 19.";
		} else {
			return "Choose a boardsize between 15 and 19.";
		}
	}
	
	public static String getBoardSizeError() {
		if(language == "deutsch") {
			return "Spielfeldgröße nur zwischen 15 und 19 erlaubt!";
		} else {
			return "Boardsize can only be between 15 and 19!";
		}
	}
	public static String getDifficulty() {
		if(language == "deutsch") {
			return "Wähle die Schwierigkeit der KI zwischen 0 und 2.";
		} else {
			return "Choose AI difficulty between 0 and 2.";
		}
	}
}
