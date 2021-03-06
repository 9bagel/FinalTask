package by.epam.learn.bahlei.finaltask.command.exception;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;

/**
 * Custom exception for classes that implements {@link ActionCommand}
 */
public class CommandException extends Exception {
    private static final long serialVersionUID = 7474662576817998279L;

    public CommandException() {
    }

    public CommandException(String message) {
        super(message);
    }

    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandException(Throwable cause) {
        super(cause);
    }
}
