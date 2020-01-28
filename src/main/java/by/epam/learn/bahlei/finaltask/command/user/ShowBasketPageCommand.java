package by.epam.learn.bahlei.finaltask.command.user;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.entity.service.Service;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.factory.LogicFactory;
import by.epam.learn.bahlei.finaltask.logic.order.OrderLogic;
import by.epam.learn.bahlei.finaltask.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowBasketPageCommand implements ActionCommand {
    private LogicFactory logicFactory = LogicFactory.getInstance();
    private OrderLogic orderLogic = logicFactory.getOrderLogic();

    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        try {
            List<Service> services;
            HttpSession session = request.getSession();

            int userId = (int) session.getAttribute(Constants.ID);
            String language = String.valueOf(session.getAttribute("lang"));

            services = orderLogic.getOrderedServices(userId, language);

            request.setAttribute("services", services);
            return new Response(Constants.BASKET_JSP, Response.ResponseType.FORWARD);
        } catch (LogicException e) {
            return new Response(Constants.ERROR_JSP, Response.ResponseType.REDIRECT);
        }
    }
}
