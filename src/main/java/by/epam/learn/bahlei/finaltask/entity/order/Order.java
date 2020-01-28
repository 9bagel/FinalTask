package by.epam.learn.bahlei.finaltask.entity.order;

import by.epam.learn.bahlei.finaltask.entity.Entity;
import by.epam.learn.bahlei.finaltask.entity.service.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Order implements Entity {
    private int id;
    private int userId;
    private int statusId;
    private Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void addService(Service service){
        services.add(service);
    }

    public List<Service> getServices() {
        return services;
    }
}
