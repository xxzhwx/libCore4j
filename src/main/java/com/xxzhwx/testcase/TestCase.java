package com.xxzhwx.testcase;

public interface TestCase {
    default String getName() {
        return this.getClass()
                .getSimpleName();
    }

    boolean run();
}
