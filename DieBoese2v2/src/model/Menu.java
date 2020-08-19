package model;

import control.Game;
import localization.ConsoleOutput;

public class Menu {
	// default values
	private int boardSize = 15;
	private int difficulty = 2;
	private boolean pvp = false;
	private boolean start = true;

	public boolean settingsChoosen() {
		ConsoleOutput.printMenuOptions(boardSize, difficulty, start, pvp);
		var change = 0;
		try {
			change = Integer.valueOf(Game.readInput());
		} catch (NumberFormatException e) {
			change = -1;
		}
		switch (change) {
		case 0:
			break;
		case 1:
			setBoardSize();
			break;
		case 2:
			setDifficulty();
			break;
		case 3:
			setStart();
			break;
		case 4:
			setPvp();
			break;
		case 5:
			changeLanguage();
			break;
		default:
			ConsoleOutput.printNoValidInput();
			break;
		}

		return (change == 0);
	}

	private void setBoardSize() {
		ConsoleOutput.printBoardSize();
		int input = -1;
		do {
			try {
				input = Integer.valueOf(Game.readInput());
				if (input < 15 || input > 19)
					throw new NumberFormatException();
			} catch (NumberFormatException e) {
				ConsoleOutput.printBoardSizeError();
			}
		} while (input < 15 || input > 19);
		this.boardSize = input;
	}

	private void setDifficulty() {
		ConsoleOutput.printDifficulty();
		int diff = -1;
		do {
			try {
				diff = Integer.valueOf(Game.readInput());
				if (diff < 0 || diff > 2)
					throw new NumberFormatException();
			} catch (NumberFormatException e) {
				ConsoleOutput.printDifficultyError();
			}
		} while (diff < 0 || diff > 2);
		this.difficulty = diff;

	}

	private void setStart() {
		ConsoleOutput.printStart();
		int start = -1;
		do {
			try {
				start = Integer.valueOf(Game.readInput());
				if (start != 0 && start != 1)
					throw new NumberFormatException();
			} catch (NumberFormatException e) {
				ConsoleOutput.printStartError();
			}
		} while (start != 0 && start != 1);
		if (start == 0)
			this.start = true;
		else
			this.start = false;

	}

	private void setPvp() {
		ConsoleOutput.printPvp();
		int pvp = -1;
		do {
			try {
				pvp = Integer.valueOf(Game.readInput());
				if (pvp != 0 && pvp != 1)
					throw new NumberFormatException();
			} catch (NumberFormatException e) {
				ConsoleOutput.printPvpError();
			}
		} while (pvp != 0 && pvp != 1);
		if (pvp == 0)
			this.pvp = true;
		else
			this.pvp = false;
	}

	private void changeLanguage() {
		ConsoleOutput.printLanguageOptions();
		int l = -1;
		do { 
			try {
				l = Integer.valueOf(Game.readInput());
				if (l < 0 || l > ConsoleOutput.getSupportedLanguages().length - 1)
					throw new NumberFormatException();
			} catch (NumberFormatException e) {
				ConsoleOutput.printLanguageOptionsError();
			}
		} while (l < 0 || l > ConsoleOutput.getSupportedLanguages().length - 1);
		ConsoleOutput.changeLanguage(ConsoleOutput.getSupportedLanguages()[l]);
	}

	public int getBoardSize() {
		return boardSize;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public boolean isPvp() {
		return pvp;
	}

	public boolean getStart() {
		return start;
	}

}
