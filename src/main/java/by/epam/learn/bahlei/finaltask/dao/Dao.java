package by.epam.learn.bahlei.finaltask.dao;

import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.entity.Entity;

import java.util.List;

public interface Dao<T extends Entity> {
    List<T> getAll() throws DaoException;

    void insert(T entity) throws DaoException;
    void update(T entity) throws DaoException;
    void delete(T entity) throws DaoException;
}
