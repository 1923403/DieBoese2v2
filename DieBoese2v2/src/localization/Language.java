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
}
