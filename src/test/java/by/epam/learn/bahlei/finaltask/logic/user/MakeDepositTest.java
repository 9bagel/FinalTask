package by.epam.learn.bahlei.finaltask.logic.user;

import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.dao.user.UserDao;
import by.epam.learn.bahlei.finaltask.entity.user.User;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.util.validator.exception.ValidationException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class MakeDepositTest {
    @Mock
    private UserDao userDao;
    @InjectMocks
    private UserLogic userLogic;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterMethod
    public void afterMethod() {
        userLogic = null;
    }

    @Test(expectedExceptions = LogicException.class)
    public void throwLogicExceptionIfErrorWithDB() throws DaoException, ValidationException, LogicException {
        User user = new User();
        user.setBalance(new BigDecimal(10));
        Mockito.doThrow(new DaoException()).when(userDao).addBalance(Mockito.anyInt(), Mockito.any());
        userLogic.makeDeposit(user, new BigDecimal(10));
    }

    @Test
    public void okMakeDeposit() throws DaoException, ValidationException, LogicException {
        User user = new User();
        user.setBalance(new BigDecimal(10));
        Mockito.doNothing().when(userDao).addBalance(Mockito.anyInt(), Mockito.any());
        userLogic.makeDeposit(user, new BigDecimal(10));
    }
}