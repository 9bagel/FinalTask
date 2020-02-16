package by.epam.learn.bahlei.finaltask.logic.order;

import by.epam.learn.bahlei.finaltask.connectionpool.ConnectionPool;
import by.epam.learn.bahlei.finaltask.connectionpool.ProxyConnection;
import by.epam.learn.bahlei.finaltask.connectionpool.exception.ConnectionPoolException;
import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.dao.order.OrderDao;
import by.epam.learn.bahlei.finaltask.dao.user.UserDao;
import by.epam.learn.bahlei.finaltask.entity.order.Order;
import by.epam.learn.bahlei.finaltask.entity.order.OrderStatus;
import by.epam.learn.bahlei.finaltask.entity.user.User;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.exception.OrderException;
import by.epam.learn.bahlei.finaltask.logic.exception.UserException;
import by.epam.learn.bahlei.finaltask.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class OrderLogic {
    private static final Logger LOGGER = LogManager.getLogger(OrderLogic.class);
    private final OrderDao orderDao;
    private final UserDao userDao;

    public OrderLogic(OrderDao orderDao, UserDao userDao) {
        this.orderDao = orderDao;
        this.userDao = userDao;
    }

    public void createOrder(List<Integer> serviceList, Order order) throws LogicException {
        ProxyConnection connection = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);

            orderDao.insert(connection, order);
            orderDao.addServicesFromShoppingCart(connection, serviceList, order.getId());

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

    public List<Order> getAllOrdersByUserId(int userId) throws LogicException {
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
            getOrderByUserIdAndOrderId(userId, orderId);
            orderDao.updateStatus(orderId, OrderStatus.CANCELED.getId());
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException(e));
        }
    }

    public void payOrder(int orderId, User user) throws UserException, OrderException, LogicException {
        ProxyConnection connection = null;

        try {
            Order order = getOrderByUserIdAndOrderId(user.getId(), orderId);
            checkIfUserHasBalanceToPay(user, order);

            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);

            userDao.subtractBalance(connection, user.getId(), order.getTotal());
            orderDao.updateStatus(connection, orderId, OrderStatus.PAID);
            user.setBalance(user.getBalance().subtract(order.getTotal()));

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

    private Order getOrderByUserIdAndOrderId(int userId, int orderId) throws OrderException, DaoException {
        Optional<Order> optionalOrder = orderDao.getOrderByUserIdAndOrderId(userId, orderId)
                .stream()
                .findFirst();
        if (!optionalOrder.isPresent()) {
            throw LOGGER.throwing(new OrderException(Constants.ORDER_NOT_FOUND));
        }
        return optionalOrder.get();
    }

    private void checkIfUserHasBalanceToPay(User user, Order order) throws UserException {
        if (user.getBalance().compareTo(order.getTotal()) < 0) {
            throw LOGGER.throwing(new UserException(Constants.USER_NOT_ENOUGH_BALANCE));
        }
    }

    public List<Order> getAllOrders() throws LogicException {
        try {
            List<Order> orders = orderDao.getAll();
            Collections.reverse(orders);
            return orders;
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException(e));
        }
    }

    public void updateOrder(Order order) throws LogicException {
        try {
            orderDao.update(order);
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException(e));
        }
    }
}
