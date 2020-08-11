package model;

import java.util.InputMismatchException;
import java.util.Scanner;

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
			setBoardSize(in);
			break;
		case 2:
			setDifficulty(in);
			break;
		case 3:
			setStart(in);
			break;
		case 4:
			setPvp(in);
			break;
		case 5:
			changeLanguage(in);
			break;
		default:
			Language.printNoValidInput();
			break;
		}

		return (change == 0);
	}

	private void setBoardSize(Scanner in) {
		Language.printBoardSize();
		int input = -1;
		do {
			try {
				input = in.nextInt();
				if (input < 15 || input > 19)
					throw new InputMismatchException();
			} catch (InputMismatchException e) {
				Language.printBoardSizeError();
			}
		} while (input < 15 || input > 19);
		this.boardSize = input;
	}

	private void setDifficulty(Scanner in) {
		Language.printDifficulty();
		int diff = -1;
		do {
			try {
				diff = in.nextInt();
				if (diff < 0 || diff > 2)
					throw new InputMismatchException();
			} catch (InputMismatchException e) {
				Language.printDifficultyError();
			}
		} while (diff < 0 || diff > 2);
		this.difficulty = diff;

	}

	private void setStart(Scanner in) {
		Language.printStart();
		int start = -1;
		do {
			try {
				start = in.nextInt();
				if (start != 0 && start != 1)
					throw new InputMismatchException();
			} catch (InputMismatchException e) {
				Language.printStartError();
			}
		} while (start != 0 && start != 1);
		if (start == 0)
			this.start = true;
		else
			this.start = false;

	}

	private void setPvp(Scanner in) {
		Language.printPvp();
		int pvp = -1;
		do {
			try {
				pvp = in.nextInt();
				if (pvp != 0 && pvp != 1)
					throw new InputMismatchException();
			} catch (InputMismatchException e) {
				Language.printPvpError();
			}
		} while (pvp != 0 && pvp != 1);
		if (pvp == 0)
			this.pvp = true;
		else
			this.pvp = false;
	}

	private void changeLanguage(Scanner in) {
		Language.printLanguageOptions();
		int l = -1;
		do {
			try {
				l = in.nextInt();
				if (l < 0 || l > Language.getSupportedLanguages().length - 1)
					throw new InputMismatchException();
			} catch (InputMismatchException e) {
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
