package by.epam.learn.bahlei.finaltask.command.order;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.entity.order.Order;
import by.epam.learn.bahlei.finaltask.entity.user.User;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.factory.LogicFactory;
import by.epam.learn.bahlei.finaltask.logic.order.OrderLogic;
import by.epam.learn.bahlei.finaltask.model.ShoppingCart;
import by.epam.learn.bahlei.finaltask.util.Constants;
import by.epam.learn.bahlei.finaltask.util.sessionutil.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CreateOrderCommand implements ActionCommand {
    private LogicFactory logicFactory = LogicFactory.getInstance();
    private OrderLogic orderLogic = logicFactory.getOrderLogic();

    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        try {
            ShoppingCart shoppingCart = SessionUtil.getShoppingCart(session);

            if (shoppingCart.getServiceIds().isEmpty()) {
                session.setAttribute(Constants.SESSION_ERROR_ATTRIBUTE, Constants.EMPTY_CART_ERROR);
                return new Response(Constants.ERROR_JSP, Response.ResponseType.FORWARD);
            }

            Order order = SessionUtil.getOrder(session, request);
            orderLogic.createOrder(shoppingCart.getServiceIds(), order);

            session.setAttribute(Constants.SESSION_SUCCESS_ATTRIBUTE, Constants.ORDER_CREATED_MESSAGE);
            return new Response(request.getContextPath() + Constants.SUCCESS_PAGE, Response.ResponseType.REDIRECT);
        } catch (LogicException e) {
            session.setAttribute(Constants.SESSION_ERROR_ATTRIBUTE, Constants.CREATE_RECEIPT_ERROR);
            return new Response(Constants.ERROR_JSP, Response.ResponseType.FORWARD);
        }

    }

}
