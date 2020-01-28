package by.epam.learn.bahlei.finaltask.command.user;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.factory.LogicFactory;
import by.epam.learn.bahlei.finaltask.logic.order.OrderLogic;
import by.epam.learn.bahlei.finaltask.util.Constants;
import com.google.protobuf.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddToCartCommand implements ActionCommand {

    private LogicFactory logicFactory = LogicFactory.getInstance();
    private OrderLogic orderLogic = logicFactory.getOrderLogic();

    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String path = request.getContextPath();

        if (session.getAttribute(Constants.ID) == null) {
            session.setAttribute(Constants.SESSION_ERROR_ATTRIBUTE, Constants.YOU_NEED_TO_LOGIN_MESSAGE);
            return new Response(Constants.ERROR_JSP, Response.ResponseType.FORWARD);
        } else {
            Integer userId = (Integer) session.getAttribute(Constants.ID);
            int serviceId = Integer.parseInt(request.getParameter(Constants.PARAMETER_SERVICE_ID));
            String language = String.valueOf(session.getAttribute("lang"));

            try {
                orderLogic.addServiceToOrder(userId, serviceId, language);
            } catch (LogicException e) {
                e.printStackTrace();
            } catch (ServiceException e) {
                session.setAttribute(Constants.SESSION_ERROR_ATTRIBUTE, Constants.SERVICE_NOT_FOUND_MESSAGE);
                return new Response(Constants.ERROR_JSP, Response.ResponseType.FORWARD);
            }
        }

        return new Response(path + Constants.SUCCESS_PAGE, Response.ResponseType.REDIRECT);
    }
}
