package by.epam.learn.bahlei.finaltask.command;

import by.epam.learn.bahlei.finaltask.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
    Response execute(HttpServletRequest request) throws CommandException;
}
