package by.epam.learn.bahlei.finaltask.logic.review;

import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.dao.review.ReviewDao;
import by.epam.learn.bahlei.finaltask.entity.review.Review;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class ReviewLogic {
    private static final Logger LOGGER = LogManager.getLogger(ReviewLogic.class);
    private final ReviewDao reviewDao;

    public ReviewLogic(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    public void addReview(Review review) throws LogicException {
        try {
            reviewDao.insert(review);
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException(e));
        }
    }

    public Review getReviewByOrderId(int orderId) throws LogicException {
        try {
            Optional<Review> optionalReview = reviewDao.getReviewsByOrderId(orderId)
                    .stream()
                    .findFirst();
            return optionalReview.orElse(null);
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException(e));
        }
    }
}
