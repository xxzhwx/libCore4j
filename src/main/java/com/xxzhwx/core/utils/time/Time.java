package com.xxzhwx.core.utils.time;

import java.util.Calendar;

public class Time {
    private int year;
    private int month;
    private int dayOfMonth;
    private int hour;
    private int minute;
    private int second;
    private int milliSecond;
    private long time;

    public Time() {
        initialize(Calendar.getInstance());
    }

    public Time(long time) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        initialize(c);
    }

    private void initialize(Calendar c) {
        this.year = c.get(Calendar.YEAR);
        this.month = c.get(Calendar.MONTH) + 1;
        this.dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        this.hour = c.get(Calendar.HOUR_OF_DAY);
        this.minute = c.get(Calendar.MINUTE);
        this.second = c.get(Calendar.SECOND);
        this.milliSecond = c.get(Calendar.MILLISECOND);
        this.time = c.getTimeInMillis();
    }

    /** 获得年份 */
    public int getYear() {
        return year;
    }

    /** 获得月份 [1,12] */
    public int getMonth() {
        return month;
    }

    /** 获得日期 [1,31] */
    public int getDayOfMonth() {
        return dayOfMonth;
    }

    /** 获得小时 [0,23] */
    public int getHour() {
        return hour;
    }

    /** 获得分钟 [0,59] */
    public int getMinute() {
        return minute;
    }

    /** 获得秒数 [0,59] */
    public int getSecond() {
        return second;
    }

    /** 获得毫秒数 [0,999] */
    public int getMilliSecond() {
        return milliSecond;
    }

    public long getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "" + year + "-" + month + "-" + dayOfMonth + " "
                + hour + ":" + minute + ":" + second + ":" + milliSecond;
    }
}
