package by.epam.learn.bahlei.finaltask.command.user;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.entity.user.User;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.factory.LogicFactory;
import by.epam.learn.bahlei.finaltask.logic.user.UserLogic;
import by.epam.learn.bahlei.finaltask.util.Constants;
import by.epam.learn.bahlei.finaltask.util.XssCleaner;
import by.epam.learn.bahlei.finaltask.util.validator.exception.ValidationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class MakeDepositCommand implements ActionCommand {
    private UserLogic userLogic = LogicFactory.getUserLogic();

    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        BigDecimal amount = new BigDecimal(XssCleaner.clean((request.getParameter(Constants.AMOUNT))));
        User user = (User) session.getAttribute(Constants.USER);
        try {
            userLogic.makeDeposit(user, amount);

            session.setAttribute(Constants.SESSION_SUCCESS_ATTRIBUTE, Constants.REFILL_MESSAGE);
            return new Response(request.getHeader(Constants.REFERER), Response.ResponseType.REDIRECT);
        } catch (LogicException | ValidationException e) {
            session.setAttribute(Constants.SESSION_ERROR_ATTRIBUTE, Constants.REFILL_ERROR);
            return new Response(request.getHeader(Constants.REFERER), Response.ResponseType.REDIRECT);
        }
    }
}