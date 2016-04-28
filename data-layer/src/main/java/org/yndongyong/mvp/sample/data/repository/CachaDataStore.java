package org.yndongyong.mvp.sample.data.repository;

import org.yndongyong.mvp.sample.data.AppFileItemEntity;
import org.yndongyong.mvp.sample.data.ModulItemEntity;
import org.yndongyong.mvp.sample.data.cache.DataLruCache;

import java.util.List;
import rx.Observable;
/**
 * Created by Dong on 2016/1/5.
 */
public class CachaDataStore implements DataStore {
    
    private DataLruCache mDataLruCache;

    public CachaDataStore(DataLruCache mDataLruCache) {
        this.mDataLruCache = mDataLruCache;
    }

    @Override
    public Observable<List<ModulItemEntity>> getMoudleList(String uid, String token) {
        return mDataLruCache.getModuleList();
    }
    
    @Override
    public Observable<List<ModulItemEntity>> getApplicatoinList(String uid, String token, String mid) {
        return mDataLruCache.getApplicationList();
    }

    @Override
    public Observable<List<AppFileItemEntity>> getAppFileList(String uid, String token, String appid) {
        return mDataLruCache.getAppFileList();
    }
}
