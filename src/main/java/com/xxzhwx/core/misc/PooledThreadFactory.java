package com.xxzhwx.core.misc;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class PooledThreadFactory implements ThreadFactory {
    private final ThreadGroup group;
    private final String name;
    private final boolean daemon;
    private final AtomicInteger threadNum = new AtomicInteger(0);

    public PooledThreadFactory(String name, boolean daemon) {
        SecurityManager secMgr = System.getSecurityManager();
        this.group = (secMgr != null) ?
                secMgr.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
        this.name = name;
        this.daemon = daemon;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r, name + "-" + threadNum.getAndIncrement(), 0);
        t.setDaemon(daemon);

        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }

        return t;
    }
}
