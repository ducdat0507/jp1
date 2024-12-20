package com.dsa.sort.forms;

public class FormUtils {

    public static String formatDuration(long nanos) {
        if (nanos < 1e3) return String.format("%d", nanos) + " nanoseconds";
        if (nanos < 1e6) return String.format("%.3f", nanos / 1e3f) + " microseconds";
        if (nanos < 1e9) return String.format("%.3f", nanos / 1e6f) + " milliseconds";
        if (nanos < 1e9 * 60) return String.format("%.3f", nanos / 1e9f) + " seconds";
        return String.format("%.3f", nanos / 1e9f / 60) + " minutes";
    }

}
