package by.epam.learn.bahlei.finaltask.logic.order;

import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.dao.factory.DaoFactory;
import by.epam.learn.bahlei.finaltask.dao.order.OrderDao;
import by.epam.learn.bahlei.finaltask.dao.service.ServiceDao;
import by.epam.learn.bahlei.finaltask.dto.LanguageTypeDto;
import by.epam.learn.bahlei.finaltask.entity.order.Order;
import by.epam.learn.bahlei.finaltask.entity.order.OrderStatus;
import by.epam.learn.bahlei.finaltask.entity.service.Service;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.util.Constants;
import by.epam.learn.bahlei.finaltask.util.LanguageUtil;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class OrderLogic {
    private static final Logger LOGGER = LogManager.getLogger(OrderLogic.class);
    private static final OrderLogic INSTANCE = new OrderLogic();
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private ServiceDao serviceDao = daoFactory.getServiceDao();
    private OrderDao orderDao = daoFactory.getOrderDao();

    private OrderLogic() {
    }

    public static OrderLogic getInstance() {
        return INSTANCE;
    }

    public void addServiceToOrder(int userId, int serviceId, String language) throws LogicException, ServiceException {
        LanguageTypeDto languageTypeDto = LanguageUtil.getLanguageTypeByName(language);
        Order order;
        try {
            Optional<Service> service = serviceDao.getServiceByIdAndLanguageType(serviceId, languageTypeDto)
                    .stream()
                    .findFirst();
            if (!service.isPresent()) {
                throw LOGGER.throwing(new ServiceException(Constants.SERVICE_NOT_FOUND_MESSAGE));
            } else {
                order = getOrderWithNewStatus(userId);

                orderDao.addOrderedService(order.getId(), serviceId);
                order.addService(service.get());
            }
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException(e));
        }

    }

    public List<Service> getOrderedServices(int userId, String language) throws LogicException {
        LanguageTypeDto languageTypeDto = LanguageUtil.getLanguageTypeByName(language);
        try {
            Order order = getOrderWithNewStatus(userId);
            return serviceDao.getOrderedServices(order.getId(), languageTypeDto);
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException(e));
        }
    }

    private Order getOrderWithNewStatus(int userId) throws DaoException {
        Order order;
        Optional<Order> optionalOrder = orderDao.getOrderWithNewStatus(userId)
                .stream()
                .findFirst();

        if (optionalOrder.isPresent()) {
            order = optionalOrder.get();
        } else {
            order = new Order();
            order.setDate(new Date(System.currentTimeMillis()));
            order.setUserId(userId);
            order.setStatusId(OrderStatus.NEW.getId());
            orderDao.insert(order);
        }
        return order;
    }

    public int getBasketOrderId(int userId) throws LogicException {
        try {
            return getOrderWithNewStatus(userId).getId();
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException(e));
        }
    }

    public void removeServiceFromBasket(int basketId, int serviceId) throws LogicException {
        try {
            orderDao.removeServiceFromBasket(basketId, serviceId);
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException(e));
        }
    }
}
