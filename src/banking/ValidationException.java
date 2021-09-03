package banking;

@SuppressWarnings("serial")
public class ValidationException extends Exception {

	
	
	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationException(String string) {
		super(string);
	}
}