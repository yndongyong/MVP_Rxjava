package org.yndongyong.mvp.sample.domain.interactor;

import org.yndongyong.mvp.sample.domain.executor.PostExecutionThread;
import org.yndongyong.mvp.sample.domain.executor.ThreadExecutor;
import org.yndongyong.mvp.sample.domain.repository.DataRepository;

import rx.Observable;

/**
 * Created by Dong on 2016/1/12.
 */
public class GetModulsForDataStore extends UseCase {
    
    private String token;
    private String uid;
    private DataRepository mDataRepository;

    public GetModulsForDataStore(ThreadExecutor mThreadExecutor, PostExecutionThread mPostExecutionThread, String token, String uid, DataRepository mDataRepository) {
            super(mThreadExecutor, mPostExecutionThread);
        this.token = token;
        this.uid = uid;
        this.mDataRepository = mDataRepository;
    }

    @Override
    public Observable buildUseCaseObservable() {
        return mDataRepository.modules(uid,token);
    }
}
