package com.xxzhwx.testcase;

import com.xxzhwx.core.utils.TimeUtils;

import java.util.Date;

public class TestCase_TimeUtils implements TestCase {
    @Override
    public boolean run() {
        // 5月5号 和 5月6号
        Date d1 = new Date(2016, 4, 5, 23, 59, 59);
        Date d2 = new Date(2016, 4, 6, 0, 0, 0);
        if (TimeUtils.diffDays(d1, d2) != 1) {
            return false;
        }

        // 4月30号 和 5月1号
        d1 = new Date(2016, 3, 30);
        d2 = new Date(2016, 4, 1);
        if (TimeUtils.diffDays(d1, d2) != 1) {
            return false;
        }

        return true;
    }
}
