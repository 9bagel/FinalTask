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
import by.epam.learn.bahlei.finaltask.util.requestutil.RequestUtil;
import com.google.protobuf.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class UpdateServiceCommand implements ActionCommand {
    private ServiceLogic serviceLogic = LogicFactory.getServiceLogic();

    @Override
    public Response execute(HttpServletRequest request) throws CommandException {

        try {
            Service service = RequestUtil.parseService(request);
            serviceLogic.updateService(service);

            request.getSession().setAttribute(Constants.SESSION_SUCCESS_ATTRIBUTE, Constants.SERVICE_UPDATE_MESSAGE);
            return new Response(request.getHeader(Constants.REFERER), Response.ResponseType.REDIRECT);
        } catch (LogicException | ServiceException e) {
            request.getSession().setAttribute(Constants.SESSION_ERROR_ATTRIBUTE, Constants.SERVICE_UPDATE_ERROR);
            return new Response(request.getHeader(Constants.REFERER), Response.ResponseType.REDIRECT);
        }
    }
}
