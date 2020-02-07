package by.epam.learn.bahlei.finaltask.command.service;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.entity.service.Service;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.factory.LogicFactory;
import by.epam.learn.bahlei.finaltask.logic.service.ServiceLogic;
import by.epam.learn.bahlei.finaltask.util.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowServiceTypePageCommand implements ActionCommand {
    private LogicFactory logicFactory = LogicFactory.getInstance();
    private ServiceLogic serviceLogic = logicFactory.getServiceLogic();

    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        try {
            List<Service> services;
            String serviceTypeName = request.getParameter(Constants.SERVICE_TYPE);
            services = serviceLogic.getServicesByTypeName(serviceTypeName);

            request.setAttribute(Constants.SERVICES, services);
            return new Response(Constants.SERVICE_JSP, Response.ResponseType.FORWARD);
        } catch (LogicException e) {
            return new Response(Constants.ERROR_JSP, Response.ResponseType.REDIRECT);
        }
    }

}
