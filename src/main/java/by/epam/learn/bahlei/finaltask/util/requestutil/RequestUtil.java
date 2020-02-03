package by.epam.learn.bahlei.finaltask.util.requestutil;

import by.epam.learn.bahlei.finaltask.entity.order.Order;
import by.epam.learn.bahlei.finaltask.entity.order.OrderStatus;
import by.epam.learn.bahlei.finaltask.entity.user.User;
import by.epam.learn.bahlei.finaltask.entity.user.UserRole;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.model.ShoppingCart;
import by.epam.learn.bahlei.finaltask.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RequestUtil {
    private static final String dateFormatPattern = "MM/dd/yyyy hh:mm";
    private static final Logger LOGGER = LogManager.getLogger(RequestUtil.class);

    private RequestUtil() {
    }

    public static ShoppingCart getShoppingCart(HttpSession session) {
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute(Constants.SHOPPING_CART);

        if (shoppingCart == null) {
            return new ShoppingCart();
        }
        return shoppingCart;
    }

    public static void emptyShoppingCart(HttpSession session) {
        session.removeAttribute(Constants.SHOPPING_CART);
    }

    public static User getUser(HttpSession session) {
        return (User) session.getAttribute(Constants.USER);
    }

    public static Order parseOrder(HttpSession session, HttpServletRequest request) throws LogicException {
        Order order = new Order();
        order.setId(getOrderId(request));
        order.setUserId(getUser(session).getId());
        order.setTotal(new BigDecimal(request.getParameter(Constants.TOTAL)));
        order.setOrderStatus(getOrderStatus(request));
        order.setDate(getDate(request));
        return order;
    }

    private static OrderStatus getOrderStatus(HttpServletRequest request) {
        if (request.getParameter(Constants.STATUS_ID) == null) {
            return OrderStatus.NEW;
        }
        return OrderStatus.getOrderStatusById(Integer.parseInt(request.getParameter(Constants.STATUS_ID)));
    }

    private static int getOrderId(HttpServletRequest request) {
        if (request.getParameter(Constants.ORDER_ID) == null) {
            return 0;
        }
        return Integer.parseInt(request.getParameter(Constants.ORDER_ID));
    }

    private static Timestamp getDate(HttpServletRequest request) throws LogicException {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
            Date date = dateFormat.parse(request.getParameter(Constants.DATE));

            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            throw LOGGER.throwing(new LogicException(e));
        }
    }

    public static User parseUser(HttpServletRequest request) {
        User user = new User();
        user.setId(Integer.parseInt(request.getParameter(Constants.USER_ID)));
        user.setLogin(request.getParameter(Constants.USER_LOGIN));
        user.setEmail(request.getParameter(Constants.USER_EMAIL));
        user.setUserRole(UserRole.getUserRoleById(Integer.parseInt(request.getParameter(Constants.USER_ROLE_ID))));
        user.setBalance(new BigDecimal(request.getParameter(Constants.USER_BALANCE)));
        return user;
    }
}
