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
		System.out.println("AusgabeMenü........"); // to do
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
		this.boardSize = in.nextInt(); // wrong input possible... has to be changed
	}

	private void setDifficulty(Scanner in) {
		System.out.println("frage nach schwierigkeit...eingabe erwartet"); // to do
		this.difficulty = in.nextInt(); // wrong input possible... has to be changed
	}

	private void setStart(Scanner in) {
		System.out.println("frage wer beginnen soll... eingabe erwartet"); // to do
		if (in.nextInt() == 0)
			this.start = true;
		else
			this.start = false;

	}

	private void setPvp(Scanner in) {
		System.out.println("frage nach spielmodus... eingabe erwartet");
		if (in.nextInt() == 0)
			pvp = true;
		else
			pvp = false;
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

	public boolean isStart() {
		return start;
	}

}
