package model;

public class Move {

	private int[] enemyMove;
	private int[] myMove;

	// numbers represent y-coordinates, letters x-coordinates
	private int[] convertCoordinates(final String coordinates) {
		final var array = new int[2];
		var number = "";

		for (var i = 0; i < coordinates.length(); i++)
			if ((coordinates.charAt(i) < 58))
				number += coordinates.charAt(i);
			else
				array[1] = coordinates.toUpperCase().charAt(i);

		array[0] = Integer.valueOf(number);

		return array;
	}

	private void setEnemyMove(final int[] move) {
		this.enemyMove = move;
	}

	private void setMyMove(final int[] move) {
		this.myMove = move;
	}
}
