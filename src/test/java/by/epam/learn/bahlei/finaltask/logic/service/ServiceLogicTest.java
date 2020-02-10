package by.epam.learn.bahlei.finaltask.logic.service;

import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.dao.service.ServiceDao;
import by.epam.learn.bahlei.finaltask.entity.service.Service;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import com.google.protobuf.ServiceException;
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

public class ServiceLogicTest {
    @Mock
    private ServiceDao serviceDao;
    @InjectMocks
    private ServiceLogic serviceLogic;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterMethod
    public void afterMethod() {
        serviceLogic = null;
    }

    @Test(expectedExceptions = LogicException.class)
    public void throwLogicExceptionWhenProblemWithDB() throws DaoException, LogicException {
        Mockito.when(serviceDao.getAll()).thenThrow(new DaoException());
        serviceLogic.getAllServices();
    }

    @Test
    public void okGetAllServices() throws DaoException, LogicException {
        List<Service> expectedServices = new ArrayList<>();
        expectedServices.add(new Service());

        Mockito.when(serviceDao.getAll()).thenReturn(expectedServices);
        List<Service> actualServices = serviceLogic.getAllServices();

        Assert.assertEquals(actualServices, expectedServices);
    }

    @Test
    public void okVerifyServiceById() throws DaoException, LogicException, ServiceException {
        Mockito.when(serviceDao.isServiceExists(1)).thenReturn(true);
        serviceLogic.verifyServiceById(1);
    }
}