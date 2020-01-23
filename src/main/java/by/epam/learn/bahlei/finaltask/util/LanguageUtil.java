package by.epam.learn.bahlei.finaltask.util;

import by.epam.learn.bahlei.finaltask.dto.LanguageTypeDto;

public class LanguageUtil {

    public static LanguageTypeDto getLanguageTypeByName(String language) {
        LanguageTypeDto languageType;
        if (language.equals("null")) {
            languageType = LanguageTypeDto.EN_US;
        } else {
            languageType = LanguageTypeDto.valueOf(language.toUpperCase());
        }
        return languageType;
    }
}
