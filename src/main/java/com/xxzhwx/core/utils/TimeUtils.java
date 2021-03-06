package com.xxzhwx.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class TimeUtils {
    private static final SimpleDateFormat dtFmt;
    static {
        dtFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public static long now() {
        return System.currentTimeMillis();
    }

    /** 格式化为标准格式 */
    public static String format(Date date) {
        return dtFmt.format(date);
    }

    /** 由标准格式解析成日期，格式错误返回 null */
    public static Date parse(String source) {
        try {
            return dtFmt.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int diffDays(Date date1, Date date2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        c1.set(Calendar.MILLISECOND, 0);
        long t1 = c1.getTimeInMillis();

        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2);
        c2.set(Calendar.HOUR_OF_DAY, 0);
        c2.set(Calendar.MINUTE, 0);
        c2.set(Calendar.SECOND, 0);
        c2.set(Calendar.MILLISECOND, 0);
        long t2 = c2.getTimeInMillis();

        return (int) (Math.abs(t1 - t2) / (3600 * 24 * 1000));
    }
}
