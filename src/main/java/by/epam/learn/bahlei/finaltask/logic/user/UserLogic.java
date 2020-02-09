package by.epam.learn.bahlei.finaltask.logic.user;

import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.dao.user.UserDao;
import by.epam.learn.bahlei.finaltask.dto.RegistrationDto;
import by.epam.learn.bahlei.finaltask.entity.user.User;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.exception.UserException;
import by.epam.learn.bahlei.finaltask.util.Constants;
import by.epam.learn.bahlei.finaltask.util.encryptor.BcryptUtil;
import by.epam.learn.bahlei.finaltask.util.validator.Validator;
import by.epam.learn.bahlei.finaltask.util.validator.exception.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class UserLogic {
    private final Logger logger = LogManager.getLogger(UserLogic.class);
    private final UserDao userDao;

    public UserLogic(UserDao userDao) {
        this.userDao = userDao;
    }

    public User login(String login, String password) throws LogicException, UserException, ValidationException {
        try {
            Validator.validateLogin(login, password);
            Optional<User> optionalUser = userDao.getUserByLogin(login)
                    .stream()
                    .findFirst();
            if (!optionalUser.isPresent()) {
                throw logger.throwing(new UserException(Constants.LOGIN_ERROR));
            }
            User user = optionalUser.get();
            if (!BcryptUtil.isPasswordCorrect(password, user.getHashedPassword())) {
                throw logger.throwing(new UserException(Constants.LOGIN_ERROR));
            }
            return user;
        } catch (DaoException e) {
            throw logger.throwing(new LogicException("Exception in checkLogin in UserLogic.class", e));
        }

    }

    public void register(RegistrationDto registrationDto) throws LogicException, ValidationException {
        try {
            Validator.validateRegistration(registrationDto);
            Optional<User> optionalUser = userDao.getUserByLogin(registrationDto.getLogin())
                    .stream()
                    .findFirst();
            if (optionalUser.isPresent()) {
                throw new ValidationException(Constants.LOGIN_TAKEN);
            }
            userDao.insert(registrationDto);
        } catch (DaoException e) {
            throw logger.throwing(new LogicException("Exception register new user", e));
        }
    }

    public void makeDeposit(User user, BigDecimal amount) throws LogicException, ValidationException {
        try {
            Validator.validateDeposit(amount);
            user.setBalance(user.getBalance().add(amount));
            userDao.addBalance(user.getId(), amount);
        } catch (DaoException e) {
            throw logger.throwing(new LogicException("Exception in makeDeposit()", e));
        }
    }

    public List<User> getAll() throws LogicException {
        try {
            return userDao.getAll();
        } catch (DaoException e) {
            throw logger.throwing(new LogicException("Exception in getAll() in UserLogic", e));
        }
    }

    public void updateUser(User user) throws LogicException {
        try {
            userDao.update(user);
        } catch (DaoException e) {
            throw logger.throwing(new LogicException("Exception in updateUser()", e));
        }
    }
}
