package by.epam.learn.bahlei.finaltask.dao.order;

import by.epam.learn.bahlei.finaltask.connectionpool.ProxyConnection;
import by.epam.learn.bahlei.finaltask.connectionpool.exception.ConnectionPoolException;
import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.entity.order.Order;
import by.epam.learn.bahlei.finaltask.entity.order.OrderStatus;
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

    private static final OrderDao INSTANCE = new OrderDao();
    private static final Logger LOGGER = LogManager.getLogger(OrderDao.class);

    private OrderDao() {
    }

    public static OrderDao getInstance() {
        return INSTANCE;
    }

    @Override
    protected List<Order> parseResultSet(ResultSet resultSet) throws DaoException {
        List<Order> orders = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Order order = new Order();
                order.setUserId(resultSet.getInt(Constants.USER_ID));
                order.setId(resultSet.getInt(Constants.ID));
                order.setStatusId(resultSet.getInt(Constants.STATUS_ID));
                order.setTotal(resultSet.getBigDecimal(Constants.TOTAL));
                order.setDate(resultSet.getTimestamp(Constants.DATE));
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            throw new DaoException("Exception in parseResultSet");
        }
    }

    @Override
    protected void prepareInsert(PreparedStatement preparedStatement, Order order) throws DaoException {
        try {
            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setInt(2, order.getStatusId());
            preparedStatement.setBigDecimal(3, order.getTotal());
            preparedStatement.setTimestamp(4, order.getDate());

        } catch (SQLException e) {
            throw LOGGER.throwing(new DaoException("Exception in prepareInsert in OrderDao", e));
        }
    }

    @Override
    public void insert(Order order) throws DaoException {
        String insertQuery = getInsertQuery();

        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {

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
        String insertQuery = getInsertQuery();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {

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

    @Override
    protected void prepareUpdate(PreparedStatement preparedStatement, Order entity) throws DaoException {

    }

    protected void prepareUpdate(PreparedStatement preparedStatement, Order entity, int serviceId) throws DaoException {

    }

    public void addOrderedService(int orderId, int serviceId) throws DaoException {
        String addOrderedServiceQuery = getAddOrderedServiceQuery();

        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(addOrderedServiceQuery)) {

            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, serviceId);

            preparedStatement.execute();

        } catch (SQLException | ConnectionPoolException e) {
            throw LOGGER.throwing(new DaoException("Error in addOrderedService()", e));

        }
    }

    public List<Order> getOrderWithNewStatus(int userId) throws DaoException {
        String orderWithNewStatusQuery = getOrderWithNewStatusQuery();

        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(orderWithNewStatusQuery)) {

            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            return parseResultSet(resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw LOGGER.throwing(new DaoException("Error in getOrderIfExists()", e));

        }
    }


    public void removeServiceFromBasket(int basketId, int serviceId) throws DaoException {
        String deleteServiceFromBasketQuery = getDeleteServiceFromBasketQuery();

        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteServiceFromBasketQuery)) {

            preparedStatement.setInt(1, basketId);
            preparedStatement.setInt(2, serviceId);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw LOGGER.throwing(new DaoException("Error in removeServiceFromBasket()", e));

        }
    }

    public void updateStatus(ProxyConnection connection, int order_id, OrderStatus orderStatus) throws DaoException {
        String updateStatusQuery = getUpdateStatusQuery();
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateStatusQuery)) {

            preparedStatement.setInt(1, orderStatus.getId());
            preparedStatement.setInt(2, order_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw LOGGER.throwing(new DaoException("Error in updateStatus()", e));

        }
    }

    public void createOrder(List<Integer> serviceIds, int userId) {

    }

    public void addServicesFromShoppingCart(ProxyConnection connection, List<Integer> serviceIds, int orderId) throws DaoException {
        String addOrderedServiceQuery = getAddOrderedServiceQuery();
        try (PreparedStatement preparedStatement = connection.prepareStatement(addOrderedServiceQuery)) {

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
        String getOrderByUserIdQuery = getOrderByUserIdQuery();

        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getOrderByUserIdQuery)) {
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            return parseResultSet(resultSet);

        } catch (ConnectionPoolException | SQLException e) {
            throw LOGGER.throwing(new DaoException("Error in getOrdersByUserId()", e));
        }

    }

    public List<Order> getOrderById(int orderId) throws DaoException {
        String getOrderByIdQuery = getOrderByIdQuery();

        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getOrderByIdQuery)) {
            preparedStatement.setInt(1, orderId);

            ResultSet resultSet = preparedStatement.executeQuery();

            return parseResultSet(resultSet);

        } catch (ConnectionPoolException | SQLException e) {
            throw LOGGER.throwing(new DaoException("Error in getOrderById() in OrderDao", e));
        }

    }
}
