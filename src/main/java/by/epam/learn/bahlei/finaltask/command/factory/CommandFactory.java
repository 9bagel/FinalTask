package by.epam.learn.bahlei.finaltask.command.factory;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.ShowMainPageCommand;
import by.epam.learn.bahlei.finaltask.command.error.ShowErrorPageCommand;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.util.Constants;
import by.epam.learn.bahlei.finaltask.util.UrlEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {
    private static final Logger LOGGER = LogManager.getLogger(CommandFactory.class);

    public ActionCommand defineCommand(HttpServletRequest request) throws CommandException {
        ActionCommand command = new ShowMainPageCommand();
        String action = request.getParameter(Constants.COMMAND);
        String pathInfo = request.getPathInfo();

        if (action == null || action.isEmpty()) {
            if (pathInfo == null || pathInfo.isEmpty()) {
                return command;
            } else {
                action = UrlEncoder.getPath(pathInfo);
            }
        }
        try {
            CommandEnum commandEnum = CommandEnum.valueOf(action.toUpperCase());
            command = commandEnum.getCurrentCommand();
            return command;
        } catch (IllegalArgumentException e) {
            throw LOGGER.throwing(new CommandException(e));
        }
    }
}
