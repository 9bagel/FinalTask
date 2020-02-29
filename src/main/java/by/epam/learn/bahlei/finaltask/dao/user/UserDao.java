package by.epam.learn.bahlei.finaltask.dao.user;

import by.epam.learn.bahlei.finaltask.connectionpool.ProxyConnection;
import by.epam.learn.bahlei.finaltask.connectionpool.exception.ConnectionPoolException;
import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.dto.RegistrationDto;
import by.epam.learn.bahlei.finaltask.entity.user.User;
import by.epam.learn.bahlei.finaltask.entity.user.UserRole;
import by.epam.learn.bahlei.finaltask.util.Constants;
import by.epam.learn.bahlei.finaltask.util.encryptor.BcryptUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends UserDaoAbstract {
    private final Logger logger = LogManager.getLogger(UserDao.class);

    public List<User> getUserByLogin(String login) throws DaoException {
        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getUserByLoginQuery())) {
            preparedStatement.setString(1, login);
            return parseResultSet(preparedStatement.executeQuery());
        } catch (SQLException | ConnectionPoolException e) {
            throw logger.throwing(new DaoException("Exception in getUserByLogin method in UserDao", e));
        }
    }

    @Override
    protected List<User> parseResultSet(ResultSet resultSet) throws DaoException {
        User user;
        List<User> userList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                user = new User();
                user.setLogin(resultSet.getString(Constants.LOGIN));
                user.setHashedPassword(resultSet.getString(Constants.Password));
                user.setEmail(resultSet.getString(Constants.EMAIL));
                user.setId(resultSet.getInt(Constants.ID));
                user.setUserRole(UserRole.getUserRoleById(resultSet.getInt(Constants.USER_ROLE_ID)));
                user.setBalance(BigDecimal.valueOf(resultSet.getInt(Constants.USER_BALANCE)));
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            throw logger.throwing(new DaoException("Exception in parseUser in UserDao", e));
        }

    }

    @Override
    protected void prepareInsert(PreparedStatement preparedStatement, User user) throws DaoException {
        try {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getHashedPassword());
            preparedStatement.setString(3, user.getEmail());
        } catch (SQLException e) {
            throw logger.throwing(new DaoException("Exception in prepareInsert in UserDao", e));
        }
    }

    @Override
    protected void prepareUpdate(PreparedStatement preparedStatement, User user) throws DaoException {
        try {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setInt(3, user.getUserRole().getId());
            preparedStatement.setBigDecimal(4, user.getBalance());
            preparedStatement.setLong(5, user.getId());

        } catch (SQLException e) {
            throw logger.throwing(new DaoException("Exception in prepareUpdate in UserDao", e));
        }
    }

    public void addBalance(int userId, BigDecimal amount) throws DaoException {
        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getAddBalanceQuery())) {

            preparedStatement.setBigDecimal(1, amount);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw logger.throwing(new DaoException("Exception in addBalance method in UserDao", e));
        }
    }

    public void subtractBalance(ProxyConnection connection, int userId, BigDecimal amount) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(getSubtractBalanceQuery())) {

            preparedStatement.setBigDecimal(1, amount);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw logger.throwing(new DaoException("Exception in subtractBalance method in UserDao", e));
        }
    }

    public void insert(RegistrationDto registrationDto) throws DaoException {
        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery())) {

            preparedStatement.setString(1, registrationDto.getLogin());
            preparedStatement.setString(2, BcryptUtil.generateHash(registrationDto.getPassword()));
            preparedStatement.setString(3, registrationDto.getEmail());
            preparedStatement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            throw logger.throwing(new DaoException("Exception in insert method in UserDao", e));
        }
    }

    public boolean isUserExists(String login) throws DaoException {
        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getUserByLoginQuery())) {
            preparedStatement.setString(1, login);
            return preparedStatement.executeQuery().next();
        } catch (SQLException | ConnectionPoolException e) {
            throw logger.throwing(new DaoException("Exception in getUserByLogin method in UserDao", e));
        }
    }
}