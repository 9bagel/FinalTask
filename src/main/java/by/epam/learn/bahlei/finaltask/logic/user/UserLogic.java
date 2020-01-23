package by.epam.learn.bahlei.finaltask.logic.user;

import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.dao.factory.DaoFactory;
import by.epam.learn.bahlei.finaltask.dao.service.ServiceDao;
import by.epam.learn.bahlei.finaltask.dao.user.UserDao;
import by.epam.learn.bahlei.finaltask.entity.service.LocalisedService;
import by.epam.learn.bahlei.finaltask.entity.user.User;
import by.epam.learn.bahlei.finaltask.entity.user.UserType;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.exception.UserException;
import by.epam.learn.bahlei.finaltask.util.encryptor.BcryptUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserLogic {
    private static final Logger LOGGER = LogManager.getLogger(UserLogic.class);
    private static final UserLogic INSTANCE = new UserLogic();
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private UserDao userDao = daoFactory.getUserDao();
    private ServiceDao serviceDao = daoFactory.getServiceDao();

    private UserLogic() {
    }

    public static UserLogic getInstance() {
        return INSTANCE;
    }

    public User login(String login, String password) throws LogicException, UserException {
        User user;
        LOGGER.info("Start login check");
        try {
            user = userDao.getUserByLogin(login);
            if (BcryptUtil.isPasswordCorrect(password, user.getHashedPassword())) {
                return user;
            } else {
                throw LOGGER.throwing(new UserException("Password is incorrect"));
            }
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException("Exception in checkLogin in LoginLogic.class", e));
        }

    }

    public User registration(String login, String password, String email) throws LogicException, UserException {
        String hashedPassword = BcryptUtil.generateHash(password);
        User user = new User(login, hashedPassword, email);

        LOGGER.info("Start registration logic");
        try {
            userDao.insert(user);
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException("Exception register new user", e));
        }

        return user;

    }

    public void checkPermission(int userTypeId, UserType requiredType) throws LogicException {
        if (userTypeId != requiredType.getId()) {
            throw new LogicException("Not enough permissions");
        }
    }

    public void addServiceToCart(int userId, int serviceId) {
        LocalisedService localisedService = serviceDao.getServiceById(serviceId);
    }
}
