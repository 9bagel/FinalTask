package by.epam.learn.bahlei.finaltask.dao;

import by.epam.learn.bahlei.finaltask.connectionpool.ConnectionPool;
import by.epam.learn.bahlei.finaltask.connectionpool.ProxyConnection;
import by.epam.learn.bahlei.finaltask.connectionpool.exception.ConnectionPoolException;
import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.entity.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractEntityDao<T extends Entity> implements Dao<T> {
    private static final Logger LOGGER = LogManager.getLogger(AbstractEntityDao.class);

    protected final ConnectionPool connectionPool;

    public AbstractEntityDao() {
        connectionPool = ConnectionPool.getInstance();
    }

    protected abstract String getDeleteQuery();

    protected abstract String getInsertQuery();

    protected abstract String getUpdateQuery();

    protected abstract String getSelectAllQuery();

    protected abstract String getSelectLimitQuery();

    protected abstract List<T> parseResultSet(ResultSet resultSet) throws DaoException;

    protected abstract void prepareInsert(PreparedStatement preparedStatement, T entity) throws DaoException;

    protected abstract void prepareUpdate(PreparedStatement preparedStatement, T entity) throws DaoException;

    @Override
    public List<T> getAll() throws DaoException {
        String selectAllQuery = getSelectAllQuery();
        List<T> entityList;

        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectAllQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            entityList = parseResultSet(resultSet);

        } catch (ConnectionPoolException | SQLException e) {
            throw LOGGER.throwing(new DaoException("Error in getAll method", e));

        }
        return entityList;
    }

    @Override
    public void insert(T entity) throws DaoException {
        String insertQuery = getInsertQuery();

        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            prepareInsert(preparedStatement, entity);
            preparedStatement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            throw LOGGER.throwing(new DaoException("Error in insert method", e));

        }
    }

    @Override
    public void update(T entity) throws DaoException {
        String updateQuery = getUpdateQuery();

        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            prepareUpdate(preparedStatement, entity);
            preparedStatement.execute();

        } catch (ConnectionPoolException | SQLException e) {
            throw LOGGER.throwing(new DaoException("Error in update method", e));
        }
    }

    @Override
    public void delete(T entity) throws DaoException {
        String deleteQuery = getDeleteQuery();
        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            throw LOGGER.throwing(new DaoException("Error in delete method", e));
        }
    }
}

