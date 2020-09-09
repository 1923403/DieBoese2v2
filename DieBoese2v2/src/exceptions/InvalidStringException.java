package exceptions;

public class InvalidStringException extends Exception {

	private static final long serialVersionUID = 5949841289466520662L;

	public InvalidStringException(final String message) {
		super(message);
	}
}
