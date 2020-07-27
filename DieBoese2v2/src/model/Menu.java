package model;

import java.util.Scanner;

public class Menu {
	//default values
	private int boardSize = 15;
	private int difficulty = 3;
	private boolean pvp = false;
	private boolean start = true;

	public boolean settingsChoosen() {
		Scanner in = new Scanner(System.in);
		System.out.println("AusgabeMenü........\n0: start game\n1: boardSize\n2: schwierigkeit\n3: start\n4: pvp/pvAI"); // to do
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
		default:
			System.out.println("kein valider input..."); // to do
			break;
		}

		return (change == 0);
	}

	private void setBoardSize(Scanner in) {
		System.out.println("Frage nach spielfeldgröße... eingabe erwartet"); // to do
		var input = in.nextInt();
		while(input < 15 || input > 19) {
			System.out.println("eingabe nur zwischen 15 und 19 erlaubt!"); // to do
			input = in.nextInt();
		}
		this.boardSize = input;
	}

	private void setDifficulty(Scanner in) {
		System.out.println("frage nach schwierigkeit...eingabe erwartet"); // to do
		var diff = in.nextInt();
		while(diff < 0 || diff > 2) {
			System.out.println("schwierigkeit nur zwischen 0 und 2 erlaubt!"); //to do
			diff = in.nextInt();
		}
		this.difficulty = diff;
	}

	private void setStart(Scanner in) {
		System.out.println("frage wer beginnen soll... eingabe erwartet"); // to do
		var start = in.nextInt();
		while(start != 0 || start != 1) {
			System.out.println("start erwartet 0 oder 1"); // to do
			start = in.nextInt();
		}
		if (start == 0)
			this.start = true;
		else
			this.start = false;

	}

	private void setPvp(Scanner in) {
		System.out.println("frage nach spielmodus... eingabe erwartet"); // to do
		var pvp = in.nextInt();
		while(pvp != 0 || pvp != 1) {
			System.out.println("pvp erwartet 0 oder 1"); //to do
			pvp = in.nextInt();
		}
		if (pvp == 0)
			this.pvp = true;
		else
			this.pvp = false;
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
