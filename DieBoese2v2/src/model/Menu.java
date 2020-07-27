package model;

import java.util.Scanner;

public class Menu {

	private int boardSize;
	private int difficulty;
	private boolean pvp;
	private boolean start;

	public boolean settingsChoosen() {
		Scanner in = new Scanner(System.in);
		System.out.println("AusgabeMenü........");
		var change = 0;
		change = in.nextInt();
		switch (change) {
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
		default:
			System.out.println("kein valider input...");
			break;
		}

		return (change == 0);
	}

	private void setBoardSize(Scanner in) {

	}

	private void setDifficulty(Scanner in) {

	}

	private void setStart(Scanner in) {

	}

	private void setPvp(Scanner in) {

	}
}
