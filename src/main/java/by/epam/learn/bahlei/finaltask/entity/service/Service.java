package by.epam.learn.bahlei.finaltask.entity.service;

import by.epam.learn.bahlei.finaltask.entity.Entity;

import java.math.BigDecimal;

public class Service implements Entity {
    private int id;
    private ServiceType serviceType;
    private BigDecimal price;
    private String descriptionEn;
    private String descriptionBy;
    private String descriptionRu;
    private String titleBy;
    private String titleRu;
    private String titleEn;

    public Service() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public String getDescriptionBy() {
        return descriptionBy;
    }

    public void setDescriptionBy(String descriptionBy) {
        this.descriptionBy = descriptionBy;
    }

    public String getDescriptionRu() {
        return descriptionRu;
    }

    public void setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
    }

    public String getTitleBy() {
        return titleBy;
    }

    public void setTitleBy(String titleBy) {
        this.titleBy = titleBy;
    }

    public String getTitleRu() {
        return titleRu;
    }

    public void setTitleRu(String titleRu) {
        this.titleRu = titleRu;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }
}
