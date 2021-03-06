package by.epam.learn.bahlei.finaltask.command.shoppingcart;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.entity.service.Service;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.factory.LogicFactory;
import by.epam.learn.bahlei.finaltask.logic.service.ServiceLogic;
import by.epam.learn.bahlei.finaltask.model.ShoppingCart;
import by.epam.learn.bahlei.finaltask.util.Constants;
import by.epam.learn.bahlei.finaltask.util.requestutil.RequestUtil;
import com.google.protobuf.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowShoppingCartCommand implements ActionCommand {
    private final ServiceLogic serviceLogic = LogicFactory.getServiceLogic();

    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        List<Service> services;
        HttpSession session = request.getSession();
        try {
            ShoppingCart shoppingCart = RequestUtil.getShoppingCart(session);
            List<Integer> serviceIds = shoppingCart.getServiceList();
            services = serviceLogic.getServiceListById(serviceIds);

            request.setAttribute(Constants.SERVICES, services);
            return new Response(Constants.SHOPPING_CART_JSP, Response.ResponseType.FORWARD);
        } catch (LogicException e) {
            return new Response(Constants.ERROR_JSP, Response.ResponseType.FORWARD);
        } catch (ServiceException e) {
            request.getSession().setAttribute(Constants.SESSION_ERROR_ATTRIBUTE, e.getMessage());
            return new Response(Constants.SHOPPING_CART_JSP, Response.ResponseType.FORWARD);
        }
    }
}
