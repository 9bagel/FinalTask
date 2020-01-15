package by.epam.learn.bahlei.finaltask.controller;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.factory.CommandFactory;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {

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
        ActionCommand command = commandFactory.defineCommand(request);
        try {
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
            //TODO
        }
    }
}