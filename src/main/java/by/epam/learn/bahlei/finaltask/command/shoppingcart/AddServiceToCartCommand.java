package by.epam.learn.bahlei.finaltask.command.shoppingcart;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.factory.LogicFactory;
import by.epam.learn.bahlei.finaltask.logic.service.ServiceLogic;
import by.epam.learn.bahlei.finaltask.model.ShoppingCart;
import by.epam.learn.bahlei.finaltask.util.Constants;
import by.epam.learn.bahlei.finaltask.util.requestutil.RequestUtil;
import com.google.protobuf.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddServiceToCartCommand implements ActionCommand {
    private ServiceLogic serviceLogic = LogicFactory.getServiceLogic();

    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        try {
            int serviceId = Integer.parseInt(request.getParameter(Constants.SERVICE_ID));
            serviceLogic.isServiceExists(serviceId);

            ShoppingCart shoppingCart = RequestUtil.getShoppingCart(session);
            shoppingCart.addServiceId(serviceId);
            session.setAttribute(Constants.SHOPPING_CART, shoppingCart);
            session.setAttribute(Constants.SESSION_SUCCESS_ATTRIBUTE, Constants.SERVICE_ADDED_TO_SHOPPING_CART);
            return new Response(request.getHeader(Constants.REFERER), Response.ResponseType.REDIRECT);
        } catch (ServiceException e) {
            session.setAttribute(Constants.SESSION_ERROR_ATTRIBUTE, Constants.SERVICE_NOT_FOUND_MESSAGE);
            return new Response(Constants.ERROR_JSP, Response.ResponseType.FORWARD);

        } catch (LogicException e) {
            return new Response(Constants.ERROR_JSP, Response.ResponseType.FORWARD);
        }
    }
}
