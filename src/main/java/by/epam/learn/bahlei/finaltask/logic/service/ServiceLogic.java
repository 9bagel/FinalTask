package by.epam.learn.bahlei.finaltask.logic.service;

import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.dao.factory.DaoFactory;
import by.epam.learn.bahlei.finaltask.dao.service.ServiceDao;
import by.epam.learn.bahlei.finaltask.dto.LanguageTypeDto;
import by.epam.learn.bahlei.finaltask.dto.service.ServiceTypeDto;
import by.epam.learn.bahlei.finaltask.entity.service.Service;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.util.LanguageUtil;

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

    public List<Service> getServicesByTypeAndLanguage(String serviceName, String language) throws LogicException {
        try {
            LanguageTypeDto languageTypeDto = LanguageUtil.getLanguageTypeByName(language);
            ServiceTypeDto serviceTypeDto = ServiceTypeDto.valueOf(serviceName.toUpperCase());

            return serviceDao.getServicesByTypeAndLanguage(serviceTypeDto, languageTypeDto);
        } catch (DaoException e) {
            throw new LogicException();
        }
    }


}
