package by.epam.learn.bahlei.finaltask.command;

import by.epam.learn.bahlei.finaltask.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * {@link ActionCommand} is an interface representing a command, handled by a servlet
 */
public interface ActionCommand {
    /**
     * Handles request to the servlet
     */
    Response execute(HttpServletRequest request) throws CommandException;
}