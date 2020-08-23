package model;

import io.Input;
import io.localization.ConsoleOutput;
import io.localization.Language;

public class Settings {

	private static Language currentLanguage = Language.EN;

	public static Language getCurrenLanguage() {
		return Settings.currentLanguage;
	}

	public void setCurrentLanguage() {
		switch (Integer.valueOf(Input.readInput())) {
			case 0:
				Settings.currentLanguage = Language.EN;
				break;
			case 1:
				Settings.currentLanguage = Language.DE;
				break;
			default:
				ConsoleOutput.printLanguageOptionsError();
				break;
		}
	}
}
