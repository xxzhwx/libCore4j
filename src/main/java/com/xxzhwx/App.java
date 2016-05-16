package com.xxzhwx;

import com.xxzhwx.testcase.*;

public class App {
    public static void main(String[] args) {
        TestSuit suit = TestSuit.instance();
        suit.addTestCase(new TestCase_ClassUtils());
        suit.addTestCase(new TestCase_CryptUtils());
        suit.addTestCase(new TestCase_IoBuffer());
        suit.addTestCase(new TestCase_TimeUtils());
        suit.addTestCase(new TestCase_ZipUtils());
        suit.run();
    }
}
