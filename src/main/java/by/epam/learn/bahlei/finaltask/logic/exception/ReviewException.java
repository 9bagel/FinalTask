package by.epam.learn.bahlei.finaltask.logic.exception;
/**
 * Represents an exception that may be thrown on the logic layer
 */
public class ReviewException extends Exception {
    private static final long serialVersionUID = 8618201153789761378L;

    public ReviewException() {
        super();
    }

    public ReviewException(String message) {
        super(message);
    }

    public ReviewException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReviewException(Throwable cause) {
        super(cause);
    }
}
