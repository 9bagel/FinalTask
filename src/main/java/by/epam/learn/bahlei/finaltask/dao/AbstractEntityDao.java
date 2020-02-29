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

    /**
     * Returns query to delete entity
     */
    protected abstract String getDeleteQuery();

    /**
     * Returns query to add entity
     */
    protected abstract String getInsertQuery();

    /**
     * Returns query to update entity
     */
    protected abstract String getUpdateQuery();

    /**
     * Returns query to select all entities
     */
    protected abstract String getSelectAllQuery();

    /**
     * Returns query to select limited list of entities
     */
    protected abstract String getSelectLimitQuery();

    /**
     * Parses ResultSet and returns list of entities
     */
    protected abstract List<T> parseResultSet(ResultSet resultSet) throws DaoException;

    /**
     * Prepares statement for insert
     */
    protected abstract void prepareInsert(PreparedStatement preparedStatement, T entity) throws DaoException;

    /**
     * Prepares statement for uppdate
     */
    protected abstract void prepareUpdate(PreparedStatement preparedStatement, T entity) throws DaoException;

    @Override
    public List<T> getAll() throws DaoException {
        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getSelectAllQuery())) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return parseResultSet(resultSet);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw LOGGER.throwing(new DaoException("Error in getAll method", e));
        }
    }

    @Override
    public void insert(T entity) throws DaoException {
        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getInsertQuery())) {

            prepareInsert(preparedStatement, entity);
            preparedStatement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            throw LOGGER.throwing(new DaoException("Error in insert method", e));

        }
    }

    @Override
    public void update(T entity) throws DaoException {
        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getUpdateQuery())) {

            prepareUpdate(preparedStatement, entity);
            preparedStatement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw LOGGER.throwing(new DaoException("Error in update method", e));
        }
    }

    @Override
    public void delete(T entity) throws DaoException {
        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery())) {
            preparedStatement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            throw LOGGER.throwing(new DaoException("Error in delete method", e));
        }
    }
}

