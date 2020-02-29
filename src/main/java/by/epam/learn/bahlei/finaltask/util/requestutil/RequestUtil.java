package by.epam.learn.bahlei.finaltask.util.requestutil;

import by.epam.learn.bahlei.finaltask.dto.RegistrationDto;
import by.epam.learn.bahlei.finaltask.entity.order.Order;
import by.epam.learn.bahlei.finaltask.entity.order.OrderStatus;
import by.epam.learn.bahlei.finaltask.entity.review.Review;
import by.epam.learn.bahlei.finaltask.entity.service.Service;
import by.epam.learn.bahlei.finaltask.entity.service.ServiceType;
import by.epam.learn.bahlei.finaltask.entity.user.User;
import by.epam.learn.bahlei.finaltask.entity.user.UserRole;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.exception.OrderException;
import by.epam.learn.bahlei.finaltask.model.ShoppingCart;
import by.epam.learn.bahlei.finaltask.util.Constants;
import by.epam.learn.bahlei.finaltask.util.XssCleaner;
import com.google.protobuf.ServiceException;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class that provides methods to parse data from request
 */
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

    public static User getUser(HttpServletRequest request) throws LogicException {
        User user = (User) request.getSession().getAttribute(Constants.USER);

        if (user == null) {
            throw new LogicException();
        }
        return user;
    }

    public static Order parseOrder(HttpServletRequest request) throws LogicException, OrderException {
        Order order = new Order();

        order.setId(parseOrderId(request));
        order.setUserId(getUser(request).getId());
        order.setTotal(parseBigDecimal(request, Constants.TOTAL));
        order.setOrderStatus(getOrderStatus(request));
        order.setDate(getDate(request));

        return order;
    }

    public static Order parseNewOrder(HttpServletRequest request) throws LogicException {
        Order order = new Order();

        order.setUserId(getUser(request).getId());
        order.setTotal(parseBigDecimal(request, Constants.TOTAL));
        order.setOrderStatus(OrderStatus.NEW);
        order.setDate(getDate(request));

        return order;
    }

    private static OrderStatus getOrderStatus(HttpServletRequest request) throws LogicException, OrderException {
        return OrderStatus.getOrderStatusById(parseInteger(request, Constants.STATUS_ID));
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

    public static User parseUserData(HttpServletRequest request) throws LogicException {
        User user = new User();

        user.setId(parseInteger(request, Constants.USER_ID));
        user.setLogin(XssCleaner.clean(request.getParameter(Constants.LOGIN)));
        user.setEmail(XssCleaner.clean(request.getParameter(Constants.EMAIL)));
        user.setUserRole(UserRole.getUserRoleById(parseInteger(request, Constants.USER_ROLE_ID)));
        user.setBalance(parseBigDecimal(request, Constants.USER_BALANCE));

        return user;
    }

    public static Service parseService(HttpServletRequest request) throws LogicException, ServiceException {
        Service service = new Service();

        service.setTitleEn(XssCleaner.clean(request.getParameter(Constants.TITLE_EN)));
        service.setTitleRu(XssCleaner.clean(request.getParameter(Constants.TITLE_RU)));
        service.setTitleBy(XssCleaner.clean(request.getParameter(Constants.TITLE_BY)));

        service.setDescriptionEn(XssCleaner.clean(request.getParameter(Constants.DESCRIPTION_EN)));
        service.setDescriptionRu(XssCleaner.clean(request.getParameter(Constants.DESCRIPTION_RU)));
        service.setDescriptionBy(XssCleaner.clean(request.getParameter(Constants.DESCRIPTION_BY)));

        service.setPrice(parseBigDecimal(request, Constants.PRICE));
        service.setServiceType(ServiceType.getById(parseServiceTypeId(request)));

        if (request.getParameter(Constants.SERVICE_ID) == null) {
            service.setId(0);
        } else {
            service.setId(parseInteger(request, Constants.SERVICE_ID));
        }
        return service;
    }

    public static Review parseReview(HttpServletRequest request) throws LogicException {
        Review review = new Review();

        review.setOrderId(parseOrderId(request));
        review.setUserId(getUser(request).getId());
        review.setMessage(XssCleaner.clean(request.getParameter(Constants.MESSAGE)));

        return review;
    }

    public static RegistrationDto parseRegistrationDto(HttpServletRequest request) {
        RegistrationDto registrationDto = new RegistrationDto();

        registrationDto.setLogin(XssCleaner.clean(request.getParameter(Constants.LOGIN)));
        registrationDto.setPassword(XssCleaner.clean(request.getParameter(Constants.Password)));
        registrationDto.setEmail(XssCleaner.clean(request.getParameter(Constants.EMAIL)));
        registrationDto.setRepeatPassword(XssCleaner.clean(request.getParameter(Constants.PASSWORD_REPEAT)));

        return registrationDto;
    }

    public static int parseServiceId(HttpServletRequest request) throws LogicException {
        return parseInteger(request, Constants.SERVICE_ID);
    }

    public static int parseServiceTypeId(HttpServletRequest request) throws LogicException {
        return parseInteger(request, Constants.SERVICE_TYPE_ID);
    }

    public static int parseOrderId(HttpServletRequest request) throws LogicException {
        return parseInteger(request, Constants.ORDER_ID);
    }

    public static int parseInteger(HttpServletRequest request, String parameterName) throws LogicException {
        String stringParameterValue = request.getParameter(parameterName);

        if (!NumberUtils.isParsable(stringParameterValue)) {
            throw new LogicException();
        }
        return Integer.parseInt(stringParameterValue);
    }

    public static BigDecimal parseBigDecimal(HttpServletRequest request, String parameterName) throws LogicException {
        String stringParameterValue = request.getParameter(parameterName);

        if (!NumberUtils.isParsable(stringParameterValue)) {
            throw new LogicException();
        }
        return new BigDecimal(stringParameterValue);
    }

    public static int getLimit(HttpServletRequest request) {
        String stringLimit = request.getParameter(Constants.LIMIT);

        if (!NumberUtils.isParsable(stringLimit)) {
            return Constants.DEFAULT_NUMBER_VALUE;
        }
        return Integer.parseInt(stringLimit);
    }

    public static int getPage(HttpServletRequest request) {
        String stringPage = request.getParameter(Constants.PAGE);

        if (!NumberUtils.isParsable(stringPage)) {
            return Constants.DEFAULT_PAGE_NUMBER;
        }
        return Integer.parseInt(stringPage);
    }

    public static ServiceType getServiceType(HttpServletRequest request) throws ServiceException {
        String serviceTypeName = XssCleaner.clean(request.getParameter(Constants.SERVICE_TYPE));
        return ServiceType.getByName(serviceTypeName.toUpperCase());
    }
}