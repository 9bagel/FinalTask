package by.epam.learn.bahlei.finaltask.command.order;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.entity.order.Order;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.factory.LogicFactory;
import by.epam.learn.bahlei.finaltask.logic.order.OrderLogic;
import by.epam.learn.bahlei.finaltask.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ManageOrdersCommand implements ActionCommand {
    private OrderLogic orderLogic = LogicFactory.getOrderLogic();

    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        try {
            List<Order> orders = orderLogic.getAllOrders();
            request.setAttribute(Constants.ATTRIBUTE_ORDERS, orders);
            return new Response(Constants.MANAGE_ORDERS_JSP, Response.ResponseType.FORWARD);
        } catch (LogicException e) {
            session.setAttribute(Constants.SESSION_ERROR_ATTRIBUTE, Constants.MANAGE_ORDERS_ERROR);
            return new Response(Constants.ERROR_JSP, Response.ResponseType.FORWARD);
        }
    }
}
