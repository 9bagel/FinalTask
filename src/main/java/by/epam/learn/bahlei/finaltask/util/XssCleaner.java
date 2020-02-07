package by.epam.learn.bahlei.finaltask.util;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class XssCleaner {
    public static String clean(String text) {
        return Jsoup.clean(text, Whitelist.basic());
    }
}
