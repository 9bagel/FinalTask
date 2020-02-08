package by.epam.learn.bahlei.finaltask.logic.user;

import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.dao.factory.DaoFactory;
import by.epam.learn.bahlei.finaltask.dao.user.UserDao;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.exception.UserException;
import by.epam.learn.bahlei.finaltask.util.validator.exception.ValidatorException;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;

@RunWith(MockitoJUnitRunner.class)
public class UserLogicTest {
//    UserLogic userLogic = new UserLogic();
//    @Before
//    public void setUp() {
//        userDao = Mockito.mock(daoFactory.getUserDao().getClass());
//    }
//
//    @Test(expectedExceptions = UserException.class)
//    public void userExceptionIfLoginNotPresentInDB() throws DaoException, ValidatorException, LogicException, UserException {
//        Mockito.when(userDao.getUserByLogin("testLogin")).thenReturn(Collections.EMPTY_LIST);
//        UserLogic userLogic = UserLogic.getInstance();
//        userLogic.login("testLogin", "password");
//    }
}