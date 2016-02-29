package com.xxzhwx.testcase;

import java.util.ArrayList;
import java.util.List;

public class TestSuit {
    private static final TestSuit I = new TestSuit();
    private List<TestCase> testcases;

    private TestSuit() {
        testcases = new ArrayList<>();
    }

    public static TestSuit instance() {
        return I;
    }

    public void addTestCase(TestCase cs) {
        testcases.add(cs);
    }

    public void run() {
        boolean allOk = true;

        for (TestCase cs : testcases) {
            if (!cs.run()) {
                allOk = false;
                System.out.println("TestCase[" + cs.getName() + "] failed!!!");
                break;
            }
        }

        if (allOk)
            System.out.println("Everything is okay...");
    }
}
