package Global;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Global {
    public static Pattern fuzzyMatchPattern(String needle) {
        return Pattern.compile(
            Pattern.quote(needle).replaceAll("\\s", "\\E.*\\Q"), 
            Pattern.CASE_INSENSITIVE
        );
    }
    public static Predicate<String> fuzzyMatch(String needle) {
        Pattern pattern = fuzzyMatchPattern(needle);
            return (x) -> pattern.matcher(x).find();
    }
    public static <T> Predicate<T> fuzzyMatch(String needle, Function<T, String> object) {
        Pattern pattern = fuzzyMatchPattern(needle);
            return (x) -> pattern.matcher(object.apply(x)).find();
    }
}
