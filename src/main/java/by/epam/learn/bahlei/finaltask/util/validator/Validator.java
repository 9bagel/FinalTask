package by.epam.learn.bahlei.finaltask.util.validator;

import by.epam.learn.bahlei.finaltask.util.Constants;
import by.epam.learn.bahlei.finaltask.util.validator.exception.ValidatorException;

public class Validator {
    private static final String LOGIN_REGEX = "^[A-Za-z0-9]{5,20}$";
    private static final String PASSWORD_REGEX = "^.{8,15}$";

    public static void validateLogin(String login) throws ValidatorException {
        try {
            matchString(login, LOGIN_REGEX);
        } catch (ValidatorException e) {
            throw new ValidatorException(Constants.LOGIN_INVALID);
        }
    }

    public static void validatePassword(String password) throws ValidatorException {
        try {
            matchString(password, PASSWORD_REGEX);
        } catch (ValidatorException e) {
            throw new ValidatorException(Constants.PASSWORD_INVALID);
        }
    }

    private static void matchString(String value, String regex) throws ValidatorException {
        if (!value.matches(regex)) {
            throw new ValidatorException();
        }
    }
}