package by.epam.learn.bahlei.finaltask.dao.order;

import by.epam.learn.bahlei.finaltask.dao.AbstractEntityDao;
import by.epam.learn.bahlei.finaltask.entity.order.Order;

public abstract class OrderDaoAbstract extends AbstractEntityDao<Order> {
    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM services where id = ?";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO orders(user_id, status_id, date) VALUE(?, ?, ?);";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE services SET type_id = ?, title_en = ?, title_ru = ?, title_by = ?, description_en = ?, description_ru = ?, description_by = ?, price = ? WHERE id = ?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT id, type_id, title_en, title_ru, title_by, description_en, description_ru, description_by, price FROM services";
    }

    @Override
    protected String getSelectLimitQuery() {
        return "SELECT id, type_id, title_en, title_ru, title_by, description_en, description_ru, description_by, price FROM services WHERE id > ? LIMIT ?";
    }

    protected String getServicesByTypeIdQuery() {
        return "SELECT id, type_id, title_en, title_ru, title_by, description_en, description_ru, description_by, price FROM services WHERE type_id = ?";
    }

    protected String getServiceByIdQuery() {
        return "SELECT id, type_id, title_en, title_ru, title_by, description_en, description_ru, description_by, price FROM services WHERE id = ?";
    }
}
