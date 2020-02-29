package by.epam.learn.bahlei.finaltask.command.service;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.entity.service.Service;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.factory.LogicFactory;
import by.epam.learn.bahlei.finaltask.logic.service.ServiceLogic;
import by.epam.learn.bahlei.finaltask.util.Constants;
import by.epam.learn.bahlei.finaltask.util.XssCleaner;
import com.google.protobuf.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SearchServiceCommand implements ActionCommand {
    private final ServiceLogic serviceLogic = LogicFactory.getServiceLogic();

    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        try {
            String searchPhrase = XssCleaner.clean(request.getParameter(Constants.SEARCH_PHRASE));
            List<Service> services = serviceLogic.getServicesLike(searchPhrase);

            request.setAttribute(Constants.SERVICES, services);
            return new Response(Constants.SEARCH_JSP, Response.ResponseType.FORWARD);
        } catch (LogicException e) {
            return new Response(Constants.ERROR_JSP, Response.ResponseType.FORWARD);
        } catch (ServiceException e) {
            request.getSession().setAttribute(Constants.SESSION_ERROR_ATTRIBUTE, e.getMessage());
            return new Response(request.getHeader(Constants.REFERER), Response.ResponseType.REDIRECT);
        }
    }
}
