package by.epam.learn.bahlei.finaltask.dao;

import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.entity.Entity;

import java.util.List;

/**
 * Represents base DAO interface for common operations with data storage.
 */
public interface Dao<T extends Entity> {

    /**
     * Returns a list of all entities
     */
    List<T> getAll() throws DaoException;

    /**
     * Add a new entity
     */
    void insert(T entity) throws DaoException;

    /**
     * Updates entity data
     */
    void update(T entity) throws DaoException;

    /**
     * Removes entity data
     */
    void delete(T entity) throws DaoException;
}
