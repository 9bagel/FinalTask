package by.epam.learn.bahlei.finaltask.entity.order;

import by.epam.learn.bahlei.finaltask.logic.exception.OrderException;
import by.epam.learn.bahlei.finaltask.util.Constants;

import java.util.HashMap;
import java.util.Map;

public enum OrderStatus {
    NEW(1, Constants.ORDER_STATUS_NEW),
    IN_PROGRESS(2, Constants.ORDER_STATUS_IN_PROGRESS),
    COMPLETED(3, Constants.ORDER_STATUS_COMPLETED),
    PAID(4, Constants.ORDER_STATUS_PAID),
    CANCELED(5, Constants.ORDER_STATUS_CANCELED);

    private static final Map<Integer, OrderStatus> map;
    private final int id;
    private String name;

    static {
        map = new HashMap<>();
        for (OrderStatus orderStatus : OrderStatus.values()) {
            map.put(orderStatus.id, orderStatus);
        }
    }

    OrderStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static OrderStatus getOrderStatusById(int orderId) throws OrderException {
        OrderStatus orderStatus = map.get(orderId);
        if (orderStatus == null) {
            throw new OrderException();
        }
        return orderStatus;
    }
}