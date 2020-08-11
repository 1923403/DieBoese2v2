package model;

import java.util.InputMismatchException;
import java.util.Scanner;

import control.Game;
import localization.Language;

public class Menu {
	// default values
	private int boardSize = 15;
	private int difficulty = 3;
	private boolean pvp = false;
	private boolean start = true;

	public boolean settingsChoosen() {
		Scanner in = new Scanner(System.in);
		Language.printMenuOptions(boardSize, difficulty, start, pvp);
		var change = 0;
		try {
			change = in.nextInt();
		} catch (InputMismatchException e) {
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
			Language.printNoValidInput();
			break;
		}

		return (change == 0);
	}

	private void setBoardSize() {
		Language.printBoardSize();
		int input = -1;
		do {
			try {
				input = Integer.valueOf(Game.readInput());
				if (input < 15 || input > 19)
					throw new NumberFormatException();
			} catch (NumberFormatException e) {
				Language.printBoardSizeError();
			}
		} while (input < 15 || input > 19);
		this.boardSize = input;
	}

	private void setDifficulty() {
		Language.printDifficulty();
		int diff = -1;
		do {
			try {
				diff = Integer.valueOf(Game.readInput());
				if (diff < 0 || diff > 2)
					throw new NumberFormatException();
			} catch (NumberFormatException e) {
				Language.printDifficultyError();
			}
		} while (diff < 0 || diff > 2);
		this.difficulty = diff;

	}

	private void setStart() {
		Language.printStart();
		int start = -1;
		do {
			try {
				start = Integer.valueOf(Game.readInput());
				if (start != 0 && start != 1)
					throw new NumberFormatException();
			} catch (NumberFormatException e) {
				Language.printStartError();
			}
		} while (start != 0 && start != 1);
		if (start == 0)
			this.start = true;
		else
			this.start = false;

	}

	private void setPvp() {
		Language.printPvp();
		int pvp = -1;
		do {
			try {
				pvp = Integer.valueOf(Game.readInput());
				if (pvp != 0 && pvp != 1)
					throw new NumberFormatException();
			} catch (NumberFormatException e) {
				Language.printPvpError();
			}
		} while (pvp != 0 && pvp != 1);
		if (pvp == 0)
			this.pvp = true;
		else
			this.pvp = false;
	}

	private void changeLanguage() {
		Language.printLanguageOptions();
		int l = -1;
		do { 
			try {
				l = Integer.valueOf(Game.readInput());
				if (l < 0 || l > Language.getSupportedLanguages().length - 1)
					throw new NumberFormatException();
			} catch (NumberFormatException e) {
				Language.printLanguageOptionsError();
			}
		} while (l < 0 || l > Language.getSupportedLanguages().length - 1);
		Language.changeLanguage(Language.getSupportedLanguages()[l]);
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
