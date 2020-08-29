package model;

import io.Input;
import io.localization.ConsoleOutput;

public class Menu {
	private Settings settings;

	public Menu(Settings settings) {
		this.settings = settings;
	}

	public boolean settingsChoosen() {
		ConsoleOutput.printMenuOptions(this.settings.getBoardSize(), this.settings.getDifficulty(),
				this.settings.getStart(), this.settings.isPvp());
		var change = Input.readInput();
		switch (change) {
			case "0":
				break;
			case "1":
				this.settings.setBoardSize();
				break;
			case "2":
				this.settings.setDifficulty();
				break;
			case "3":
				this.settings.setStart();
				break;
			case "4":
				this.settings.setPvp();
				break;
			case "5":
				this.settings.setCurrentLanguage();
				break;
			default:
				ConsoleOutput.printNoValidInput();
				break;
		}
		return (Integer.valueOf(change) == 0);
	}
}
