package model;

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
		change = in.nextInt();
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
		var input = in.nextInt();
		while (input < 15 || input > 19) {
			Language.printBoardSizeError();
			input = in.nextInt();
		}
		this.boardSize = input;
	}

	private void setDifficulty(Scanner in) {
		Language.printDifficulty(); // to do
		var diff = in.nextInt();
		while (diff < 0 || diff > 2) {
			Language.printDifficultyError();
			diff = in.nextInt();
		}
		this.difficulty = diff;
	}

	private void setStart(Scanner in) {
		Language.printStart();
		var start = in.nextInt();
		while (start != 0 && start != 1) {
			Language.printStartError();
			start = in.nextInt();
		}
		if (start == 0)
			this.start = true;
		else
			this.start = false;

	}

	private void setPvp(Scanner in) {
		Language.printPvp();
		var pvp = in.nextInt();
		while (pvp != 0 && pvp != 1) {
			Language.printPvpError();
			pvp = in.nextInt();
		}
		if (pvp == 0)
			this.pvp = true;
		else
			this.pvp = false;
	}
	
	private void changeLanguage(Scanner in) {
		Language.printLanguageOptions();
		var l = in.nextInt();
		while(l <0 && l > Language.getSupportedLanguages().length){
			Language.printLanguageOptionsError();
			l = in.nextInt();
		}
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
