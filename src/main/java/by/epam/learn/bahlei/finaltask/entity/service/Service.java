package by.epam.learn.bahlei.finaltask.entity.service;

import by.epam.learn.bahlei.finaltask.entity.Entity;

import java.math.BigDecimal;

public class Service implements Entity {
    private int id;
    private int typeId;
    private String title;
    private String description;
    private BigDecimal price;

    public Service() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
