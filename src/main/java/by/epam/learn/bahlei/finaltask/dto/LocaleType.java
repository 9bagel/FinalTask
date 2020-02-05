package by.epam.learn.bahlei.finaltask.dto;

import by.epam.learn.bahlei.finaltask.util.Constants;

public enum LocaleType {
    EN_US(Constants.TITLE_EN, Constants.DESCRIPTION_EN),
    RU_RU(Constants.TITLE_RU, Constants.DESCRIPTION_RU),
    BE_BY(Constants.TITLE_BY, Constants.DESCRIPTION_BY);

    private String titleName;
    private String descriptionName;

    LocaleType(String titleName, String descriptionName) {
        this.titleName = titleName;
        this.descriptionName = descriptionName;
    }

    public String getTitleName() {
        return titleName;
    }

    public String getDescriptionName() {
        return descriptionName;
    }
}