package by.epam.learn.bahlei.finaltask.command.order;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.entity.order.Order;
import by.epam.learn.bahlei.finaltask.entity.review.Review;
import by.epam.learn.bahlei.finaltask.entity.service.Service;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.exception.OrderException;
import by.epam.learn.bahlei.finaltask.logic.factory.LogicFactory;
import by.epam.learn.bahlei.finaltask.logic.order.OrderLogic;
import by.epam.learn.bahlei.finaltask.logic.review.ReviewLogic;
import by.epam.learn.bahlei.finaltask.logic.service.ServiceLogic;
import by.epam.learn.bahlei.finaltask.util.Constants;
import by.epam.learn.bahlei.finaltask.util.requestutil.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OrderDetailsCommand implements ActionCommand {
    private ServiceLogic serviceLogic = LogicFactory.getServiceLogic();
    private OrderLogic orderLogic = LogicFactory.getOrderLogic();
    private ReviewLogic reviewLogic = LogicFactory.getReviewLogic();

    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        try {
            int orderId = RequestUtil.parseOrderId(request);
            List<Service> services = serviceLogic.getOrderedServicesByOrderId(orderId);
            Order order = orderLogic.getOrderById(orderId);
            Review review = reviewLogic.getReviewByOrderId(orderId);

            request.setAttribute(Constants.SERVICES, services);
            request.setAttribute(Constants.ORDER, order);
            request.setAttribute(Constants.REVIEW, review);
            return new Response(Constants.DETAILS_JSP, Response.ResponseType.FORWARD);
        } catch (LogicException e) {
            return new Response(Constants.ERROR_JSP, Response.ResponseType.FORWARD);
        } catch (OrderException e) {
            session.setAttribute(Constants.SESSION_ERROR_ATTRIBUTE, Constants.ORDER_NOT_FOUND);
            return new Response(Constants.ERROR_JSP, Response.ResponseType.FORWARD);
        }
    }
}