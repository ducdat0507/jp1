package Global;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Global {
    public static boolean ignoreCase(String preg, String str){
        String regex = Pattern.quote(preg).replaceAll("\\s+", "\\\\E.*\\\\Q");
        Pattern p = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        return m.find();
    }
}
