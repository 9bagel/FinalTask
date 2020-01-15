package by.epam.learn.bahlei.finaltask.logic.exception;

public class UserException extends Exception {
    private static final long serialVersionUID = -3582591351443992235L;

    public UserException() {
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserException(Throwable cause) {
        super(cause);
    }
}
