package by.epam.learn.bahlei.finaltask.logic.factory;

import by.epam.learn.bahlei.finaltask.dao.factory.DaoFactory;
import by.epam.learn.bahlei.finaltask.logic.order.OrderLogic;
import by.epam.learn.bahlei.finaltask.logic.review.ReviewLogic;
import by.epam.learn.bahlei.finaltask.logic.service.ServiceLogic;
import by.epam.learn.bahlei.finaltask.logic.user.UserLogic;
/**
 * This class based on factory design pattern provides Logic classes on demand.
 */
public class LogicFactory {
    private static OrderLogic orderLogic;
    private static ServiceLogic serviceLogic;
    private static ReviewLogic reviewLogic;
    private static UserLogic userLogic;

    public static UserLogic getUserLogic() {
        if (userLogic == null) {
            userLogic = new UserLogic(DaoFactory.getUserDao());
        }
        return userLogic;
    }

    public static ServiceLogic getServiceLogic() {
        if (serviceLogic == null) {
            serviceLogic = new ServiceLogic(DaoFactory.getServiceDao());
        }
        return serviceLogic;
    }

    public static OrderLogic getOrderLogic() {
        if (orderLogic == null) {
            orderLogic = new OrderLogic(DaoFactory.getOrderDao(), DaoFactory.getUserDao());
        }
        return orderLogic;
    }

    public static ReviewLogic getReviewLogic() {
        if (reviewLogic == null) {
            reviewLogic = new ReviewLogic(DaoFactory.getReviewDao());
        }
        return reviewLogic;
    }
}
