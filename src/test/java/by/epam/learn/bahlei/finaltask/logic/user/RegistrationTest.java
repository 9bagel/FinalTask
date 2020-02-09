package by.epam.learn.bahlei.finaltask.logic.user;

import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.dao.user.UserDao;
import by.epam.learn.bahlei.finaltask.dto.RegistrationDto;
import by.epam.learn.bahlei.finaltask.entity.user.User;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.util.Constants;
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

public class RegistrationTest {
    @Mock
    private UserDao userDao;
    @InjectMocks
    private UserLogic userLogic;
    private List<User> users;
    private RegistrationDto registrationDto;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        users = new ArrayList<>();
        users.add(new User());
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

    @Test
    public void throwValidationExceptionIfUserExists() throws DaoException, LogicException {
        try {
            Mockito.when(userDao.getUserByLogin(Mockito.anyString())).thenReturn(users);
            userLogic.register(registrationDto);
        } catch (ValidationException e) {
            Assert.assertEquals(e.getMessage(), Constants.LOGIN_TAKEN);
        }
    }

    @Test(expectedExceptions = LogicException.class)
    public void throwLogicExceptionIfErrorWithDB() throws DaoException, LogicException, ValidationException {
        Mockito.when(userDao.getUserByLogin(Mockito.anyString())).thenReturn(new ArrayList<>());
        Mockito.doThrow(new DaoException()).when(userDao).insert(registrationDto);
        userLogic.register(registrationDto);
    }

    @Test
    public void okRegister() throws DaoException, LogicException, ValidationException {
        Mockito.when(userDao.getUserByLogin(Mockito.anyString())).thenReturn(new ArrayList<>());
        Mockito.doNothing().when(userDao).insert(registrationDto);
        userLogic.register(registrationDto);
    }
}