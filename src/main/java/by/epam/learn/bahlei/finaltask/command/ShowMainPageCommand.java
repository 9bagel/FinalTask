package by.epam.learn.bahlei.finaltask.command;

import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.entity.service.Service;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.factory.LogicFactory;
import by.epam.learn.bahlei.finaltask.logic.service.ServiceLogic;
import by.epam.learn.bahlei.finaltask.util.Constants;
import by.epam.learn.bahlei.finaltask.util.requestutil.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowMainPageCommand implements ActionCommand {
    private final ServiceLogic serviceLogic = LogicFactory.getServiceLogic();

    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        try {
            List<Service> services;

            int limit = RequestUtil.getLimit(request);
            int page = RequestUtil.getPage(request);
            int offset = (page - 1) * limit;
            services = serviceLogic.getLimitServices(offset, limit);
            int serviceCount = serviceLogic.getServiceCount();
            int totalPages = serviceCount / limit + 1;

            request.setAttribute(Constants.SERVICES, services);
            request.setAttribute(Constants.TOTAL_PAGES, totalPages);
            request.setAttribute(Constants.PAGE, page);
            request.setAttribute(Constants.LIMIT, limit);
            return new Response(Constants.MAIN_JSP, Response.ResponseType.FORWARD);
        } catch (LogicException e) {
            return new Response(Constants.ERROR_JSP, Response.ResponseType.FORWARD);
        }
    }
}
