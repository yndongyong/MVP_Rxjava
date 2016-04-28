package org.yndongyong.mvp.sample.domain.interactor;

import org.yndongyong.mvp.sample.domain.executor.PostExecutionThread;
import org.yndongyong.mvp.sample.domain.executor.ThreadExecutor;
import org.yndongyong.mvp.sample.domain.repository.DataRepository;

import rx.Observable;

/**
 * Created by Dong on 2016/1/5.
 */
public class GetModuls extends UseCase {
    
    private DataRepository mDataRepository;
    private String token;
    private String uid;

    public GetModuls(ThreadExecutor mThreadExecutor, 
                     PostExecutionThread mPostExecutionThread,
                     DataRepository dataRepository,String uid,String token) {
        super(mThreadExecutor, mPostExecutionThread);
        this.mDataRepository = dataRepository;
        this.uid= uid;
        this.token = token;
    }

    @Override
    public Observable buildUseCaseObservable() {
        return mDataRepository.modules(uid,token);
    }
}
