package by.epam.learn.bahlei.finaltask.util;

public class Constants {
    //Data base ================================================
    public static final String DATABASE_PROPERTIES = "database";
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "db.url";
    public static final String DB_PASSWORD = "db.password";
    public static final String DB_USER = "db.user";
    public static final String DB_POOL = "db.poolSize";

    public static final String SESSION_USER_LOGIN = "login";
    public static final String ID = "id";
    public static final String USER_ROLE_ID = "role_id";
    public static final String USER_BALANCE = "balance";
    public static final String USER_ID = "user_id";
    public static final String STATUS_ID = "status_id";
    public static final String DATE = "date";
    public static final String AMOUNT = "amount";

    public static final String SESSION_ERROR_ATTRIBUTE = "errorMessage";
    public static final String SESSION_BASKET_ID = "basket_id";
    public static final String YOU_NEED_TO_LOGIN_MESSAGE = "text.login.message";
    public static final String SERVICE_NOT_FOUND_MESSAGE = "text.service.not_found";
    public static final String SERVICE_ADDED_TO_BASKET = "text.service.success";
    public static final String CREATE_RECEIPT_ERROR = "text.receipt.error";
    public static final String EMPTY_CART_ERROR = "text.empty_cart.error";
    public static final String USER_NOT_FOUND_ERROR = "text.user.not_found";
    public static final String REFILL_ERROR = "text.refill_error";
    public static final String RECEIPT_CREATED_MESSAGE = "text.receipt.success";

    public static final String SUCCESS_MESSAGE = "successMessage";
    public static final String ATTRIBUTE_SERVICES = "services";
    public static final String TOTAL = "total";

    public static final String LOGIN_JSP = "/WEB-INF/pages/login.jsp";
    public static final String BALANCE_JSP = "/WEB-INF/pages/balance.jsp";
    public static final String REGISTRATION_JSP = "/WEB-INF/pages/registration.jsp";
    public static final String MAIN_JSP = "/WEB-INF/pages/main.jsp";
    public static final String SERVICE_JSP = "/WEB-INF/pages/service.jsp";
    public static final String ERROR_JSP = "/WEB-INF/pages/errors/errorPage.jsp";
    public static final String SUCCESS_JSP = "/WEB-INF/pages/success.jsp";
    public static final String BASKET_JSP = "/WEB-INF/pages/basket.jsp";
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

    public static final String PARAMETER_SERVICE_ID = "service_id";
}
