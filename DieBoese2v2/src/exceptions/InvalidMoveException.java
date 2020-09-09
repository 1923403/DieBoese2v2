package exceptions;

public class InvalidMoveException extends Exception {

	private static final long serialVersionUID = 5205258843302734848L;

	public InvalidMoveException(final String message) {
		super(message);
	}
}
