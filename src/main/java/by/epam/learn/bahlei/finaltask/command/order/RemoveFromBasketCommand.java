package by.epam.learn.bahlei.finaltask.command.order;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.factory.LogicFactory;
import by.epam.learn.bahlei.finaltask.logic.order.OrderLogic;
import by.epam.learn.bahlei.finaltask.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RemoveFromBasketCommand implements ActionCommand {

    private LogicFactory logicFactory = LogicFactory.getInstance();
    private OrderLogic orderLogic = logicFactory.getOrderLogic();

    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();

        int basketId = (int) session.getAttribute(Constants.SESSION_BASKET_ID);
        int serviceId = Integer.parseInt(request.getParameter(Constants.PARAMETER_SERVICE_ID));

        try {
            orderLogic.removeServiceFromBasket(basketId, serviceId);
            String lastUrl = request.getHeader(Constants.REFERER);
            return new Response(lastUrl, Response.ResponseType.REDIRECT);
        } catch (LogicException e) {
            return new Response(Constants.ERROR_JSP, Response.ResponseType.REDIRECT);
        }
    }
}
