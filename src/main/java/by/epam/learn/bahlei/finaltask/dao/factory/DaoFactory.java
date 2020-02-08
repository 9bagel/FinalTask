package by.epam.learn.bahlei.finaltask.dao.factory;

import by.epam.learn.bahlei.finaltask.dao.order.OrderDao;
import by.epam.learn.bahlei.finaltask.dao.review.ReviewDao;
import by.epam.learn.bahlei.finaltask.dao.service.ServiceDao;
import by.epam.learn.bahlei.finaltask.dao.user.UserDao;

public class DaoFactory {
    private static ServiceDao serviceDao;
    private static OrderDao orderDao;
    private static ReviewDao reviewDao;
    private static UserDao userDao;

    public static UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDao();
        }
        return userDao;
    }

    public static ServiceDao getServiceDao() {
        if (serviceDao == null) {
            serviceDao = new ServiceDao();
        }
        return serviceDao;
    }

    public static OrderDao getOrderDao() {
        if (orderDao == null) {
            orderDao = new OrderDao();
        }
        return orderDao;
    }

    public static ReviewDao getReviewDao() {
        if (reviewDao == null) {
            reviewDao = new ReviewDao();
        }
        return reviewDao;
    }

}