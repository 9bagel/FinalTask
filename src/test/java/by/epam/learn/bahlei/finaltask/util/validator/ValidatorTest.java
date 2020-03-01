package by.epam.learn.bahlei.finaltask.util.validator;

import by.epam.learn.bahlei.finaltask.util.validator.exception.ValidationException;
import org.testng.annotations.Test;

public class ValidatorTest {

    @Test(expectedExceptions = ValidationException.class)
    public void throwExceptionIfLoginIncorrect() throws ValidationException {
        String invalidLogin = "$invalid#";
        String password = "password";

        Validator.validateLogin(invalidLogin, password);
    }

    @Test(expectedExceptions = ValidationException.class)
    public void throwExceptionIfPasswordIncorrect() throws ValidationException {
        String invalidPassword = "1";
        String login = "login";

        Validator.validateLogin(login, invalidPassword);
    }
}