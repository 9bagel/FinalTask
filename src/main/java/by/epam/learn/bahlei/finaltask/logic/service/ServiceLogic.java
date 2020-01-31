package by.epam.learn.bahlei.finaltask.logic.service;

import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.dao.factory.DaoFactory;
import by.epam.learn.bahlei.finaltask.dao.service.ServiceDao;
import by.epam.learn.bahlei.finaltask.dto.LanguageTypeDto;
import by.epam.learn.bahlei.finaltask.dto.service.ServiceTypeDto;
import by.epam.learn.bahlei.finaltask.entity.service.Service;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.util.LanguageUtil;
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
}
