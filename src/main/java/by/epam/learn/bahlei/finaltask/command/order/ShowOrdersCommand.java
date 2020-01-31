package by.epam.learn.bahlei.finaltask.command.order;

import by.epam.learn.bahlei.finaltask.command.ActionCommand;
import by.epam.learn.bahlei.finaltask.command.Response;
import by.epam.learn.bahlei.finaltask.command.exception.CommandException;
import by.epam.learn.bahlei.finaltask.entity.receipt.Receipt;
import by.epam.learn.bahlei.finaltask.entity.service.Service;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.factory.LogicFactory;
import by.epam.learn.bahlei.finaltask.logic.order.OrderLogic;
import by.epam.learn.bahlei.finaltask.logic.receipt.ReceiptLogic;
import by.epam.learn.bahlei.finaltask.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowOrdersCommand implements ActionCommand {
    private LogicFactory logicFactory = LogicFactory.getInstance();
    private ReceiptLogic receiptLogic = logicFactory.getReceiptLogic();

    @Override
    public Response execute(HttpServletRequest request) throws CommandException {
        try {
            HttpSession session = request.getSession();
            int userId = (int) session.getAttribute(Constants.ID);
            List<Receipt> receipts = receiptLogic.getReceiptsByUserId(userId);

            request.setAttribute(Constants.ATTRIBUTE_RECEIPTS, receipts);
            return new Response(Constants.RECEIPTS_JSP, Response.ResponseType.FORWARD);
        } catch (LogicException e) {
            return new Response(Constants.ERROR_JSP, Response.ResponseType.FORWARD);
        }
    }
}
