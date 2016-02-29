package com.xxzhwx;

import com.xxzhwx.testcase.TestCase_ClassUtils;
import com.xxzhwx.testcase.TestCase_IoBuffer;
import com.xxzhwx.testcase.TestSuit;

public class App {
    public static void main(String[] args) {
        TestSuit suit = TestSuit.instance();
        suit.addTestCase(new TestCase_ClassUtils());
        suit.addTestCase(new TestCase_IoBuffer());
        suit.run();
    }
}
