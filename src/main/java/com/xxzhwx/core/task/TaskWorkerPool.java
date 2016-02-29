package com.xxzhwx.core.task;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TaskWorkerPool {
    private String name;
    private TaskWorker[] workers;
    private CountDownLatch doneSignal;

    public TaskWorkerPool(String name, int workerCount, int queueSize) {
        this.name = name;
        this.initWorkers(workerCount, queueSize);
    }

    private void initWorkers(int workerCount, int queueSize) {
        workers = new TaskWorker[workerCount];

        for (int i = 0; i < workerCount; ++i) {
            workers[i] = new TaskWorker(String.format("%s-%s", name, String.valueOf(i + 1)), queueSize);
            workers[i].start();
        }
    }

    public boolean acceptTask(int distributeKey, Task task) {
        TaskWorker worker = workers[distributeKey];
        return worker.acceptTask(task);
    }

    public String getName() {
        return name;
    }

    public int getWorkerCount() {
        return workers.length;
    }

    public int getTaskQueueSize() {
        int size = 0;
        for (TaskWorker w : workers)
            size += w.getTaskQueueSize();
        return size;
    }

    public void shutdown() {
        doneSignal = new CountDownLatch(workers.length);
        for (TaskWorker w : workers)
            w.shutdown(doneSignal);
    }

    public boolean awaitTermination(long timeout, TimeUnit unit) {
        try {
            return doneSignal.await(timeout, unit);
        } catch (InterruptedException e) {
            System.err.println("TaskWorkerPool " + getName() + " : " + e.getMessage());
        }

        return false;
    }

    public void shutdownNow() throws InterruptedException {
        for (TaskWorker w : workers)
            w.shutdownNow();

        for (TaskWorker w : workers)
            w.join();
    }
}
