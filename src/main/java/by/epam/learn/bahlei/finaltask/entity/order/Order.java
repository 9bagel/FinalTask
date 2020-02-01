package by.epam.learn.bahlei.finaltask.entity.order;

import by.epam.learn.bahlei.finaltask.entity.Entity;
import by.epam.learn.bahlei.finaltask.entity.service.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Order implements Entity {
    private int id;
    private int userId;
    private int statusId;
    private BigDecimal total;
    private Timestamp date;

    private List<Service> services = new ArrayList<>();

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public void addService(Service service) {
        services.add(service);
    }

    public List<Service> getServices() {
        return services;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
