package by.epam.learn.bahlei.finaltask.command.order;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.entity.user.User;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.exception.OrderException;
import by.epam.learn.bahlei.finaltask.logic.factory.LogicFactory;
import by.epam.learn.bahlei.finaltask.logic.order.OrderLogic;
import by.epam.learn.bahlei.finaltask.util.Constants;
import by.epam.learn.bahlei.finaltask.util.requestutil.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CancelOrderCommand implements ActionCommand {
    private LogicFactory logicFactory = LogicFactory.getInstance();
    private OrderLogic orderLogic = logicFactory.getOrderLogic();

    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();

        int orderId = Integer.parseInt(request.getParameter(Constants.ORDER_ID));
        User user = RequestUtil.getUser(session);

        try {
            orderLogic.cancelOrder(user.getId(), orderId);

            session.setAttribute(Constants.SESSION_SUCCESS_ATTRIBUTE, Constants.ORDER_CANCELED_MESSAGE);
            return new Response(request.getContextPath() + Constants.SUCCESS_PAGE, Response.ResponseType.REDIRECT);
        } catch (OrderException e) {
            session.setAttribute(Constants.SESSION_ERROR_ATTRIBUTE, e.getMessage());
            return new Response(Constants.ERROR_JSP, Response.ResponseType.FORWARD);
        } catch (LogicException e) {
            session.setAttribute(Constants.SESSION_ERROR_ATTRIBUTE, Constants.ORDER_CANCEL_ERROR);
            return new Response(Constants.ERROR_JSP, Response.ResponseType.FORWARD);
        }
    }
}
