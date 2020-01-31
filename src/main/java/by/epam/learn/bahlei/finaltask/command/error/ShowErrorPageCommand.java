package by.epam.learn.bahlei.finaltask.command.error;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public class ShowErrorPageCommand implements ActionCommand {
    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        return null;
    }
}
