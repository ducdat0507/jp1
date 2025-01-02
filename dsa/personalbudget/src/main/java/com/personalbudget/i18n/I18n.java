package com.personalbudget.i18n;

import java.util.Collections;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

public class I18n {
    public static final Set<Locale> supportedLocales = Collections.unmodifiableSet(
        Set.of(
            Locale.ENGLISH, 
            Locale.forLanguageTag("vi") // Vietnamese
        )
    );

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
