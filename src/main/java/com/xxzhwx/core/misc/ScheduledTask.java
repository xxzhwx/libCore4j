package com.xxzhwx.core.misc;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

public class ScheduledTask<V> implements Runnable, Callable<V> {
    public ScheduledTask(String name, Callable<V> task) {
        this.name = name;
        this.task = task;
    }

    public ScheduledTask(String name, Runnable task) {
        this.name = name;
        this.task = Executors.callable(task);
    }

    @SuppressWarnings("unchecked")
    public V call() throws Exception {
        return (V) execute();
    }

    public void run() {
        try {
            execute();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    private Object execute() throws Exception {
        return task.call();
    }

    private String name;
    private Callable<?> task;
}
