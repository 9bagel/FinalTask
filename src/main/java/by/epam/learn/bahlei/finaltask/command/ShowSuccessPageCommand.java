package by.epam.learn.bahlei.finaltask.command;

import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.util.Constants;

import javax.servlet.http.HttpServletRequest;

public class ShowSuccessPageCommand implements ActionCommand {
    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        return new Response(Constants.SUCCESS_JSP, Response.ResponseType.FORWARD);
    }
}