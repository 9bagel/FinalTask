package by.epam.learn.bahlei.finaltask.command.order;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.entity.service.Service;
import by.epam.learn.bahlei.finaltask.entity.user.User;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.exception.UserException;
import by.epam.learn.bahlei.finaltask.logic.factory.LogicFactory;
import by.epam.learn.bahlei.finaltask.logic.order.OrderLogic;
import by.epam.learn.bahlei.finaltask.logic.user.UserLogic;
import by.epam.learn.bahlei.finaltask.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PayCommand implements ActionCommand {
    private LogicFactory logicFactory = LogicFactory.getInstance();
    private OrderLogic orderLogic = logicFactory.getOrderLogic();
    private UserLogic userLogic = logicFactory.getUserLogic();

    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String language = String.valueOf(session.getAttribute(Constants.LOCALE));
        List<Service> services = new ArrayList<>();

        int userId = (int) session.getAttribute(Constants.ID);

        try {
            User user = userLogic.getUserById(userId);
            BigDecimal balance = user.getBalance();

            services = orderLogic.getOrderedServices(userId, language);
        } catch (UserException e) {
            session.setAttribute(Constants.SESSION_ERROR_ATTRIBUTE, e);
            return new Response(Constants.ERROR_JSP, Response.ResponseType.FORWARD);
        } catch (LogicException e) {
            return new Response(Constants.ERROR_JSP, Response.ResponseType.FORWARD);
        }

        return null;
    }
}
