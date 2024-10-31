package Common;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Common {
    public static Predicate<String> fuzzyMatch(String needle) {
        Pattern pattern = Pattern.compile(
            Pattern.quote(needle)
                .replaceAll("\\s", "\\E.*\\Q"), 
            Pattern.CASE_INSENSITIVE
        );
        return haystack -> pattern.matcher(haystack).find();
    }

    private Common() {}
}
