package by.epam.learn.bahlei.finaltask.command.order;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.entity.order.Order;
import by.epam.learn.bahlei.finaltask.entity.user.User;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.factory.LogicFactory;
import by.epam.learn.bahlei.finaltask.logic.order.OrderLogic;
import by.epam.learn.bahlei.finaltask.util.Constants;
import by.epam.learn.bahlei.finaltask.util.requestutil.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowOrdersCommand implements ActionCommand {
    private OrderLogic orderLogic = LogicFactory.getOrderLogic();

    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        try {
            User user = RequestUtil.getUser(request.getSession());
            List<Order> orders = orderLogic.getAllOrdersByUserId(user.getId());

            request.setAttribute(Constants.ATTRIBUTE_ORDERS, orders);
            return new Response(Constants.ORDERS_JSP, Response.ResponseType.FORWARD);
        } catch (LogicException e) {
            return new Response(Constants.ERROR_JSP, Response.ResponseType.FORWARD);
        }
    }
}
