package by.epam.learn.bahlei.finaltask.dto.service;

import java.math.BigDecimal;

public class ServiceDto {
    private int id;
    private int typeId;
    private String titleEn;
    private String titleRu;
    private String titleBy;
    private String descriptionEn;
    private String descriptionRu;
    private String descriptionBy;
    private BigDecimal price;

    public ServiceDto() {
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

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
}
