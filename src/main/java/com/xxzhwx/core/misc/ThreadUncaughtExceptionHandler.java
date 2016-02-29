package com.xxzhwx.core.misc;

import java.lang.Thread.UncaughtExceptionHandler;

public class ThreadUncaughtExceptionHandler implements UncaughtExceptionHandler {
    private static final UncaughtExceptionHandler I = new ThreadUncaughtExceptionHandler();

    private ThreadUncaughtExceptionHandler() {
    }

    public static UncaughtExceptionHandler getHandler() {
        return I;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        String msg = String.format(
                "Thread %s/%s encounter an uncaught exception: %s",
                String.valueOf(t.getId()), t.getName(), e.getMessage());
        System.err.println(msg);
    }
}
