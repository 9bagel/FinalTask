package by.epam.learn.bahlei.finaltask.command.service;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.entity.service.Service;
import by.epam.learn.bahlei.finaltask.entity.service.ServiceType;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.factory.LogicFactory;
import by.epam.learn.bahlei.finaltask.logic.service.ServiceLogic;
import by.epam.learn.bahlei.finaltask.util.Constants;
import by.epam.learn.bahlei.finaltask.util.XssCleaner;
import by.epam.learn.bahlei.finaltask.util.requestutil.RequestUtil;
import com.google.protobuf.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowServiceTypePageCommand implements ActionCommand {
    private final ServiceLogic serviceLogic = LogicFactory.getServiceLogic();

    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        try {
            int page = RequestUtil.getPage(request);
            int limit = RequestUtil.getLimit(request);
            int offset = (page - 1) * limit;

            ServiceType serviceType = RequestUtil.getServiceType(request);
            List<Service> services = serviceLogic.getLimitServices(serviceType, offset, limit);

            int serviceCount = serviceLogic.getServiceCount(serviceType);
            int totalPages = serviceCount / limit + 1;

            request.setAttribute(Constants.SERVICES, services);
            request.setAttribute(Constants.LIMIT, limit);
            request.setAttribute(Constants.TOTAL_PAGES, totalPages);
            request.setAttribute(Constants.PAGE, page);

            return new Response(Constants.SERVICE_JSP, Response.ResponseType.FORWARD);
        } catch (LogicException | ServiceException e) {
            return new Response(Constants.ERROR_JSP, Response.ResponseType.FORWARD);
        }
    }

}
