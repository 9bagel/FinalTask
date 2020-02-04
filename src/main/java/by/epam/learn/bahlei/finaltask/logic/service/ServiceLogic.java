package by.epam.learn.bahlei.finaltask.logic.service;

import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.dao.factory.DaoFactory;
import by.epam.learn.bahlei.finaltask.dao.service.ServiceDao;
import by.epam.learn.bahlei.finaltask.dto.LanguageTypeDto;
import by.epam.learn.bahlei.finaltask.dto.service.ServiceDto;
import by.epam.learn.bahlei.finaltask.dto.service.ServiceTypeDto;
import by.epam.learn.bahlei.finaltask.entity.service.Service;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.util.Constants;
import by.epam.learn.bahlei.finaltask.util.LanguageUtil;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

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

    public List<Service> getServicesByTypeAndLanguage(String serviceName, String language) throws LogicException {
        try {
            LanguageTypeDto languageTypeDto = LanguageUtil.getLanguageTypeByName(language);
            ServiceTypeDto serviceTypeDto = ServiceTypeDto.valueOf(serviceName.toUpperCase());

            return serviceDao.getServicesByTypeAndLanguage(serviceTypeDto, languageTypeDto);
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException(e));
        }
    }


    public List<Service> getAllServices(String language) throws LogicException {
        LanguageTypeDto languageTypeDto = LanguageUtil.getLanguageTypeByName(language);
        try {
            return serviceDao.getAll(languageTypeDto);
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException(e));
        }
    }

    public List<Service> getOrderedServicesByOrderId(int orderId, String language) throws LogicException {
        LanguageTypeDto languageTypeDto = LanguageUtil.getLanguageTypeByName(language);

        try {
            return serviceDao.getOrderedServices(orderId, languageTypeDto);
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

    public List<Service> getServicesByIdsAndLanguage(List<Integer> serviceIds, String language) throws LogicException {
        LanguageTypeDto languageTypeDto = LanguageUtil.getLanguageTypeByName(language);

        try {
            return serviceDao.getServicesByIdsAndLanguage(serviceIds, languageTypeDto);
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException(e));
        }
    }

    public void add(ServiceDto serviceDto) throws LogicException {
        try {
            serviceDao.insert(serviceDto);
        } catch (DaoException e) {
            throw LOGGER.throwing(new LogicException(e));
        }
    }
}
