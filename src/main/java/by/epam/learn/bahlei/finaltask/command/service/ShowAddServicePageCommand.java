package by.epam.learn.bahlei.finaltask.command.service;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.entity.service.ServiceType;
import by.epam.learn.bahlei.finaltask.util.Constants;

import javax.servlet.http.HttpServletRequest;

public class ShowAddServicePageCommand implements ActionCommand {
    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        request.setAttribute(Constants.SERVICE_TYPES, ServiceType.values());
        return new Response(Constants.ADD_SERVICE_JSP, Response.ResponseType.FORWARD);
    }
}