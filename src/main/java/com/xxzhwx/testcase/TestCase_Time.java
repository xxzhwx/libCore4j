package com.xxzhwx.testcase;

import com.xxzhwx.core.utils.time.Time;

public class TestCase_Time implements TestCase {
    @Override
    public boolean run() {
        Time now = new Time();
        System.out.println(now);
        System.out.println(now.getTime());
        return true;
    }
}
