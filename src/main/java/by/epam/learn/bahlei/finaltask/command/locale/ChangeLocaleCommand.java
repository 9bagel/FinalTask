package by.epam.learn.bahlei.finaltask.command.locale;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLocaleCommand implements ActionCommand {
    private static final String REFERER = "referer";

    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String lang = request.getParameter("lang");
        session.setAttribute("lang", lang);
        String lastUrl = request.getHeader(REFERER);
        return new Response(lastUrl, Response.ResponseType.REDIRECT);
    }
}
