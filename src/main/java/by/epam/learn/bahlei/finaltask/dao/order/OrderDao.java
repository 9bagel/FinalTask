package by.epam.learn.bahlei.finaltask.dao.order;

import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.entity.order.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        return null;
    }

    @Override
    protected void prepareInsert(PreparedStatement preparedStatement, Order order) throws DaoException {
        try {
            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setInt(2, order.getStatusId());
            preparedStatement.setDate(3, order.getDate());
        } catch (SQLException e) {
            throw LOGGER.throwing(new DaoException("Exception in prepareInsert in OrderDao", e));
        }
    }

    @Override
    protected void prepareUpdate(PreparedStatement preparedStatement, Order entity) throws DaoException {

    }

}
