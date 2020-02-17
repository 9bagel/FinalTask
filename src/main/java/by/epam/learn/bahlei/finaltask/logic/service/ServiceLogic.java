package by.epam.learn.bahlei.finaltask.logic.service;

import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
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
    private final ServiceDao serviceDao;

    public ServiceLogic(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }


    public List<Service> getAllServices() throws LogicException {
        try {
            return serviceDao.getAll();
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException(e));
        }
    }

    public void verifyServiceById(int serviceId) throws LogicException, ServiceException {
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
            return serviceDao.getServiceById(serviceId)
                    .stream()
                    .findFirst()
                    .orElseThrow(LogicException::new);
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

    public List<Service> getLimitServices(int offset, int limit) throws LogicException {
        try {
            return serviceDao.getLimitServices(offset, limit);
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException(e));
        }
    }

    public int getServiceCount() throws LogicException {
        try {
            return serviceDao.getServiceCount();
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException(e));
        }
    }

    public List<Service> getServicesByTypeName(String serviceTypeName) throws LogicException {
        try {
            ServiceType serviceType = ServiceType.getByName(serviceTypeName.toUpperCase());
            return serviceDao.getServicesByType(serviceType);
        } catch (DaoException | ServiceException e) {
            throw LOGGER.throwing(new LogicException(e));
        }
    }

    public List<Service> getLimitServicesByType(int offset, int limit, String serviceTypeName) throws LogicException {
        try {
            ServiceType serviceType = ServiceType.getByName(serviceTypeName.toUpperCase());
            return serviceDao.getLimitServicesByType(offset, limit, serviceType);
        } catch (DaoException | ServiceException e) {
            throw LOGGER.throwing(new LogicException(e));
        }
    }
}