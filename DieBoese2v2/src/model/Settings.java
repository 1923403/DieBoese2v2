package model;

import io.Input;
import io.localization.ConsoleOutput;
import io.localization.Language;

public class Settings {

	private static Language currentLanguage = Language.EN;
	private int boardSize = 15;
	private int difficulty = 2;
	private boolean pvp = false;
	private boolean start = true;

	public static Language getCurrenLanguage() {
		return Settings.currentLanguage;
	}

	public int getBoardSize() {
		return this.boardSize;
	}

	public int getDifficulty() {
		return this.difficulty;
	}

	public boolean getStart() {
		return this.start;
	}

	public boolean isPvp() {
		return this.pvp;
	}

	public void setBoardSize() {
		ConsoleOutput.printBoardSize();
		switch (Input.readInput()) {
			case "15":
				this.boardSize = 15;
				break;
			case "16":
				this.boardSize = 16;
				break;
			case "17":
				this.boardSize = 17;
				break;
			case "18":
				this.boardSize = 18;
				break;
			case "19":
				this.boardSize = 19;
				break;
			default:
				ConsoleOutput.printBoardSizeError();
				break;
		}
	}

	public void setCurrentLanguage() {
		ConsoleOutput.printLanguageOptions();
		switch (Input.readInput()) {
			case "0":
				Settings.currentLanguage = Language.EN;
				break;
			case "1":
				Settings.currentLanguage = Language.DE;
				break;
			default:
				ConsoleOutput.printLanguageOptionsError();
				break;
		}
	}

	public void setDifficulty() {
		ConsoleOutput.printDifficulty();
		switch (Input.readInput()) {
			case "0":
				this.difficulty = 0;
				break;
			case "1":
				this.difficulty = 1;
				break;
			case "2":
				this.difficulty = 2;
				break;
			default:
				ConsoleOutput.printDifficultyError();
				break;
		}
	}

	public void setPvp() {
		ConsoleOutput.printPvp();
		switch (Input.readInput()) {
			case "0":
				this.pvp = true;
				break;
			case "1":
				this.pvp = false;
				break;
			default:
				ConsoleOutput.printPvpError();
				break;
		}
	}

	public void setStart() {
		ConsoleOutput.printStart();
		switch (Input.readInput()) {
			case "0":
				this.start = true;
				break;
			case "1":
				this.start = false;
				break;
			default:
				ConsoleOutput.printStartError();
				break;
		}
	}
}
