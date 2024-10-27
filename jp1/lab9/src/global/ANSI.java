package global;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ANSI {
    public static final int CLEAR = 0;

    public static final int BOLD = 1;
    public static final int ITALIC = 3;
    public static final int UNDERLINE = 4;
    public static final int BLINK = 5;

    public static final int FG_BLACK = 30;
    public static final int FG_RED = 31;
    public static final int FG_GREEN = 32;
    public static final int FG_YELLOW = 33;
    public static final int FG_BLUE = 34;
    public static final int FG_MAGENTA = 35;
    public static final int FG_CYAN = 36;
    public static final int FG_LIGHT_GRAY = 37;
    public static final int FG_DARK_GRAY = 90;
    public static final int FG_LIGHT_RED = 91;
    public static final int FG_LIGHT_GREEN = 92;
    public static final int FG_LIGHT_YELLOW = 93;
    public static final int FG_LIGHT_BLUE = 94;
    public static final int FG_LIGHT_MAGENTA = 95;
    public static final int FG_LIGHT_CYAN = 96;
    public static final int FG_WHITE = 97;

    public static final int BG_BLACK = 40;
    public static final int BG_RED = 41;
    public static final int BG_GREEN = 42;
    public static final int BG_YELLOW = 43;
    public static final int BG_BLUE = 44;
    public static final int BG_MAGENTA = 45;
    public static final int BG_CYAN = 46;
    public static final int BG_LIGHT_GRAY = 47;
    public static final int BG_DARK_GRAY = 100;
    public static final int BG_LIGHT_RED = 101;
    public static final int BG_LIGHT_GREEN = 102;
    public static final int BG_LIGHT_YELLOW = 103;
    public static final int BG_LIGHT_BLUE = 104;
    public static final int BG_LIGHT_MAGENTA = 105;
    public static final int BG_LIGHT_CYAN = 106;
    public static final int BG_WHITE = 107;

    private ANSI () { /*  */ }

    public static String format(Integer ...codes) {
        String codesStr = Stream.of(codes).map(t -> t.toString()).collect(Collectors.joining(";"));
        return "\033[" + codesStr + "m";
    }
}
