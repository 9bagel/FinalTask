package by.epam.learn.bahlei.finaltask.entity.receipt;

import by.epam.learn.bahlei.finaltask.entity.Entity;

public class Receipt implements Entity {
    private int user_id;
    private int status_id;
    private int total;
    private int order_id;
    private String date;

    public Receipt() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
