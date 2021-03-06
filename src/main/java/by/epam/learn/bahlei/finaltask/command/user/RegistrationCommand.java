package by.epam.learn.bahlei.finaltask.command.user;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.dto.RegistrationDto;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.exception.UserException;
import by.epam.learn.bahlei.finaltask.logic.factory.LogicFactory;
import by.epam.learn.bahlei.finaltask.logic.user.UserLogic;
import by.epam.learn.bahlei.finaltask.util.Constants;
import by.epam.learn.bahlei.finaltask.util.requestutil.RequestUtil;
import by.epam.learn.bahlei.finaltask.util.validator.Validator;
import by.epam.learn.bahlei.finaltask.util.validator.exception.ValidationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegistrationCommand implements ActionCommand {
    private final UserLogic userLogic = LogicFactory.getUserLogic();

    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        RegistrationDto registrationDto = RequestUtil.parseRegistrationDto(request);

        try {
            Validator.validateRegistration(registrationDto);
            userLogic.register(registrationDto);

            session.setAttribute(Constants.SESSION_SUCCESS_ATTRIBUTE, Constants.REGISTRATION_MESSAGE);
            return new Response(request.getContextPath(), Response.ResponseType.REDIRECT);
        } catch (LogicException e) {
            session.setAttribute(Constants.SESSION_ERROR_ATTRIBUTE, Constants.REGISTRATION_ERROR);
            return new Response(request.getHeader(Constants.REFERER), Response.ResponseType.REDIRECT);
        } catch (ValidationException | UserException e) {
            session.setAttribute(Constants.SESSION_ERROR_ATTRIBUTE, e.getMessage());
            return new Response(request.getHeader(Constants.REFERER), Response.ResponseType.REDIRECT);
        }
    }
}