package by.epam.learn.bahlei.finaltask.dao.review;

import by.epam.learn.bahlei.finaltask.connectionpool.ProxyConnection;
import by.epam.learn.bahlei.finaltask.connectionpool.exception.ConnectionPoolException;
import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.entity.review.Review;
import by.epam.learn.bahlei.finaltask.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao extends ReviewDaoAbstract {
    private static final Logger LOGGER = LogManager.getLogger(ReviewDao.class);

    @Override
    protected List<Review> parseResultSet(ResultSet resultSet) throws DaoException {
        List<Review> reviews = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Review review = new Review();
                review.setId(resultSet.getInt(Constants.ID));
                review.setUserId(resultSet.getInt(Constants.USER_ID));
                review.setOrderId(resultSet.getInt(Constants.ORDER_ID));
                review.setMessage(resultSet.getString(Constants.MESSAGE));
                reviews.add(review);
            }
            return reviews;
        } catch (SQLException e) {
            throw LOGGER.throwing(new DaoException(e));
        }
    }

    @Override
    protected void prepareInsert(PreparedStatement preparedStatement, Review review) throws DaoException {
        try {
            preparedStatement.setInt(1, review.getOrderId());
            preparedStatement.setInt(2, review.getUserId());
            preparedStatement.setString(3, review.getMessage());
        } catch (SQLException e) {
            throw LOGGER.throwing(new DaoException(e));
        }
    }

    @Override
    protected void prepareUpdate(PreparedStatement preparedStatement, Review entity) throws DaoException {

    }


    public List<Review> getReviewsByOrderId(int orderId) throws DaoException {
        try (ProxyConnection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getReviewByOrderIdQuery())) {
            preparedStatement.setInt(1, orderId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return parseResultSet(resultSet);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw LOGGER.throwing(new DaoException(e));
        }
    }
}