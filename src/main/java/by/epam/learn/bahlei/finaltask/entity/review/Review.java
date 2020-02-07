package by.epam.learn.bahlei.finaltask.entity.review;

import by.epam.learn.bahlei.finaltask.entity.Entity;

public class Review implements Entity {
    private int id;
    private int orderId;
    private int userId;
    private String message;

    public Review() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}