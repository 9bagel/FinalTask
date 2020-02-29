package by.epam.learn.bahlei.finaltask.util.validator;

import by.epam.learn.bahlei.finaltask.dto.RegistrationDto;
import by.epam.learn.bahlei.finaltask.util.Constants;
import by.epam.learn.bahlei.finaltask.util.validator.exception.ValidationException;

import java.math.BigDecimal;

/**
 * Helps validate data
 */
public class Validator {
    private static final String LOGIN_REGEX = "^[A-Za-z0-9]{5,20}$";
    private static final String PASSWORD_REGEX = "^.{8,15}$";
    private static final String EMAIL_REGEX = "\\w+@\\w+\\.\\w+";
    private static final String AMOUNT_REGEX = "^[1-9][0-9]*$";

    public static void validateLogin(String login, String password) throws ValidationException {
        try {
            matchString(login, LOGIN_REGEX);
            matchString(password, PASSWORD_REGEX);
        } catch (ValidationException e) {
            throw new ValidationException(Constants.LOGIN_DATA_INVALID);
        }
    }

    private static void matchString(String value, String regex) throws ValidationException {
        if (!value.matches(regex)) {
            throw new ValidationException();
        }
    }

    public static void validateRegistration(RegistrationDto registrationDto) throws ValidationException {
        if (!registrationDto.getPassword().equals(registrationDto.getRepeatPassword())) {
            throw new ValidationException(Constants.REGISTRATION_DATA_INVALID);
        }
        try {
            matchString(registrationDto.getLogin(), LOGIN_REGEX);
            matchString(registrationDto.getPassword(), PASSWORD_REGEX);
            matchString(registrationDto.getEmail(), EMAIL_REGEX);
        } catch (ValidationException e) {
            throw new ValidationException(Constants.REGISTRATION_DATA_INVALID);
        }
    }

    public static void validateDeposit(BigDecimal amount) throws ValidationException {
        matchString(amount.toString(), AMOUNT_REGEX);
    }
}