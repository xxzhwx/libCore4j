package com.xxzhwx.core.task;

import com.xxzhwx.core.misc.ThreadUncaughtExceptionHandler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public class TaskWorker extends Thread {
    private volatile boolean running = true;
    private volatile boolean shutting = false;
    private BlockingQueue<Task> taskQueue;

    public TaskWorker(String name, int queueSize) {
        super(name);

        taskQueue = new ArrayBlockingQueue<>(queueSize);

        setUncaughtExceptionHandler(ThreadUncaughtExceptionHandler.getHandler());
        setDaemon(true);
    }

    @Override
    public void run() {
        while (running) {
            try {
                Task t = taskQueue.take();
                t.execute();
            } catch (InterruptedException e) {
                System.err.println("TaskWorker " + getName() + ": " + e.getMessage());
            }
        }
    }

    public boolean acceptTask(Task task) {
        if (shutting) {
            return false;
        }

        boolean ok = taskQueue.offer(task);
        if (!ok) {
            System.err.println("TaskWorker " + getName() + " queue is full!!!");
        }
        return ok;
    }

    public int getTaskQueueSize() {
        return taskQueue.size();
    }

    public void shutdown(CountDownLatch doneSignal) {
        shutting = true;

        if (!putShutdownTask(doneSignal)) {
            doShutdownTask(doneSignal);
        }
    }

    private boolean putShutdownTask(CountDownLatch doneSignal) {
        return taskQueue.offer(() -> TaskWorker.this.doShutdownTask(doneSignal));
    }

    private void doShutdownTask(final CountDownLatch doneSignal) {
        running = false;
        doneSignal.countDown();
    }

    public void shutdownNow() {
        shutting = true;
        running = false;
        interrupt();
    }
}
