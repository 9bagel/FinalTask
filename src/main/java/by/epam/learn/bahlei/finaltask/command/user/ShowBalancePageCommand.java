package by.epam.learn.bahlei.finaltask.command.user;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.util.Constants;

import javax.servlet.http.HttpServletRequest;

public class ShowBalancePageCommand implements ActionCommand {

    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        return new Response(Constants.BALANCE_JSP, Response.ResponseType.FORWARD);
    }
}