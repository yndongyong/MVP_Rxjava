package org.yndongyong.mvp.sample.domain.interactor;

import org.yndongyong.mvp.sample.domain.executor.PostExecutionThread;
import org.yndongyong.mvp.sample.domain.executor.ThreadExecutor;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by Dong on 2016/1/5.
 */
public abstract class UseCase {
    
    private ThreadExecutor mThreadExecutor;
    private PostExecutionThread mPostExecutionThread;
    
    private Subscription subscription = Subscriptions.empty();

    public UseCase(ThreadExecutor mThreadExecutor, PostExecutionThread mPostExecutionThread) {
        this.mThreadExecutor = mThreadExecutor;
        this.mPostExecutionThread = mPostExecutionThread;
    }

    public abstract Observable buildUseCaseObservable();

    public void execute(Subscriber usecaseSubscriber) {
        subscription = buildUseCaseObservable()
                .subscribeOn(Schedulers.from(mThreadExecutor))
                .observeOn(mPostExecutionThread.getScheduler())
                .subscribe(usecaseSubscriber);
    }
    
    public void unsubscribe(){
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }
    
}
