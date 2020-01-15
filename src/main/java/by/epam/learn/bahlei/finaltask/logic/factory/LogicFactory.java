package by.epam.learn.bahlei.finaltask.logic.factory;

import by.epam.learn.bahlei.finaltask.logic.service.ServiceLogic;
import by.epam.learn.bahlei.finaltask.logic.user.UserLogic;

public class LogicFactory {

    private static final LogicFactory INSTANCE = new LogicFactory();

    private LogicFactory() {
    }

    public static LogicFactory getInstance() {
        return INSTANCE;
    }

    public UserLogic getUserLogic() {
        return UserLogic.getInstance();
    }

    public ServiceLogic getServiceLogic() {
        return ServiceLogic.getInstance();
    }
}
