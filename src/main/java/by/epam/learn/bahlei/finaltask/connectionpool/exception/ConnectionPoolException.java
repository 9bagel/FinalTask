package by.epam.learn.bahlei.finaltask.connectionpool.exception;

public class ConnectionPoolException extends Exception {
    private static final long serialVersionUID = 6645811201084938339L;

    public ConnectionPoolException() {
        super();
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}
