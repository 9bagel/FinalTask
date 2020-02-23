package by.epam.learn.bahlei.finaltask.connectionpool;

import by.epam.learn.bahlei.finaltask.connectionpool.exception.ConnectionPoolException;
import by.epam.learn.bahlei.finaltask.database.DBConnector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static by.epam.learn.bahlei.finaltask.util.Constants.DATABASE_PROPERTIES;
import static by.epam.learn.bahlei.finaltask.util.Constants.DB_POOL;

public class ConnectionPool {
    private static final ConnectionPool instance = new ConnectionPool();
    private BlockingQueue<ProxyConnection> connectionQueue;

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    private ConnectionPool() {

    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public void init() throws ConnectionPoolException {
        final int POOL_SIZE = Integer.parseInt(ResourceBundle.getBundle(DATABASE_PROPERTIES).getString(DB_POOL));
        LOGGER.info("Creating connectionPool with size = " + POOL_SIZE);
        connectionQueue = new ArrayBlockingQueue<>(POOL_SIZE);

        for (int i = 0; i < POOL_SIZE; i++) {
            Connection connection;
            try {
                connection = DBConnector.createConnection();
                connectionQueue.offer(new ProxyConnection(connection));

            } catch (ClassNotFoundException e) {
                throw LOGGER.throwing(new ConnectionPoolException("Exception register dataBase driver", e));

            } catch (SQLException e) {
                throw LOGGER.throwing(new ConnectionPoolException("Exception create connectionPool", e));
            }
        }
    }

    public ProxyConnection getConnection() throws ConnectionPoolException {
        LOGGER.info("Getting connection from connectionPool");
        try {
            return connectionQueue.take();
        } catch (InterruptedException e) {
            throw LOGGER.throwing(new ConnectionPoolException(e));
        }
    }

    public void closeConnection(ProxyConnection connection) {
        LOGGER.info("Return connection back to connectionPool");
        connectionQueue.offer(connection);
    }

    public void closeAllRealConnections() {

        for (ProxyConnection proxyConnection : connectionQueue) {
            try {
                proxyConnection.realClose();
            } catch (SQLException e) {
                LOGGER.error("Exception realClose connection in ConnectionPool " + proxyConnection, e);
            }
        }
    }
}
