package by.epam.learn.bahlei.finaltask.logic.service;

import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.dao.factory.DaoFactory;
import by.epam.learn.bahlei.finaltask.dao.service.ServiceDao;
import by.epam.learn.bahlei.finaltask.entity.service.Service;
import by.epam.learn.bahlei.finaltask.entity.service.ServiceType;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.util.Constants;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ServiceLogic {
    private static final Logger LOGGER = LogManager.getLogger(ServiceLogic.class);
    private static final ServiceLogic INSTANCE = new ServiceLogic();
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private ServiceDao serviceDao = daoFactory.getServiceDao();

    private ServiceLogic() {
    }

    public static ServiceLogic getInstance() {
        return INSTANCE;
    }

    public List<Service> getServicesByTypeName(String serviceTypeName) throws LogicException {
        try {
            ServiceType serviceType = ServiceType.valueOf(serviceTypeName.toUpperCase());
            return serviceDao.getServicesByType(serviceType);
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException(e));
        }
    }


    public List<Service> getAllServices() throws LogicException {
        try {
            return serviceDao.getAll();
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException(e));
        }
    }

    public void isServiceExists(int serviceId) throws LogicException, ServiceException {
        try {
            if (!serviceDao.isServiceExists(serviceId)) {
                throw LOGGER.throwing(new ServiceException(Constants.SERVICE_NOT_FOUND_MESSAGE));
            }
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException(e));
        }
    }

    public List<Service> getServicesById(List<Integer> serviceIds) throws LogicException {
        try {
            return serviceDao.getServicesById(serviceIds);
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException(e));
        }
    }

    public void insert(Service service) throws LogicException {
        try {
            serviceDao.insert(service);
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException(e));
        }
    }

    public void deleteServiceById(int serviceId) throws LogicException {
        try {
            serviceDao.deleteServiceById(serviceId);
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException(e));
        }
    }

    public List<Service> getOrderedServicesByOrderId(int orderId) throws LogicException {
        try {
            return serviceDao.getOrderedServices(orderId);
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException(e));
        }
    }

    public Service getServiceById(int serviceId) throws LogicException {
        try {
            return serviceDao.getServiceById(serviceId);
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException(e));
        }
    }

    public void updateService(Service service) throws LogicException {
        try {
            serviceDao.update(service);
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException(e));
        }
    }
}