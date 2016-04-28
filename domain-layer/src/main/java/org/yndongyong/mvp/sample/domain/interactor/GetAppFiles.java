package org.yndongyong.mvp.sample.domain.interactor;

import org.yndongyong.mvp.sample.domain.executor.PostExecutionThread;
import org.yndongyong.mvp.sample.domain.executor.ThreadExecutor;
import org.yndongyong.mvp.sample.domain.repository.DataRepository;

import rx.Observable;

/**
 * Created by Dong on 2016/1/5.
 */
public class GetAppFiles extends UseCase {

    private DataRepository mDataRepository;

    private String uid;
    private String token;
    private String appid;

    public GetAppFiles(ThreadExecutor mThreadExecutor, PostExecutionThread mPostExecutionThread, DataRepository mDataRepository, String uid, String token, String appid) {
        super(mThreadExecutor, mPostExecutionThread);
        this.mDataRepository = mDataRepository;
        this.uid = uid;
        this.token = token;
        this.appid = appid;
    }
    @Override
    public Observable buildUseCaseObservable() {
        return mDataRepository.appFiles(uid, token, appid);
    }
}
