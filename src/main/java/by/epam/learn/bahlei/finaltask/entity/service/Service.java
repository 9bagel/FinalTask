package by.epam.learn.bahlei.finaltask.entity.service;

import by.epam.learn.bahlei.finaltask.entity.Entity;

public class Service implements Entity {
    private int id;
    private int typeId;
    private String titleEn;
    private String titleRu;
    private String titleBy;
    private String descriptionEn;
    private String descriptionRu;
    private String descriptionBy;
    private int price;

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

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getTitleRu() {
        return titleRu;
    }

    public void setTitleRu(String titleRu) {
        this.titleRu = titleRu;
    }

    public String getTitleBy() {
        return titleBy;
    }

    public void setTitleBy(String titleBy) {
        this.titleBy = titleBy;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public String getDescriptionRu() {
        return descriptionRu;
    }

    public void setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
    }

    public String getDescriptionBy() {
        return descriptionBy;
    }

    public void setDescriptionBy(String descriptionBy) {
        this.descriptionBy = descriptionBy;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
