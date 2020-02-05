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
        return "INSERT INTO orders(user_id, status_id, total, date) VALUE(?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE orders SET status_id = ?, total = ?, date = ? WHERE id = ?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT id, user_id, status_id, date, total FROM orders";
    }

    @Override
    protected String getSelectLimitQuery() {
        return "";
    }

    protected String getAddOrderedServiceQuery() {
        return "INSERT INTO ordered_services(order_id, service_id) VALUE(?, ?);";
    }

    protected String getUpdateStatusQuery() {
        return "UPDATE orders SET status_id = ? WHERE id = ?";
    }

    protected String getOrderByUserIdQuery() {
        return "SELECT id, user_id, status_id, date, total FROM orders where user_id = ?";
    }
    protected String getOrderByIdQuery() {
        return "SELECT id, user_id, status_id, date, total FROM orders where id = ?";
    }
    protected String getOrderByUserIdAndOrderIdQuery() {
        return "SELECT id, user_id, status_id, date, total FROM orders where user_id = ? AND id = ?";
    }

}
