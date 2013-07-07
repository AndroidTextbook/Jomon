
package com.android_textbook.learnjunit.util;

public class StringUtil {
    public static boolean isEmpty(CharSequence arg) {
        return arg == null || arg.length() == 0;
    }

    public static int parseInt(String arg, int defaultValue) {
        if (arg == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
