package model;

import control.Game;
import localization.ConsolOutput;

public class Menu {
	// default values
	private int boardSize = 15;
	private int difficulty = 2;
	private boolean pvp = false;
	private boolean start = true;

	public boolean settingsChoosen() {
		ConsolOutput.printMenuOptions(boardSize, difficulty, start, pvp);
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
			ConsolOutput.printNoValidInput();
			break;
		}

		return (change == 0);
	}

	private void setBoardSize() {
		ConsolOutput.printBoardSize();
		int input = -1;
		do {
			try {
				input = Integer.valueOf(Game.readInput());
				if (input < 15 || input > 19)
					throw new NumberFormatException();
			} catch (NumberFormatException e) {
				ConsolOutput.printBoardSizeError();
			}
		} while (input < 15 || input > 19);
		this.boardSize = input;
	}

	private void setDifficulty() {
		ConsolOutput.printDifficulty();
		int diff = -1;
		do {
			try {
				diff = Integer.valueOf(Game.readInput());
				if (diff < 0 || diff > 2)
					throw new NumberFormatException();
			} catch (NumberFormatException e) {
				ConsolOutput.printDifficultyError();
			}
		} while (diff < 0 || diff > 2);
		this.difficulty = diff;

	}

	private void setStart() {
		ConsolOutput.printStart();
		int start = -1;
		do {
			try {
				start = Integer.valueOf(Game.readInput());
				if (start != 0 && start != 1)
					throw new NumberFormatException();
			} catch (NumberFormatException e) {
				ConsolOutput.printStartError();
			}
		} while (start != 0 && start != 1);
		if (start == 0)
			this.start = true;
		else
			this.start = false;

	}

	private void setPvp() {
		ConsolOutput.printPvp();
		int pvp = -1;
		do {
			try {
				pvp = Integer.valueOf(Game.readInput());
				if (pvp != 0 && pvp != 1)
					throw new NumberFormatException();
			} catch (NumberFormatException e) {
				ConsolOutput.printPvpError();
			}
		} while (pvp != 0 && pvp != 1);
		if (pvp == 0)
			this.pvp = true;
		else
			this.pvp = false;
	}

	private void changeLanguage() {
		ConsolOutput.printLanguageOptions();
		int l = -1;
		do { 
			try {
				l = Integer.valueOf(Game.readInput());
				if (l < 0 || l > ConsolOutput.getSupportedLanguages().length - 1)
					throw new NumberFormatException();
			} catch (NumberFormatException e) {
				ConsolOutput.printLanguageOptionsError();
			}
		} while (l < 0 || l > ConsolOutput.getSupportedLanguages().length - 1);
		ConsolOutput.changeLanguage(ConsolOutput.getSupportedLanguages()[l]);
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
