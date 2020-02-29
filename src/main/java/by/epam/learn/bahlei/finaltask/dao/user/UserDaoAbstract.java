package by.epam.learn.bahlei.finaltask.dao.user;

import by.epam.learn.bahlei.finaltask.dao.AbstractEntityDao;
import by.epam.learn.bahlei.finaltask.entity.user.User;

public abstract class UserDaoAbstract extends AbstractEntityDao<User> {
    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM users where id = ?";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO users(login, password, email) VALUE(?, ?, ?);";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE users SET login = ?, email = ?, role_id = ?, balance = ? WHERE id = ?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT id, email, login, password, role_id, balance FROM users";
    }

    @Override
    protected String getSelectLimitQuery() {
        return "SELECT id, email, login, password, role_id, balance FROM users WHERE id > ? LIMIT ?";
    }

    protected String getUserByLoginQuery() {
        return "SELECT id, email, login, password, role_id, balance FROM users WHERE login = ?";
    }

    protected String getAddBalanceQuery() {
        return "UPDATE users SET balance = balance + ? where id = ?";
    }

    protected String getSubtractBalanceQuery() {
        return "UPDATE users SET balance = balance - ? where id = ?";
    }
}
