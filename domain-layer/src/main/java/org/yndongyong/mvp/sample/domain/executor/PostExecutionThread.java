package org.yndongyong.mvp.sample.domain.executor;

import rx.Scheduler;

/**
 * Created by Dong on 2016/1/5.
 */
public interface PostExecutionThread {
    Scheduler getScheduler();
}
