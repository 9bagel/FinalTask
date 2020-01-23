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
        return "UPDATE users SET login = ?, password = ?, email = ?, role_id = ? WHERE id = ?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT id, email, login, password, role_id FROM users";
    }

    @Override
    protected String getSelectLimitQuery() {
        return "SELECT id, email, login, password, role_id FROM users WHERE id > ? LIMIT ?";
    }

    protected String getUserByLoginQuery() {
        return "SELECT * FROM users WHERE login = ?";
    }
}
