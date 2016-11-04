package com.douncoding.schoollock.domain;

import rx.Scheduler;

public interface PostExecutionThread {
    Scheduler getScheduler();
}
