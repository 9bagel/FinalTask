package by.epam.learn.bahlei.finaltask.logic.user;

import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.dao.user.UserDao;
import by.epam.learn.bahlei.finaltask.entity.user.User;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.exception.UserException;
import by.epam.learn.bahlei.finaltask.util.validator.exception.ValidationException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class LoginTest {
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

    @Test(expectedExceptions = UserException.class)
    public void throwUserExceptionIfLoginNotPresentInDB() throws DaoException, ValidationException, LogicException, UserException {
        Mockito.when(userDao.getUserByLogin("testLogin")).thenReturn(new ArrayList<>());
        userLogic.login("testLogin", "password");
    }

    @Test(expectedExceptions = LogicException.class)
    public void throwLogicExceptionIfErrorWithDB() throws DaoException, ValidationException, LogicException, UserException {
        Mockito.when(userDao.getUserByLogin(Mockito.anyString())).thenThrow(new DaoException());
        userLogic.login("login", "password");
    }

    @Test(expectedExceptions = UserException.class)
    public void throwUserExceptionIfWrongPassword() throws DaoException, ValidationException, LogicException, UserException {
        User user = new User();
        user.setHashedPassword("$2a$10$bBFzQwn6wPVaMhURo3X0I.eBezmv8nhbtiW3ib.xLkI.u6zrzRAiy"); //validPassword
        List<User> users = new ArrayList<>();
        users.add(user);

        Mockito.when(userDao.getUserByLogin(Mockito.anyString())).thenReturn(users);
        userLogic.login("login", "wrongPassword");
    }

    @Test
    public void okIfLoginAndPasswordCorrect() throws DaoException, ValidationException, LogicException, UserException {
        User expectedUser = new User();
        expectedUser.setHashedPassword("$2a$10$bBFzQwn6wPVaMhURo3X0I.eBezmv8nhbtiW3ib.xLkI.u6zrzRAiy"); //validPassword
        List<User> users = new ArrayList<>();
        users.add(expectedUser);

        Mockito.when(userDao.getUserByLogin("login")).thenReturn(users);
        User actualUser = userLogic.login("login", "validPassword");

        Assert.assertEquals(actualUser, expectedUser);
    }
}