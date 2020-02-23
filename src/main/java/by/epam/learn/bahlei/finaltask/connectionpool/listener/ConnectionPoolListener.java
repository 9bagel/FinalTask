package by.epam.learn.bahlei.finaltask.connectionpool.listener;

import by.epam.learn.bahlei.finaltask.connectionpool.ConnectionPool;
import by.epam.learn.bahlei.finaltask.connectionpool.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

@WebListener
public class ConnectionPoolListener implements javax.servlet.ServletContextListener {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPoolListener.class);

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            connectionPool.init();
        } catch (ConnectionPoolException e) {
            LOGGER.error("Exception creating connectionPool", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        connectionPool.closeAllRealConnections();
    }
}
