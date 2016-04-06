package com.xxzhwx.core.utils;

import java.util.Calendar;
import java.util.Date;

public final class TimeUtils {
    public static long now() {
        return System.currentTimeMillis();
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
