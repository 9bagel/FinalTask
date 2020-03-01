package by.epam.learn.bahlei.finaltask.logic.user;

import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.dao.user.UserDao;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class GetAllTest {
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
    public void exceptionGetAll() throws DaoException, LogicException {
        Mockito.when(userDao.getAll()).thenThrow(new DaoException());

        userLogic.getAll();
    }

    @Test
    public void okGetAll() throws DaoException, LogicException {
        Mockito.when(userDao.getAll()).thenReturn(new ArrayList<>());

        userLogic.getAll();
    }
}