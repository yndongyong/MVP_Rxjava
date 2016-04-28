package org.yndongyong.mvp.sample.domain.interactor;

import org.yndongyong.mvp.sample.domain.executor.PostExecutionThread;
import org.yndongyong.mvp.sample.domain.executor.ThreadExecutor;
import org.yndongyong.mvp.sample.domain.repository.DataRepository;

import rx.Observable;

/**
 * Created by Dong on 2016/1/5.
 */
public class GetApps extends UseCase {
    
    private String uid;
    private String token;
    private String mid;
    
    private DataRepository mDataRepository;

    public GetApps(ThreadExecutor mThreadExecutor, PostExecutionThread mPostExecutionThread, String uid, String token, String mid, DataRepository mDataRepository) {
        super(mThreadExecutor, mPostExecutionThread);
        this.uid = uid;
        this.token = token;
        this.mid = mid;
        this.mDataRepository = mDataRepository;
    }

    @Override
    public Observable buildUseCaseObservable() {
        return mDataRepository.apps(uid,token,mid);
    }
}
