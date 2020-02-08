package by.epam.learn.bahlei.finaltask.util.validator;

import by.epam.learn.bahlei.finaltask.util.validator.exception.ValidatorException;
import org.testng.annotations.Test;

public class ValidatorTest {

    @Test(expectedExceptions = ValidatorException.class)
    public void throwExceptionIfLoginIncorrect() throws ValidatorException {
        String invalidLogin = "$fdfd$gfg";

        Validator.validateLogin(invalidLogin);
    }

    @Test(expectedExceptions = ValidatorException.class)
    public void throwExceptionIfPasswordIncorrect() throws ValidatorException {
        String invalidPassword = "1234";

        Validator.validatePassword(invalidPassword);
    }
}