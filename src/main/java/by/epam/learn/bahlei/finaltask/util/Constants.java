package by.epam.learn.bahlei.finaltask.util;

public class Constants {
    //Data base ================================================
    public static final String DATABASE_PROPERTIES = "database";
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "db.url";
    public static final String DB_PASSWORD = "db.password";
    public static final String DB_USER = "db.user";
    public static final String DB_POOL = "db.poolSize";

    public static final String COMMAND = "command";

    public static final String SHOPPING_CART = "shopping_cart";
    public static final String USER = "user";
    public static final String ID = "id";
    public static final String USER_ROLE_ID = "role_id";
    public static final String USER_BALANCE = "balance";
    public static final String USER_ID = "user_id";
    public static final String STATUS_ID = "status_id";
    public static final String DATE = "date";
    public static final String AMOUNT = "amount";

    public static final String SESSION_ERROR_ATTRIBUTE = "errorMessage";
    public static final String SESSION_SUCCESS_ATTRIBUTE = "successMessage";
    public static final String SESSION_BASKET_ID = "basket_id";
    public static final String YOU_NEED_TO_LOGIN_MESSAGE = "text.login.message";
    public static final String SERVICE_NOT_FOUND_MESSAGE = "text.service.not_found";
    public static final String SERVICE_ADDED_TO_SHOPPING_CART = "text.shopping_cart.add.success";
    public static final String CREATE_ORDER_ERROR = "text.order.create.error";
    public static final String EMPTY_CART_ERROR = "text.empty_cart.error";
    public static final String USER_NOT_FOUND_ERROR = "text.user.not_found";
    public static final String ORDER_NOT_FOUND = "text.order.not_found";
    public static final String REFILL_ERROR = "text.refill_error";
    public static final String ORDER_CREATED_MESSAGE = "text.order.success";

    public static final String SUCCESS_MESSAGE = "successMessage";
    public static final String ATTRIBUTE_SERVICES = "services";
    public static final String ATTRIBUTE_ORDERS = "orders";
    public static final String ATTRIBUTE_USERS = "users";
    public static final String TOTAL = "total";
    public static final String ORDER_ID = "order_id";

    public static final String LOGIN_JSP = "/WEB-INF/pages/user/login.jsp";
    public static final String BALANCE_JSP = "/WEB-INF/pages/user/balance.jsp";
    public static final String REGISTRATION_JSP = "/WEB-INF/pages/user/registration.jsp";
    public static final String MANAGE_ORDERS_JSP = "/WEB-INF/pages/admin/manageOrders.jsp";
    public static final String MANAGE_USERS_JSP = "/WEB-INF/pages/admin/manageUsers.jsp";
    public static final String MAIN_JSP = "/WEB-INF/pages/main.jsp";
    public static final String SERVICE_JSP = "/WEB-INF/pages/service.jsp";
    public static final String ERROR_JSP = "/WEB-INF/pages/errors/errorPage.jsp";
    public static final String SUCCESS_JSP = "/WEB-INF/pages/success.jsp";
    public static final String SHOPPING_CART_JSP = "/WEB-INF/pages/shoppingCart/shoppingCart.jsp";
    public static final String ORDERS_JSP = "/WEB-INF/pages/order/orders.jsp";
    public static final String DETAILS_JSP = "/WEB-INF/pages/order/orderDetails.jsp";


    public static final String LOGIN_PAGE = "/controller/login";
    public static final String SUCCESS_PAGE = "/controller/success";

    public static final String ERROR_LOGIN = "error.login";

    public static final String LOCALE = "lang";
    public static final String REFERER = "referer";


    public static final String PRICE_COLUMN_NAME = "price";
    public static final String ID_COLUMN_NAME = "id";

    //Services ================================================
    public static final String TITLE_COLUMN_NAME_EN = "title_en";
    public static final String TITLE_COLUMN_NAME_RU = "title_ru";
    public static final String TITLE_COLUMN_NAME_BY = "title_by";
    public static final String DESCRIPTION_COLUMN_NAME_EN = "description_en";
    public static final String DESCRIPTION_COLUMN_NAME_RU = "description_ru";
    public static final String DESCRIPTION_COLUMN_NAME_BY = "description_by";
    public static final String TYPE_ID = "type_id";

    public static final String USER_LOGIN = "login";
    public static final String USER_Password = "password";
    public static final String USER_EMAIL = "email";

    public static final String SERVICE_ID = "service_id";
    public static final String ORDER = "order";

    public static final String ORDER_STATUS_NEW = "text.order.status.new";
    public static final String ORDER_STATUS_IN_PROGRESS = "text.order.status.in_progress";
    public static final String ORDER_STATUS_COMPLETED = "text.order.status.completed";
    public static final String ORDER_STATUS_CANCELED = "text.order.status.canceled";
    public static final String ORDER_STATUS_PAID = "text.order.status.paid";
    public static final String CANCEL_ORDER_ERROR = "text.order.cancel.error";
    public static final String ORDER_CANCELED_MESSAGE = "text.order.cancelled.message";
    public static final String USER_NOT_ENOUGH_BALANCE = "text.user.not_enough_balance.error";
    public static final String PAY_ORDER_ERROR = "text.order.pay.error";
    public static final String ORDER_PAYED_MESSAGE = "text.order.payed.message";
    public static final String ORDER_UPDATE_ERROR = "text.order.update.error";
    public static final String ORDER_UPDATE_MESSAGE = "text.order.update.message";
    public static final String USER_ROLE_ADMINISTRATOR = "text.user.type.administrator";
    public static final String USER_ROLE_CUSTOMER = "text.user.type.customer";
    public static final String MANAGE_ORDERS_ERROR = "text.order.manage.error";
    public static final String UPDATE_USER_ERROR = "text.user.update.error";
    public static final String USER_UPDATE_MESSAGE = "text.user.update.message";
}
