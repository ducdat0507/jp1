package global;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Global {
    public static <T> Predicate<T> fuzzyMatch(String needle, Function<T, String> object) {
        Pattern pattern = Pattern.compile(Pattern.quote(needle).replaceAll("\\s", "\\E.*\\Q"), Pattern.CASE_INSENSITIVE);
            return (x) -> pattern.matcher(object.apply(x)).find();
    }

    public static void drawPrompt(String header, String[][] options) {
        System.out.println(
            ANSI.format(ANSI.BOLD, ANSI.UNDERLINE)
            + header + ANSI.format(ANSI.CLEAR));
        for (String[] option : options) {
            System.out.println(
                ANSI.format(ANSI.FG_LIGHT_YELLOW) + option[0] + ")"
                + ANSI.format(ANSI.CLEAR) + " " + option[1]);
        }
    }
}
