package by.epam.learn.bahlei.finaltask.util.validator.exception;

/**
 * Represents custom exception throwing when validation wasn't complete
 */
public class ValidationException extends Exception {
    private static final long serialVersionUID = -8252272031811108157L;

    public ValidationException() {
        super();
    }

    public ValidationException(String message) {
        super(message);
    }
}
