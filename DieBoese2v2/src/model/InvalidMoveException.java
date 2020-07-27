package model;

public class InvalidMoveException extends Exception {

	private static final long serialVersionUID = 5205258843302734848L;

	public InvalidMoveException() {
	}

	public InvalidMoveException(String message) {
		super(message);
	}
}
