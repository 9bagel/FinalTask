package by.epam.learn.bahlei.finaltask.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static by.epam.learn.bahlei.finaltask.util.Constants.*;

public class DBConnector {
    private static final Logger LOGGER = LogManager.getLogger(DBConnector.class);

    public static Connection createConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);

        ResourceBundle resource = ResourceBundle.getBundle(DATABASE_PROPERTIES);

        String user = resource.getString(DB_USER);
        String password = resource.getString(DB_PASSWORD);
        String url = resource.getString(DB_URL);
        LOGGER.info("Establishing connection with data base");
            return DriverManager.getConnection(url, user, password);
    }
}
