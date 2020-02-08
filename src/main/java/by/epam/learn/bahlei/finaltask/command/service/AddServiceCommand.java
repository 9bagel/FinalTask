package by.epam.learn.bahlei.finaltask.command.service;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.factory.LogicFactory;
import by.epam.learn.bahlei.finaltask.logic.service.ServiceLogic;
import by.epam.learn.bahlei.finaltask.util.Constants;
import by.epam.learn.bahlei.finaltask.util.requestutil.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddServiceCommand implements ActionCommand {
    private ServiceLogic serviceLogic = LogicFactory.getServiceLogic();

    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        try {
            serviceLogic.insert(RequestUtil.parseService(request));

            session.setAttribute(Constants.SESSION_SUCCESS_ATTRIBUTE, Constants.SERVICE_ADD_MESSAGE);
            return new Response(request.getHeader(Constants.REFERER), Response.ResponseType.REDIRECT);
        } catch (LogicException e) {
            session.setAttribute(Constants.SESSION_ERROR_ATTRIBUTE, Constants.SERVICE_ADD_ERROR);
            return new Response(request.getHeader(Constants.REFERER), Response.ResponseType.REDIRECT);
        }
    }
}
