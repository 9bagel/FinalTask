package by.epam.learn.bahlei.finaltask.command.factory;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.util.Constants;
import by.epam.learn.bahlei.finaltask.util.UrlEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {
    private static final Logger LOGGER = LogManager.getLogger(CommandFactory.class);

    public static ActionCommand defineCommand(HttpServletRequest request) throws CommandException {
        String requestCommand = parseRequestCommand(request);

        try {
            CommandEnum commandEnum = CommandEnum.valueOf(requestCommand.toUpperCase());
            return commandEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            throw LOGGER.throwing(new CommandException(e));
        }
    }

    public static String parseRequestCommand(HttpServletRequest request) {
        String requestCommand = request.getParameter(Constants.COMMAND);

        if (requestCommand == null || requestCommand.isEmpty()) {
            requestCommand = parsePathInfo(request);
        }
        return requestCommand;
    }

    private static String parsePathInfo(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.isEmpty()) {
            return Constants.MAIN;
        } else {
            return UrlEncoder.getPath(pathInfo);
        }
    }
}
