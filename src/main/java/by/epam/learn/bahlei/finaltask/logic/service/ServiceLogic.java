package by.epam.learn.bahlei.finaltask.logic.service;

import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.dao.factory.DaoFactory;
import by.epam.learn.bahlei.finaltask.dao.service.ServiceDao;
import by.epam.learn.bahlei.finaltask.entity.service.LanguageType;
import by.epam.learn.bahlei.finaltask.entity.service.Service;
import by.epam.learn.bahlei.finaltask.entity.service.ServiceType;
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
        LanguageType languageType;
        if (language.equals("null")) {
            languageType = LanguageType.EN_US;
        } else {
            languageType = LanguageType.valueOf(language.toUpperCase());
        }
        ServiceType serviceType = ServiceType.valueOf(serviceName.toUpperCase());
        Service service = new Service();
        service.setLanguageType(languageType);
        service.setServiceType(serviceType);

        try {

            return serviceDao.getServicesByType(service);
        } catch (DaoException e) {
            throw new LogicException();
        }
    }
}
