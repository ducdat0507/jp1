package com.personalbudget.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18n {
    private static Locale locale;
    private static ResourceBundle bundle;

    public static void load(Locale locale) {
        I18n.locale = locale;
        bundle = ResourceBundle.getBundle("com.personalbudget.i18n.i18n", locale);
    }

    public static String get(String key) {
        return bundle.getString(key);
    }

    public static ResourceBundle getBundle() {
        return bundle;
    }

    public static Locale getLocale() {
        return locale;
    }
}
