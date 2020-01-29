package by.epam.learn.bahlei.finaltask.dao.receipt;

import by.epam.learn.bahlei.finaltask.connectionpool.ProxyConnection;
import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.entity.receipt.Receipt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        return null;
    }

    @Override
    protected void prepareInsert(PreparedStatement preparedStatement, Receipt receipt) throws DaoException {
        try {
            preparedStatement.setInt(1, receipt.getStatus_id());
            preparedStatement.setInt(2, receipt.getTotal());
            preparedStatement.setInt(3, receipt.getOrder_id());
            //TODO Change date
            preparedStatement.setDate(4, new Date(new java.util.Date().getTime()));
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
}
