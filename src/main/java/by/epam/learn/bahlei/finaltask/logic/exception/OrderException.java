package by.epam.learn.bahlei.finaltask.logic.exception;

public class OrderException extends Exception {

    private static final long serialVersionUID = -3717764055248285898L;

    public OrderException() {
        super();
    }

    public OrderException(String message) {
        super(message);
    }

    public OrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderException(Throwable cause) {
        super(cause);
    }
}
