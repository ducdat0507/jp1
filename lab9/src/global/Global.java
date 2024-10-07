package global;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Global {
    public static <T> Predicate<T> fuzzyMatch(String needle, Function<T, String> object) {
        Pattern pattern = Pattern.compile(Pattern.quote(needle).replaceAll("\\s", "\\E.*\\Q"), Pattern.CASE_INSENSITIVE);
            return (x) -> pattern.matcher(object.apply(x)).find();
    }
}
