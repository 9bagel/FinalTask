package by.epam.learn.bahlei.finaltask.logic.service;

import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.dao.factory.DaoFactory;
import by.epam.learn.bahlei.finaltask.dao.service.ServiceDao;
import by.epam.learn.bahlei.finaltask.dto.LanguageTypeDto;
import by.epam.learn.bahlei.finaltask.dto.service.ServiceTypeDto;
import by.epam.learn.bahlei.finaltask.entity.service.Service;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;

import java.util.List;

public class ServiceLogic {
    private static final ServiceLogic INSTANCE = new ServiceLogic();
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private ServiceDao serviceDao = daoFactory.getServiceDao();

    private ServiceLogic() {
    }

    public static ServiceLogic getInstance() {
        return INSTANCE;
    }

    public List<Service> getServicesByType(String serviceName, String language) throws LogicException {
        LanguageTypeDto languageType = getLanguageTypeByName(language);
        ServiceTypeDto serviceTypeDto = ServiceTypeDto.valueOf(serviceName.toUpperCase());

        try {
            return serviceDao.getServicesByTypeAndLanguage(serviceTypeDto, languageType);
        } catch (DaoException e) {
            throw new LogicException();
        }
    }

    private LanguageTypeDto getLanguageTypeByName(String language) {
        LanguageTypeDto languageType;
        if (language.equals("null")) {
            languageType = LanguageTypeDto.EN_US;
        } else {
            languageType = LanguageTypeDto.valueOf(language.toUpperCase());
        }
        return languageType;
    }
}
