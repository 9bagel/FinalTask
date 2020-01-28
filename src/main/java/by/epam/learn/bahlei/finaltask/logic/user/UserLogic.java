package by.epam.learn.bahlei.finaltask.logic.user;

import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.dao.factory.DaoFactory;
import by.epam.learn.bahlei.finaltask.dao.user.UserDao;
import by.epam.learn.bahlei.finaltask.entity.user.User;
import by.epam.learn.bahlei.finaltask.entity.user.UserType;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.exception.UserException;
import by.epam.learn.bahlei.finaltask.util.Constants;
import by.epam.learn.bahlei.finaltask.util.encryptor.BcryptUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Optional;

public class UserLogic {
    private static final Logger LOGGER = LogManager.getLogger(UserLogic.class);
    private static final UserLogic INSTANCE = new UserLogic();
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private UserDao userDao = daoFactory.getUserDao();

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
        User user = new User();
        user.setLogin(login);
        user.setHashedPassword(hashedPassword);
        user.setEmail(email);
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

    public void makeDeposit(int userId, BigDecimal amount) throws LogicException {
        try {
            userDao.addBalance(userId, amount);
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException("Exception in makeDeposit()", e));
        }
    }

    public User getUserById(Integer userId) throws UserException, LogicException {
        try {
            Optional<User> user = userDao.getUserById(userId)
                    .stream()
                    .findFirst();
            if (!user.isPresent()) {
                throw LOGGER.throwing(new UserException(Constants.USER_NOT_FOUND_ERROR));
            }
            return user.get();
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException("Exception in makeDeposit()", e));
        }
    }
}
