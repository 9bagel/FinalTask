package by.epam.learn.bahlei.finaltask.logic.order;

import by.epam.learn.bahlei.finaltask.connectionpool.ConnectionPool;
import by.epam.learn.bahlei.finaltask.connectionpool.ProxyConnection;
import by.epam.learn.bahlei.finaltask.connectionpool.exception.ConnectionPoolException;
import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.dao.factory.DaoFactory;
import by.epam.learn.bahlei.finaltask.dao.order.OrderDao;
import by.epam.learn.bahlei.finaltask.dao.service.ServiceDao;
import by.epam.learn.bahlei.finaltask.entity.order.Order;
import by.epam.learn.bahlei.finaltask.entity.order.OrderStatus;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.exception.OrderException;
import by.epam.learn.bahlei.finaltask.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Collections;
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

    public void createOrder(List<Integer> serviceIds, Order order) throws LogicException {
        ProxyConnection connection = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);

            orderDao.insert(connection, order);
            orderDao.addServicesFromShoppingCart(connection, serviceIds, order.getId());

            connection.commit();
            connection.setAutoCommit(true);
        } catch (ConnectionPoolException | SQLException | DaoException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw LOGGER.throwing(new LogicException(ex));
                }
            }
            throw LOGGER.throwing(new LogicException(e));
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<Order> getOrdersByUserId(int userId) throws LogicException {
        try {
            List<Order> orders = orderDao.getOrdersByUserId(userId);
            if (orders.isEmpty()) {
                throw LOGGER.throwing(new LogicException());
            }
            Collections.reverse(orders);
            return orders;
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException(e));
        }
    }

    public Order getOrderById(int orderId) throws OrderException, LogicException {
        try {
            Optional<Order> optionalOrder = orderDao.getOrderById(orderId)
                    .stream()
                    .findFirst();
            if (!optionalOrder.isPresent()) {
                throw LOGGER.throwing(new OrderException());
            }
            return optionalOrder.get();
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException(e));
        }
    }

    public void cancelOrder(int userId, int orderId) throws OrderException, LogicException {
        try {
            Optional<Order> optionalOrder = orderDao.getOrderByUserIdAndOrderId(userId, orderId)
                    .stream()
                    .findFirst();
            if (!optionalOrder.isPresent()) {
                throw LOGGER.throwing(new OrderException(Constants.ORDER_NOT_FOUND));
            }
            orderDao.updateStatus(orderId, OrderStatus.CANCELED.getId());
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException(e));
        }
    }
}
