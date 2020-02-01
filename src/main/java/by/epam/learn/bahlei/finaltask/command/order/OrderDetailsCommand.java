package by.epam.learn.bahlei.finaltask.command.order;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.entity.order.Order;
import by.epam.learn.bahlei.finaltask.entity.service.Service;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.exception.OrderException;
import by.epam.learn.bahlei.finaltask.logic.factory.LogicFactory;
import by.epam.learn.bahlei.finaltask.logic.order.OrderLogic;
import by.epam.learn.bahlei.finaltask.logic.service.ServiceLogic;
import by.epam.learn.bahlei.finaltask.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OrderDetailsCommand implements ActionCommand {
    private LogicFactory logicFactory = LogicFactory.getInstance();
    private ServiceLogic serviceLogic = logicFactory.getServiceLogic();
    private OrderLogic orderLogic = logicFactory.getOrderLogic();

    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        int orderId = Integer.parseInt(request.getParameter(Constants.ORDER_ID));
        String language = String.valueOf(session.getAttribute(Constants.LOCALE));
        try {
            List<Service> services = serviceLogic.getOrderedServicesByOrderId(orderId, language);
            Order order = orderLogic.getOrderById(orderId);
            request.setAttribute(Constants.ATTRIBUTE_SERVICES, services);
            request.setAttribute(Constants.ORDER, order);
            return new Response(Constants.DETAILS_JSP, Response.ResponseType.FORWARD);
        } catch (LogicException e) {
            return new Response(Constants.ERROR_JSP, Response.ResponseType.FORWARD);
        } catch (OrderException e) {
            session.setAttribute(Constants.SESSION_ERROR_ATTRIBUTE, Constants.ORDER_NOT_FOUND);
            return new Response(Constants.ERROR_JSP, Response.ResponseType.FORWARD);
        }
    }
}
