package by.epam.learn.bahlei.finaltask.logic.exception;

public class LogicException extends Exception {

    private static final long serialVersionUID = -9173378622474727616L;

    public LogicException() {
        super();
    }

    public LogicException(String message) {
        super(message);
    }

    public LogicException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogicException(Throwable cause) {
        super(cause);
    }
}
