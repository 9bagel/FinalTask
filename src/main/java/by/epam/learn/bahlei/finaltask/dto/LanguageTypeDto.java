package by.epam.learn.bahlei.finaltask.dto;

import by.epam.learn.bahlei.finaltask.util.Constants;

public enum LanguageTypeDto {
    EN_US(Constants.TITLE_EN, Constants.DESCRIPTION_EN),
    RU_RU(Constants.TITLE_RU, Constants.DESCRIPTION_RU),
    BE_BY(Constants.TITLE_BY, Constants.DESCRIPTION_BY);

    private String titleColumnName;
    private String descriptionColumnName;

    LanguageTypeDto(String titleColumnName, String descriptionColumnName) {
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