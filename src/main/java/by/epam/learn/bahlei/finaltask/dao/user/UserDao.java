package by.epam.learn.bahlei.finaltask.dao.user;

import by.epam.learn.bahlei.finaltask.connectionpool.ProxyConnection;
import by.epam.learn.bahlei.finaltask.connectionpool.exception.ConnectionPoolException;
import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.entity.user.User;
import by.epam.learn.bahlei.finaltask.logic.exception.UserException;
import by.epam.learn.bahlei.finaltask.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends UserDaoAbstract {

    private static final UserDao INSTANCE = new UserDao();
    private static final Logger LOGGER = LogManager.getLogger(UserDao.class);

    private UserDao() {
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    public User getUserByLogin(String login) throws DaoException, UserException {
        String query = getUserByLoginQuery();
        User user;
        List<User> userList;


        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();

            userList = parseResultSet(resultSet);
            if (!userList.isEmpty()) {
                user = userList.iterator().next();
            } else {
                throw LOGGER.throwing(new UserException("User doesn't exist"));
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw LOGGER.throwing(new DaoException("Exception in getUserByLogin method in UserDao", e));
        }

        return user;
    }

    @Override
    protected List<User> parseResultSet(ResultSet resultSet) throws DaoException {
        User user = new User();
        List<User> userList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                user.setLogin(resultSet.getString(Constants.USER_LOGIN));
                user.setHashedPassword(resultSet.getString(Constants.USER_Password));
                user.setEmail(resultSet.getString(Constants.USER_EMAIL));
                user.setId(resultSet.getInt(Constants.ID));
                user.setTypeId(resultSet.getInt(Constants.USER_ROLE_ID));
                user.setBalance(BigDecimal.valueOf(resultSet.getInt(Constants.USER_BALANCE)));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw LOGGER.throwing(new DaoException("Exception in parseUser in UserDao", e));
        }

        return userList;
    }

    @Override
    protected void prepareInsert(PreparedStatement preparedStatement, User user) throws DaoException {
        try {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getHashedPassword());
            preparedStatement.setString(3, user.getEmail());
        } catch (SQLException e) {
            throw LOGGER.throwing(new DaoException("Exception in prepareInsert in UserDao", e));
        }
    }

    @Override
    protected void prepareUpdate(PreparedStatement preparedStatement, User user) throws DaoException {
        try {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getHashedPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getTypeId());
            preparedStatement.setLong(5, user.getId());

        } catch (SQLException e) {
            throw LOGGER.throwing(new DaoException("Exception in prepareUpdate in UserDao", e));
        }
    }
}
