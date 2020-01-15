package by.epam.learn.bahlei.finaltask.entity.service;

import by.epam.learn.bahlei.finaltask.util.Constants;

public enum LanguageType {
    EN_US(Constants.TITLE_COLUMN_NAME_EN, Constants.DESCRIPTION_COLUMN_NAME_EN),
    RU_RU(Constants.TITLE_COLUMN_NAME_RU, Constants.DESCRIPTION_COLUMN_NAME_RU),
    BE_BY(Constants.TITLE_COLUMN_NAME_BY, Constants.DESCRIPTION_COLUMN_NAME_BY);

    private String titleColumnName;
    private String descriptionColumnName;

    LanguageType(String titleColumnName, String descriptionColumnName) {
        this.titleColumnName = titleColumnName;
        this.descriptionColumnName = descriptionColumnName;
    }

    public String getTitleColumnName() {
        return titleColumnName;
    }

    public String getDescriptionColumnName() {
        return descriptionColumnName;
    }
}