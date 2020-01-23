package by.epam.learn.bahlei.finaltask.command.user;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.logic.factory.LogicFactory;
import by.epam.learn.bahlei.finaltask.logic.user.UserLogic;
import by.epam.learn.bahlei.finaltask.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddToCartCommand implements ActionCommand {

    private LogicFactory logicFactory = LogicFactory.getInstance();
    private UserLogic userLogic = logicFactory.getUserLogic();

    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();

        if (session.getAttribute(Constants.SESSION_USER_TYPE_ID) == null) {
            session.setAttribute(Constants.SESSION_ERROR_ATTRIBUTE, Constants.YOU_NEED_TO_LOGIN_MESSAGE);
            return new Response(Constants.ERROR_PAGE, Response.ResponseType.FORWARD);
        } else {
            userLogic.addServiceToCart();
        }

        return new Response(Constants.ERROR_PAGE, Response.ResponseType.FORWARD);
    }
}
