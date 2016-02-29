package com.xxzhwx.core.task;

public interface Task {
    default String getName() {
        return this.getClass()
                .getSimpleName();
    }

    void execute();
}
