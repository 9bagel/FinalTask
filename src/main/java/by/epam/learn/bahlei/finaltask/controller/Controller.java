package by.epam.learn.bahlei.finaltask.controller;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.command.factory.CommandFactory;
import by.epam.learn.bahlei.finaltask.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/controller", "/controller/*"})
public class Controller extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommandFactory commandFactory = new CommandFactory();
        try {
            ActionCommand command = commandFactory.defineCommand(request);
            Response commandResponse = command.execute(request);

            switch (commandResponse.getType()) {
                case FORWARD:
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(commandResponse.getPath());
                    dispatcher.forward(request, response);
                    break;
                case REDIRECT:
                    response.sendRedirect(commandResponse.getPath());
                    break;
            }
        } catch (CommandException e) {
            LOGGER.error(e);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Constants.ERROR_JSP);
            dispatcher.forward(request, response);
        }
    }
}