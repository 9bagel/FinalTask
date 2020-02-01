package by.epam.learn.bahlei.finaltask.util.shoppingcart;

import by.epam.learn.bahlei.finaltask.model.ShoppingCart;
import by.epam.learn.bahlei.finaltask.util.Constants;

import javax.servlet.http.HttpSession;

public class ShoppingCartUtil {

    private ShoppingCartUtil() {
    }

    public static ShoppingCart getShoppingCart(HttpSession session) {
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute(Constants.SHOPPING_CART);

        if (shoppingCart == null) {
            return new ShoppingCart();
        }
        return shoppingCart;
    }
}
