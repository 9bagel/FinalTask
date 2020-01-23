package by.epam.learn.bahlei.finaltask.command;


import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.util.Constants;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {

    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        return new Response(Constants.MAIN_JSP, Response.ResponseType.FORWARD);
    }
}
