package by.epam.learn.bahlei.finaltask.dao.receipt;

import by.epam.learn.bahlei.finaltask.connectionpool.ProxyConnection;
import by.epam.learn.bahlei.finaltask.connectionpool.exception.ConnectionPoolException;
import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.entity.receipt.Receipt;
import by.epam.learn.bahlei.finaltask.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReceiptDao extends ReceiptDaoAbstract {

    private static final ReceiptDao INSTANCE = new ReceiptDao();
    private static final Logger LOGGER = LogManager.getLogger(ReceiptDao.class);

    private ReceiptDao() {
    }

    public static ReceiptDao getInstance() {
        return INSTANCE;
    }

    @Override
    protected List<Receipt> parseResultSet(ResultSet resultSet) throws DaoException {
        List<Receipt> receipts = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Receipt receipt = new Receipt();
                receipt.setStatusId(resultSet.getInt(Constants.STATUS_ID));
                receipt.setTotal(resultSet.getInt(Constants.TOTAL));
                receipt.setOrderId(resultSet.getInt(Constants.ORDER_ID));
                receipt.setUserId(resultSet.getInt(Constants.USER_ID));
                receipt.setId(resultSet.getInt(Constants.ID));
                receipt.setDate(String.valueOf(resultSet.getDate(Constants.DATE)));
                receipts.add(receipt);
            }
            return receipts;
        } catch (SQLException e) {
            throw new DaoException("Exception in parseResultSet", e);
        }
    }

    @Override
    protected void prepareInsert(PreparedStatement preparedStatement, Receipt receipt) throws DaoException {
        try {
            preparedStatement.setInt(1, receipt.getStatusId());
            preparedStatement.setInt(2, receipt.getTotal());
            preparedStatement.setInt(3, receipt.getOrderId());
            //TODO Change date
            preparedStatement.setDate(4, new Date(new java.util.Date().getTime()));
            preparedStatement.setInt(5, receipt.getUserId());
        } catch (SQLException e) {
            throw LOGGER.throwing(new DaoException("Exception in prepareInsert in ReceiptDao", e));
        }
    }

    @Override
    protected void prepareUpdate(PreparedStatement preparedStatement, Receipt entity) throws DaoException {

    }

    public void insert(ProxyConnection connection, Receipt receipt) throws DaoException {
        String insertQuery = getInsertQuery();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            prepareInsert(preparedStatement, receipt);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw LOGGER.throwing(new DaoException("Error in insert method", e));

        }
    }

    public List<Receipt> getByUserId(int userId) throws DaoException {
        String getByUserIdQuery = getByUserIdQuery();

        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getByUserIdQuery)) {
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            return parseResultSet(resultSet);

        } catch (ConnectionPoolException | SQLException e) {
            throw LOGGER.throwing(new DaoException("Error in getByUserId()", e));
        }

    }
}
