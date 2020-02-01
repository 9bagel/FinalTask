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
        return "UPDATE orders JOIN ordered_services ON orders.id = ordered_services.order_id SET orders.user_id = ?, orders.status_id = ?, ordered_services.service_id = ? WHERE orders.id = ?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "";
    }

    @Override
    protected String getSelectLimitQuery() {
        return "";
    }

    protected String getAddOrderedServiceQuery() {
        return "INSERT INTO ordered_services(order_id, service_id) VALUE(?, ?);";
    }

    protected String getOrderWithNewStatusQuery() {
        return "SELECT id, user_id, status_id FROM orders WHERE user_id = ? AND status_id = 1;";
    }
    protected String getDeleteServiceFromBasketQuery() {
        return "DELETE from ordered_services where order_id = ? AND service_id = ? LIMIT 1";
    }
    protected String getUpdateStatusQuery() {
        return "UPDATE orders SET status_id = ? WHERE id = ?";
    }

    protected String getOrderByUserIdQuery() {
        return "SELECT id, user_id, status_id FROM orders where user_id = ?";
    }

}
