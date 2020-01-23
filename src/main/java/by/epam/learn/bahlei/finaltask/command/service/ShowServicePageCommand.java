package by.epam.learn.bahlei.finaltask.command.service;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.entity.service.LocalisedService;
import by.epam.learn.bahlei.finaltask.entity.service.Service;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.factory.LogicFactory;
import by.epam.learn.bahlei.finaltask.logic.service.ServiceLogic;
import by.epam.learn.bahlei.finaltask.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowServicePageCommand implements ActionCommand {
    private LogicFactory logicFactory = LogicFactory.getInstance();
    private ServiceLogic serviceLogic = logicFactory.getServiceLogic();

    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        List<Service> services;
        List<LocalisedService> localisedServices;
        HttpSession session = request.getSession();
        int typeId = Integer.parseInt(request.getParameter("type_id"));
        String locale = String.valueOf(session.getAttribute("lang"));

        try {
            services = serviceLogic.getServicesByTypeId(typeId);
            localisedServices = serviceLogic.parseLocalisedServices(services, locale);
            request.setAttribute("services", services);
            return new Response(Constants.SERVICE_JSP, Response.ResponseType.FORWARD);
        } catch (LogicException e) {
            return new Response(Constants.ERROR_PAGE, Response.ResponseType.REDIRECT);
        }


    }

}
