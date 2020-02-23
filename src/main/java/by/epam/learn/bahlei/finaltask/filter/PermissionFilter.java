package by.epam.learn.bahlei.finaltask.filter;

import by.epam.learn.bahlei.finaltask.command.factory.CommandEnum;
import by.epam.learn.bahlei.finaltask.command.factory.CommandFactory;
import by.epam.learn.bahlei.finaltask.entity.user.User;
import by.epam.learn.bahlei.finaltask.entity.user.UserRole;
import by.epam.learn.bahlei.finaltask.util.Constants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.apache.logging.log4j.web.WebLoggerContextUtils.getServletContext;

@WebFilter(urlPatterns = "/controller")
public class PermissionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(Constants.USER);
        UserRole userRole = UserRole.USER;

        if (user != null) {
            userRole = user.getUserRole();
        }

        String requestCommand = CommandFactory.parseRequestCommand(request);
        CommandEnum commandEnum = CommandEnum.valueOf(requestCommand.toUpperCase());

        if (!commandEnum.isRoleAllowed(userRole)) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Constants.ERROR_JSP);
            session.setAttribute(Constants.SESSION_ERROR_ATTRIBUTE, Constants.PERMISSION_ERROR);
            dispatcher.forward(request, response);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
