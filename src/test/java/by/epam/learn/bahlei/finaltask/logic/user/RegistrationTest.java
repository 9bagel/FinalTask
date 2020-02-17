package by.epam.learn.bahlei.finaltask.logic.user;

import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.dao.user.UserDao;
import by.epam.learn.bahlei.finaltask.dto.RegistrationDto;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.exception.UserException;
import by.epam.learn.bahlei.finaltask.util.Constants;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class RegistrationTest {
    @Mock
    private UserDao userDao;
    @InjectMocks
    private UserLogic userLogic;
    private RegistrationDto registrationDto;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        registrationDto = new RegistrationDto();
        registrationDto.setLogin("validLogin");
        registrationDto.setPassword("validPassword");
        registrationDto.setRepeatPassword("validPassword");
        registrationDto.setEmail("validEmail@epam.by");
    }

    @AfterMethod
    public void afterMethod() {
        userLogic = null;
    }

    @Test(expectedExceptions = UserException.class,
            expectedExceptionsMessageRegExp = Constants.LOGIN_TAKEN)
    public void throwUserExceptionIfUserExists() throws DaoException, LogicException, UserException {
        Mockito.when(userDao.isUserExists(Mockito.anyString())).thenReturn(true);
        userLogic.register(registrationDto);
    }

    @Test(expectedExceptions = LogicException.class)
    public void throwLogicExceptionIfErrorWithDB() throws DaoException, LogicException, UserException {
        Mockito.when(userDao.getUserByLogin(Mockito.anyString())).thenReturn(new ArrayList<>());
        Mockito.doThrow(new DaoException()).when(userDao).insert(registrationDto);
        userLogic.register(registrationDto);
    }

    @Test
    public void okRegister() throws DaoException, LogicException, UserException {
        Mockito.when(userDao.getUserByLogin(Mockito.anyString())).thenReturn(new ArrayList<>());
        Mockito.doNothing().when(userDao).insert(registrationDto);
        userLogic.register(registrationDto);
    }
}