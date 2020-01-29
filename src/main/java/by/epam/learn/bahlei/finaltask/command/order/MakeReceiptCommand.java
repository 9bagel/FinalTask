package by.epam.learn.bahlei.finaltask.command.order;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.entity.receipt.Receipt;
import by.epam.learn.bahlei.finaltask.entity.receipt.ReceiptStatus;
import by.epam.learn.bahlei.finaltask.entity.service.Service;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.factory.LogicFactory;
import by.epam.learn.bahlei.finaltask.logic.order.OrderLogic;
import by.epam.learn.bahlei.finaltask.logic.receipt.ReceiptLogic;
import by.epam.learn.bahlei.finaltask.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class MakeReceiptCommand implements ActionCommand {
    private LogicFactory logicFactory = LogicFactory.getInstance();
    private ReceiptLogic receiptLogic = logicFactory.getReceiptLogic();
    private OrderLogic orderLogic = logicFactory.getOrderLogic();

    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();

        try {
            int userId = (int) session.getAttribute(Constants.ID);
            String language = String.valueOf(session.getAttribute(Constants.LOCALE));

            if (orderLogic.getOrderedServices(userId, language).isEmpty()){
                session.setAttribute(Constants.SESSION_ERROR_ATTRIBUTE, Constants.EMPTY_CART_ERROR);
                return new Response(Constants.ERROR_JSP, Response.ResponseType.FORWARD);
            }

            receiptLogic.createReceiptTransaction(createNewReceipt(request, session));

            session.setAttribute(Constants.SUCCESS_MESSAGE, Constants.RECEIPT_CREATED_MESSAGE);
            return new Response(request.getContextPath() + Constants.SUCCESS_PAGE, Response.ResponseType.REDIRECT);
        } catch (LogicException e) {
            session.setAttribute(Constants.SESSION_ERROR_ATTRIBUTE, Constants.CREATE_RECEIPT_ERROR);
            return new Response(Constants.ERROR_JSP, Response.ResponseType.FORWARD);
        }

    }

    private Receipt createNewReceipt(HttpServletRequest request, HttpSession session) {
        Receipt receipt = new Receipt();
        receipt.setUser_id((int) session.getAttribute(Constants.ID));
        receipt.setOrder_id((int) session.getAttribute(Constants.SESSION_BASKET_ID));
        receipt.setDate(request.getParameter(Constants.DATE));
        receipt.setTotal(Integer.parseInt(session.getAttribute(Constants.TOTAL).toString()));
        receipt.setStatus_id(ReceiptStatus.NOT_PAID.getId());
        return receipt;
    }

}
