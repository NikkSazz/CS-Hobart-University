
/**
 * Exception indicating an error with parsing a file (due to improper file
 * format).
 * 
 * @author ssb
 */
@SuppressWarnings("serial")
public class ParseException extends RuntimeException {

	/**
	 * 
	 */
	public ParseException () {}

	/**
	 * @param message
	 */
	public ParseException ( String message ) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ParseException ( Throwable cause ) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ParseException ( String message, Throwable cause ) {
		super(message,cause);
	}

}
