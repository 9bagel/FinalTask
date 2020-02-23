package by.epam.learn.bahlei.finaltask.command.user;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.entity.user.User;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.factory.LogicFactory;
import by.epam.learn.bahlei.finaltask.logic.user.UserLogic;
import by.epam.learn.bahlei.finaltask.util.Constants;
import by.epam.learn.bahlei.finaltask.util.requestutil.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UpdateUserCommand implements ActionCommand {
    private final UserLogic userLogic = LogicFactory.getUserLogic();

    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        try {
            User user = RequestUtil.parseUserData(request);
            userLogic.updateUser(user);

            session.setAttribute(Constants.USER, user);
            session.setAttribute(Constants.SESSION_SUCCESS_ATTRIBUTE, Constants.USER_UPDATE_MESSAGE);
            return new Response(request.getHeader(Constants.REFERER), Response.ResponseType.REDIRECT);
        } catch (LogicException e) {
            request.getSession().setAttribute(Constants.SESSION_ERROR_ATTRIBUTE, Constants.USER_UPDATE_ERROR);
            return new Response(Constants.ERROR_JSP, Response.ResponseType.FORWARD);
        }
    }
}
