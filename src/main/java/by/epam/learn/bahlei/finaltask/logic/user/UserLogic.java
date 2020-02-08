package by.epam.learn.bahlei.finaltask.logic.user;

import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.dao.user.UserDao;
import by.epam.learn.bahlei.finaltask.entity.user.User;
import by.epam.learn.bahlei.finaltask.entity.user.UserRole;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.exception.UserException;
import by.epam.learn.bahlei.finaltask.util.Constants;
import by.epam.learn.bahlei.finaltask.util.encryptor.BcryptUtil;
import by.epam.learn.bahlei.finaltask.util.validator.Validator;
import by.epam.learn.bahlei.finaltask.util.validator.exception.ValidatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class UserLogic {
    private static final Logger LOGGER = LogManager.getLogger(UserLogic.class);
    private final UserDao userDao;

    public UserLogic(UserDao userDao) {
        this.userDao = userDao;
    }

    public User login(String login, String password) throws LogicException, UserException, ValidatorException {
        Validator.validateLogin(login);
        Validator.validatePassword(password);
        try {
            Optional<User> optionalUser = userDao.getUserByLogin(login)
                    .stream()
                    .findFirst();
            if (!optionalUser.isPresent()) {
                throw LOGGER.throwing(new UserException(Constants.LOGIN_ERROR));
            }
            User user = optionalUser.get();
            if (!BcryptUtil.isPasswordCorrect(password, user.getHashedPassword())) {
                throw LOGGER.throwing(new UserException(Constants.LOGIN_ERROR));
            }
            return user;
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException("Exception in checkLogin in LoginLogic.class", e));
        }

    }

    public User register(String login, String password, String email) throws LogicException, UserException {
        String hashedPassword = BcryptUtil.generateHash(password);
        User user = new User();
        user.setLogin(login);
        user.setHashedPassword(hashedPassword);
        user.setEmail(email);
        try {
            userDao.insert(user);
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException("Exception register new user", e));
        }

        return user;

    }

    public void checkPermission(int userTypeId, UserRole requiredType) throws LogicException {
        if (userTypeId != requiredType.getId()) {
            throw new LogicException("Not enough permissions");
        }
    }

    public void makeDeposit(User user, BigDecimal amount) throws LogicException {
        try {
            user.setBalance(user.getBalance().add(amount));
            userDao.addBalance(user.getId(), amount);
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException("Exception in makeDeposit()", e));
        }
    }

    public User getUserById(Integer userId) throws UserException, LogicException {
        try {
            Optional<User> optionalUser = userDao.getUserById(userId)
                    .stream()
                    .findFirst();
            if (!optionalUser.isPresent()) {
                throw LOGGER.throwing(new UserException(Constants.USER_NOT_FOUND_ERROR));
            }
            return optionalUser.get();
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException("Exception in makeDeposit()", e));
        }
    }

    public List<User> getAll() throws LogicException {
        try {
            return userDao.getAll();
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException("Exception in getAll() in UserLogic", e));
        }
    }

    public void updateUser(User user) throws LogicException {
        try {
            userDao.update(user);
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException("Exception in updateUser()", e));
        }
    }
}
