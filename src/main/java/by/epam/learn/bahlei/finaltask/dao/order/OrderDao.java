package by.epam.learn.bahlei.finaltask.dao.order;

import by.epam.learn.bahlei.finaltask.connectionpool.ProxyConnection;
import by.epam.learn.bahlei.finaltask.connectionpool.exception.ConnectionPoolException;
import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.entity.order.Order;
import by.epam.learn.bahlei.finaltask.entity.order.OrderStatus;
import by.epam.learn.bahlei.finaltask.logic.exception.OrderException;
import by.epam.learn.bahlei.finaltask.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends OrderDaoAbstract {
    private static final Logger LOGGER = LogManager.getLogger(OrderDao.class);

    @Override
    protected List<Order> parseResultSet(ResultSet resultSet) throws DaoException {
        List<Order> orders = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Order order = new Order();

                order.setUserId(resultSet.getInt(Constants.USER_ID));
                order.setId(resultSet.getInt(Constants.ID));
                order.setOrderStatus(OrderStatus.getOrderStatusById(resultSet.getInt(Constants.STATUS_ID)));
                order.setTotal(resultSet.getBigDecimal(Constants.TOTAL));
                order.setDate(resultSet.getTimestamp(Constants.DATE));

                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            throw new DaoException("Exception in parseResultSet");
        } catch (OrderException e) {
            throw LOGGER.throwing(new DaoException(e));
        }
    }

    @Override
    protected void prepareInsert(PreparedStatement preparedStatement, Order order) throws DaoException {
        try {
            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setInt(2, order.getOrderStatus().getId());
            preparedStatement.setBigDecimal(3, order.getTotal());
            preparedStatement.setTimestamp(4, order.getDate());

        } catch (SQLException e) {
            throw LOGGER.throwing(new DaoException("Exception in prepareInsert in OrderDao", e));
        }
    }

    @Override
    protected void prepareUpdate(PreparedStatement preparedStatement, Order order) throws DaoException {
        try {
            preparedStatement.setInt(1, order.getOrderStatus().getId());
            preparedStatement.setBigDecimal(2, order.getTotal());
            preparedStatement.setTimestamp(3, order.getDate());
            preparedStatement.setInt(4, order.getId());

        } catch (SQLException e) {
            throw LOGGER.throwing(new DaoException("Exception in prepareUpdate in OrderDao", e));
        }
    }

    @Override
    public void insert(Order order) throws DaoException {
        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery(), Statement.RETURN_GENERATED_KEYS)) {
            prepareInsert(preparedStatement, order);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating order failed, no rows affected.");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    order.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw LOGGER.throwing(new DaoException("Error in insert() in OrderDao", e));

        }
    }

    public void insert(ProxyConnection connection, Order order) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery(), Statement.RETURN_GENERATED_KEYS)) {
            prepareInsert(preparedStatement, order);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating order failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    order.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw LOGGER.throwing(new DaoException("Error in insert() in OrderDao", e));

        }
    }

    public void updateStatus(ProxyConnection connection, int order_id, OrderStatus orderStatus) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(getUpdateStatusQuery())) {
            preparedStatement.setInt(1, orderStatus.getId());
            preparedStatement.setInt(2, order_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw LOGGER.throwing(new DaoException("Error in updateStatus()", e));

        }
    }

    public void updateStatus(int orderId, int statusId) throws DaoException {
        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getUpdateStatusQuery())) {
            preparedStatement.setInt(1, statusId);
            preparedStatement.setInt(2, orderId);

            if (preparedStatement.executeUpdate() == 0) {
                throw LOGGER.throwing(new DaoException("No rows were affected"));
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw LOGGER.throwing(new DaoException("Error in updateStatus()", e));
        }
    }

    public void addServicesFromShoppingCart(ProxyConnection connection, List<Integer> serviceIds, int orderId) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(getAddOrderedServiceQuery())) {
            for (Integer serviceId : serviceIds) {
                preparedStatement.setInt(1, orderId);
                preparedStatement.setInt(2, serviceId);
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw LOGGER.throwing(new DaoException("Error in insert() in OrderDao", e));

        }
    }

    public List<Order> getOrdersByUserId(int userId) throws DaoException {
        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getOrderByUserIdQuery())) {
            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return parseResultSet(resultSet);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw LOGGER.throwing(new DaoException("Error in getOrdersByUserId()", e));
        }

    }

    public List<Order> getOrderById(int orderId) throws DaoException {
        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getOrderByIdQuery())) {
            preparedStatement.setInt(1, orderId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return parseResultSet(resultSet);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw LOGGER.throwing(new DaoException("Error in getOrderById() in OrderDao", e));
        }

    }

    public List<Order> getOrderByUserIdAndOrderId(int userId, int orderId) throws DaoException {
        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getOrderByUserIdAndOrderIdQuery())) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, orderId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return parseResultSet(resultSet);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw LOGGER.throwing(new DaoException("Error in getOrderByUserIdAndOrderId() in OrderDao", e));
        }
    }
}
