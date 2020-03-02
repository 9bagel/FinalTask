package by.epam.learn.bahlei.finaltask.logic.service;

import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.dao.service.ServiceDao;
import by.epam.learn.bahlei.finaltask.entity.service.Service;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.util.Constants;
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
    public void exceptionGetAllServices() throws DaoException, LogicException {
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
        Mockito.when(serviceDao.isServiceExists(Mockito.anyInt())).thenReturn(true);
        serviceLogic.verifyServiceById(1);
    }

    @Test(expectedExceptions = ServiceException.class,
            expectedExceptionsMessageRegExp = Constants.SERVICE_NOT_FOUND_MESSAGE)
    public void serviceExceptionVerifyServiceById() throws DaoException, LogicException, ServiceException {
        Mockito.when(serviceDao.isServiceExists(Mockito.anyInt())).thenReturn(false);
        serviceLogic.verifyServiceById(1);
    }

    @Test(expectedExceptions = LogicException.class)
    public void logicExceptionVerifyServiceById() throws DaoException, LogicException, ServiceException {
        Mockito.when(serviceDao.isServiceExists(Mockito.anyInt())).thenThrow(new DaoException());
        serviceLogic.verifyServiceById(1);
    }

    @Test
    public void okGetServiceListById() throws DaoException, LogicException, ServiceException {
        ArrayList<Service> services = new ArrayList<>();
        services.add(new Service());

        Mockito.when(serviceDao.getServicesById(Mockito.any())).thenReturn(services);
        serviceLogic.getServiceListById(new ArrayList<>());
    }

    @Test(expectedExceptions = LogicException.class)
    public void logicExceptionGetServiceListById() throws DaoException, LogicException, ServiceException {
        Mockito.when(serviceDao.getServicesById(Mockito.any())).thenThrow(new DaoException());
        serviceLogic.getServiceListById(new ArrayList<>());
    }

    @Test(expectedExceptions = ServiceException.class)
    public void serviceExceptionGetServiceListById() throws DaoException, LogicException, ServiceException {
        Mockito.when(serviceDao.getServicesById(Mockito.any())).thenReturn(new ArrayList<>());
        serviceLogic.getServiceListById(new ArrayList<>());
    }

    @Test(expectedExceptions = LogicException.class)
    public void logicExceptionGetServiceByIdWhenNoSuchId() throws DaoException, LogicException{
        Mockito.when(serviceDao.getServiceById(Mockito.anyInt())).thenReturn(new ArrayList<>());
        serviceLogic.getServiceById(3);
    }

    @Test(expectedExceptions = ServiceException.class,
    expectedExceptionsMessageRegExp = Constants.SERVICE_NOT_FOUND_MESSAGE)
    public void logicExceptionGetServicesLikeWhenNoServicesLike() throws DaoException, LogicException, ServiceException {
        Mockito.when(serviceDao.getServicesLike(Mockito.anyString())).thenReturn(new ArrayList<>());
        serviceLogic.getServicesLike("TAXI");
    }
}