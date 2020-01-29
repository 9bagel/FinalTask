package by.epam.learn.bahlei.finaltask.dao.factory;

import by.epam.learn.bahlei.finaltask.dao.order.OrderDao;
import by.epam.learn.bahlei.finaltask.dao.receipt.ReceiptDao;
import by.epam.learn.bahlei.finaltask.dao.service.ServiceDao;
import by.epam.learn.bahlei.finaltask.dao.user.UserDao;

public class DaoFactory {
    private static final DaoFactory INSTANCE = new DaoFactory();

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return INSTANCE;
    }

    public UserDao getUserDao(){
        return UserDao.getInstance();
    }

    public ServiceDao getServiceDao(){
        return ServiceDao.getInstance();
    }

    public OrderDao getOrderDao() {
        return OrderDao.getInstance();
    }

    public ReceiptDao getReceiptDao() {
        return ReceiptDao.getInstance();
    }
}
