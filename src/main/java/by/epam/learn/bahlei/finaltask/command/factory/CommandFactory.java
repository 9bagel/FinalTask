package by.epam.learn.bahlei.finaltask.command.factory;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.EmptyCommand;
import by.epam.learn.bahlei.finaltask.util.UrlEncoder;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {
    private static final String COMMAND = "command";

    public ActionCommand defineCommand(HttpServletRequest request) {

        ActionCommand command = new EmptyCommand();
        String action = request.getParameter(COMMAND);
        String pathInfo = request.getPathInfo();

        if (action == null || action.isEmpty()) {
            if (pathInfo == null || pathInfo.isEmpty()) {
                return command;
            } else {
                action = UrlEncoder.getPath(pathInfo);
            }
        }

        CommandEnum commandEnum = CommandEnum.valueOf(action.toUpperCase());
        command = commandEnum.getCurrentCommand();

        return command;
    }

}
