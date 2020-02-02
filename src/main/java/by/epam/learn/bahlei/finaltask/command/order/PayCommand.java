package by.epam.learn.bahlei.finaltask.command.order;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.logic.factory.LogicFactory;
import by.epam.learn.bahlei.finaltask.logic.order.OrderLogic;
import by.epam.learn.bahlei.finaltask.logic.user.UserLogic;

import javax.servlet.http.HttpServletRequest;

public class PayCommand implements ActionCommand {
    private LogicFactory logicFactory = LogicFactory.getInstance();
    private OrderLogic orderLogic = logicFactory.getOrderLogic();
    private UserLogic userLogic = logicFactory.getUserLogic();

    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        return null;
    }
}
