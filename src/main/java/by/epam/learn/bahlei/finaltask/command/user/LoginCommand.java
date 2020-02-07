package by.epam.learn.bahlei.finaltask.command.user;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.entity.user.User;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.exception.UserException;
import by.epam.learn.bahlei.finaltask.logic.factory.LogicFactory;
import by.epam.learn.bahlei.finaltask.logic.user.UserLogic;
import by.epam.learn.bahlei.finaltask.util.Constants;
import by.epam.learn.bahlei.finaltask.util.XssCleaner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ActionCommand {
    private LogicFactory logicFactory = LogicFactory.getInstance();
    private UserLogic userLogic = logicFactory.getUserLogic();

    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String login = XssCleaner.clean(request.getParameter(Constants.USER_LOGIN));
        String password = XssCleaner.clean(request.getParameter(Constants.USER_Password));

        try {
            User user = userLogic.login(login, password);

            session.setAttribute(Constants.USER, user);
            return new Response(request.getContextPath(), Response.ResponseType.REDIRECT);
        } catch (LogicException e) {
            throw LOGGER.throwing(new CommandException("LogicException in execute method in LoginCommand:", e));
        } catch (UserException e) {
            LOGGER.info("User does not exists");
            session.setAttribute(Constants.SESSION_ERROR_ATTRIBUTE, Constants.ERROR_LOGIN);
            return new Response(request.getContextPath() + Constants.LOGIN_PAGE, Response.ResponseType.REDIRECT);
        }
    }
}
