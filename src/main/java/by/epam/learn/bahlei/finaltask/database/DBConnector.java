package by.epam.learn.bahlei.finaltask.database;

import by.epam.learn.bahlei.finaltask.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnector {
    private static final Logger LOGGER = LogManager.getLogger(DBConnector.class);

    public static Connection createConnection() throws ClassNotFoundException, SQLException {
        Class.forName(Constants.DRIVER);

        ResourceBundle resource = ResourceBundle.getBundle(Constants.DATABASE_PROPERTIES);

        String user = resource.getString(Constants.DB_USER);
        String password = resource.getString(Constants.DB_PASSWORD);
        String url = resource.getString(Constants.DB_URL);
        LOGGER.info("Establishing connection with data base");
        return DriverManager.getConnection(url, user, password);
    }
}
