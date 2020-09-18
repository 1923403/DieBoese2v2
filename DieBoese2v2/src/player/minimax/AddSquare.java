package player.minimax;

import java.awt.Point;
import java.util.ArrayList;

public class AddSquare {

	private final char[][] board;
	private final Point center;
	private final ArrayList<Point> list;
	private final int squareSize;

	public AddSquare(final char[][] board, final Point center, final ArrayList<Point> list, final int squareSize) {
		this.board = board;
		this.center = center;
		this.list = list;
		this.squareSize = squareSize;
	}

	public void run() {
		for (int x = this.center.x - this.squareSize; x <= (this.center.x + this.squareSize); x++)
			for (int y = this.center.y - this.squareSize; y <= (this.center.y + this.squareSize); y++)
				if (this.isEmptyField(x, y) && !this.isOnList(x, y))
					this.list.add(new Point(x, y));
	}

	private boolean isEmptyField(final int x, final int y) {
		return ((x >= 0) && (x < this.board.length)) && ((y >= 0) && (y < this.board.length))
				&& (this.board[x][y] == ' ');
	}

	private boolean isOnList(final int x, final int y) {
		return this.list.contains(new Point(x, y));
	}
}
