package by.epam.learn.bahlei.finaltask.command.user;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.entity.user.User;
import by.epam.learn.bahlei.finaltask.entity.user.UserRole;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.factory.LogicFactory;
import by.epam.learn.bahlei.finaltask.logic.user.UserLogic;
import by.epam.learn.bahlei.finaltask.util.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ManageUsersCommand implements ActionCommand {
    private UserLogic userLogic = LogicFactory.getUserLogic();

    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        try {
            List<User> users = userLogic.getAll();

            request.setAttribute(Constants.ATTRIBUTE_USERS, users);
            request.setAttribute(Constants.ATTRIBUTE_USER_TYPES, UserRole.values());
            return new Response(Constants.MANAGE_USERS_JSP, Response.ResponseType.FORWARD);
        } catch (LogicException e) {
            request.getSession().setAttribute(Constants.SESSION_ERROR_ATTRIBUTE, Constants.CREATE_ORDER_ERROR);
            return new Response(Constants.ERROR_JSP, Response.ResponseType.FORWARD);
        }
    }
}
