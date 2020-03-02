package by.epam.learn.bahlei.finaltask.logic.order;

import by.epam.learn.bahlei.finaltask.dao.exception.DaoException;
import by.epam.learn.bahlei.finaltask.dao.order.OrderDao;
import by.epam.learn.bahlei.finaltask.entity.order.Order;
import by.epam.learn.bahlei.finaltask.entity.user.User;
import by.epam.learn.bahlei.finaltask.logic.exception.LogicException;
import by.epam.learn.bahlei.finaltask.logic.exception.OrderException;
import by.epam.learn.bahlei.finaltask.logic.exception.UserException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

public class OrderLogicTest {
    @Mock
    private OrderDao orderDao;
    @InjectMocks
    private OrderLogic orderLogic;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterMethod
    public void afterMethod() {
        orderLogic = null;
    }

    @Test(expectedExceptions = OrderException.class)
    public void throwOrderExceptionGetOrdersByUserIdWhenNoOrdersFound() throws DaoException, LogicException, OrderException {
        Mockito.when(orderDao.getOrdersByUserId(Mockito.anyInt())).thenReturn(new ArrayList<>());

        orderLogic.getOrdersByUserId(42);
    }

    @Test(expectedExceptions = OrderException.class)
    public void throwOrderExceptionGetOrderByIdWhenNoOrderFound() throws DaoException, LogicException, OrderException {
        Mockito.when(orderDao.getOrderById(Mockito.anyInt())).thenReturn(new ArrayList<>());

        orderLogic.getOrderById(42);
    }

    @Test(expectedExceptions = OrderException.class)
    public void throwOrderExceptionGetOrderByUserIdAndOrderIdWhenNoOrderFound() throws DaoException, OrderException {
        Mockito.when(orderDao.getOrderByUserIdAndOrderId(Mockito.anyInt(), Mockito.anyInt())).thenReturn(new ArrayList<>());

        orderLogic.getOrderByUserIdAndOrderId(42, 42);
    }

    @Test(expectedExceptions = UserException.class)
    public void throwUserExceptionCheckIfUserHasBalanceToPay() throws UserException {
        User user = new User();
        user.setBalance(BigDecimal.valueOf(0));

        Order order = new Order();
        order.setTotal(BigDecimal.valueOf(10));

        orderLogic.checkIfUserHasBalanceToPay(user, order);
    }
}
