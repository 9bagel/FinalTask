package by.epam.learn.bahlei.finaltask.command.user;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.entity.User;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.exception.UserException;
import by.epam.learn.bahlei.finaltask.logic.factory.LogicFactory;
import by.epam.learn.bahlei.finaltask.logic.user.UserLogic;
import by.epam.learn.bahlei.finaltask.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private LogicFactory logicFactory = LogicFactory.getInstance();
    private UserLogic userLogic = logicFactory.getUserLogic();

    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        String path;

        //Extracting login and password from request
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        HttpSession session = request.getSession();

        //Check if user exists in database and password correct
        try {
            User user = userLogic.login(login, password);
            session.setAttribute(Constants.SESSION_USER_LOGIN, user.getLogin());
            session.setAttribute(Constants.SESSION_USER_ID, user.getId());
            session.setAttribute(Constants.SESSION_USER_TYPE_ID, user.getTypeId());
            path = request.getContextPath();

        } catch (LogicException e) {

            throw LOGGER.throwing(new CommandException("LogicException in execute method in LoginCommand:", e));
        } catch (UserException e) {

            LOGGER.info("User does not exists");
            session.setAttribute("errorLoginPassMessage", Constants.ERROR_LOGIN);
            path = request.getContextPath() + "/controller/login";
        }
        return new Response(path, Response.ResponseType.REDIRECT);
    }
}
