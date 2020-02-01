package by.epam.learn.bahlei.finaltask.command.shoppingcart;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.model.ShoppingCart;
import by.epam.learn.bahlei.finaltask.util.Constants;
import by.epam.learn.bahlei.finaltask.util.shoppingcart.ShoppingCartUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RemoveServiceFromShoppingCartCommand implements ActionCommand {
    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        ShoppingCart shoppingCart = ShoppingCartUtil.getShoppingCart(session);

        shoppingCart.removeService(Integer.valueOf(request.getParameter(Constants.SERVICE_ID)));
        session.setAttribute(Constants.SHOPPING_CART, shoppingCart);
        return new Response(request.getHeader(Constants.REFERER), Response.ResponseType.REDIRECT);
    }
}
