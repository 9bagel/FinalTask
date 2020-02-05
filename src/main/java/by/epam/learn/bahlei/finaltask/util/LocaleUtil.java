package by.epam.learn.bahlei.finaltask.util;

import by.epam.learn.bahlei.finaltask.dto.LocaleType;

public class LocaleUtil {

    public static LocaleType getLocaleType(String language) {
        LocaleType languageType;
        if (language.equals("null")) {
            languageType = LocaleType.EN_US;
        } else {
            languageType = LocaleType.valueOf(language.toUpperCase());
        }
        return languageType;
    }
}
