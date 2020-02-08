package by.epam.learn.bahlei.finaltask.command;

import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.entity.service.Service;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.factory.LogicFactory;
import by.epam.learn.bahlei.finaltask.logic.service.ServiceLogic;
import by.epam.learn.bahlei.finaltask.util.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowMainPageCommand implements ActionCommand {
    private ServiceLogic serviceLogic = LogicFactory.getServiceLogic();

    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        try {
            List<Service> services;
            services = serviceLogic.getAllServices();

            request.setAttribute(Constants.SERVICES, services);
            return new Response(Constants.MAIN_JSP, Response.ResponseType.FORWARD);
        } catch (LogicException e) {
            return new Response(Constants.ERROR_JSP, Response.ResponseType.REDIRECT);
        }
    }
}
