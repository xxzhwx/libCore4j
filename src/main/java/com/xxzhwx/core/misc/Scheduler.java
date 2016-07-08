package com.xxzhwx.core.misc;

import java.util.concurrent.*;

public class Scheduler {
    public Scheduler(int executorCount, ThreadFactory threadFactory) {
        executors = new ScheduledExecutorService[executorCount];
        for (int i = 0; i < executorCount; i++) {
            executors[i] = Executors.newSingleThreadScheduledExecutor(threadFactory);
        }
    }

    public void execute(int index, String taskName, Runnable command) {
        this.getExecutor(index).execute(new ScheduledTask(taskName, command));
    }

    public ScheduledFuture<?> schedule(int index, String taskName, Runnable command, long delay, TimeUnit unit) {
        return this.getExecutor(index).schedule((Runnable) new ScheduledTask<Void>(taskName, command), delay, unit);
    }

    public <V> ScheduledFuture<V> schedule(int index, String taskName, Callable<V> callable, long delay, TimeUnit unit) {
        return this.getExecutor(index).schedule((Callable<V>) new ScheduledTask<V>(taskName, callable), delay, unit);
    }

    public ScheduledFuture<?> scheduleAtFixedRate(int index, String taskName, Runnable command, long initialDelay, long period, TimeUnit unit) {
        return this.getExecutor(index).scheduleAtFixedRate(new ScheduledTask<Void>(taskName, command), initialDelay, period, unit);
    }

    public ScheduledFuture<?> scheduleWithFixedDelay(int index, String taskName, Runnable command, long initialDelay, long delay, TimeUnit unit) {
        return this.getExecutor(index).scheduleWithFixedDelay(new ScheduledTask<Void>(taskName, command), initialDelay, delay, unit);
    }

    public void shutdownNow() {
        for (ScheduledExecutorService executor : executors) {
            executor.shutdownNow();
        }
    }

    private ScheduledExecutorService getExecutor(int index) {
        return executors[Math.abs(index % executors.length)];
    }

    private ScheduledExecutorService[] executors;
}
