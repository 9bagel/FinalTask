package by.epam.learn.bahlei.finaltask.logic.receipt;

import by.epam.learn.bahlei.finaltask.connectionpool.ConnectionPool;
import by.epam.learn.bahlei.finaltask.connectionpool.ProxyConnection;
import by.epam.learn.bahlei.finaltask.connectionpool.exception.ConnectionPoolException;
import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.dao.factory.DaoFactory;
import by.epam.learn.bahlei.finaltask.dao.order.OrderDao;
import by.epam.learn.bahlei.finaltask.dao.receipt.ReceiptDao;
import by.epam.learn.bahlei.finaltask.entity.order.OrderStatus;
import by.epam.learn.bahlei.finaltask.entity.receipt.Receipt;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class ReceiptLogic {
    private static final Logger LOGGER = LogManager.getLogger(ReceiptLogic.class);
    private static final ReceiptLogic INSTANCE = new ReceiptLogic();
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private ReceiptDao receiptDao = daoFactory.getReceiptDao();
    private OrderDao orderDao = daoFactory.getOrderDao();

    private ReceiptLogic() {
    }

    public static ReceiptLogic getInstance() {
        return INSTANCE;
    }

    public void createReceiptTransaction(Receipt receipt) throws LogicException {
        ProxyConnection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            receiptDao.insert(connection, receipt);
            orderDao.updateStatus(connection, receipt.getOrderId(), OrderStatus.IN_PROGRESS);
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
}