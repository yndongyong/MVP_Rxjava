package org.yndongyong.mvp.sample.domain.interactor;

import rx.Subscriber;

/**
 * Created by Dong on 2016/1/5.
 */
public class DefaultSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {
        
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {

    }
}
