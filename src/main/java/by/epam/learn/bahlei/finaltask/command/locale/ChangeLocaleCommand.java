package by.epam.learn.bahlei.finaltask.command.locale;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLocaleCommand implements ActionCommand {

    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String lang = request.getParameter(Constants.LOCALE);
        session.setAttribute(Constants.LOCALE, lang);
        String lastUrl = request.getHeader(Constants.REFERER);
        return new Response(lastUrl, Response.ResponseType.REDIRECT);
    }
}
