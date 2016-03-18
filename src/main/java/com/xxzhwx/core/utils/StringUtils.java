package com.xxzhwx.core.utils;

public final class StringUtils {
    public static final String EMPTY_STRING = "";

    public static String join(int[] values, char separator) {
        if (values == null) {
            return null;
        }

        StringBuilder buf = new StringBuilder(32);
        for (int i = 0, n = values.length; i < n; ++i) {
            if (i > 0) {
                buf.append(separator);
            }
            buf.append(values[i]);
        }

        return buf.toString();
    }
}
