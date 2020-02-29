package by.epam.learn.bahlei.finaltask.dao.review;

import by.epam.learn.bahlei.finaltask.dao.AbstractEntityDao;
import by.epam.learn.bahlei.finaltask.entity.review.Review;

public abstract class ReviewDaoAbstract extends AbstractEntityDao<Review> {
    @Override
    protected String getDeleteQuery() {
        return "";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO reviews(order_id, user_id, message) VALUE(?, ?, ?)";
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

    protected String getReviewByOrderIdQuery() {
        return "SELECT id, order_id, user_id, message FROM reviews WHERE order_id = ?";
    }
}