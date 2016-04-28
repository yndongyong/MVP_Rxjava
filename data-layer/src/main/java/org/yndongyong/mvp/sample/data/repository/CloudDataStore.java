package org.yndongyong.mvp.sample.data.repository;

import android.util.Log;

import org.yndongyong.mvp.sample.data.AppFileItemEntity;
import org.yndongyong.mvp.sample.data.AppFileEntity;
import org.yndongyong.mvp.sample.data.ModulItemEntity;
import org.yndongyong.mvp.sample.data.ModuleEntity;
import org.yndongyong.mvp.sample.data.cache.DataLruCache;
import org.yndongyong.mvp.sample.data.retrofit.BankAppApi;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Dong on 2016/1/5.
 */
public class CloudDataStore implements DataStore {


    private BankAppApi mBankAppApi;
    private DataLruCache mDataLruCache;

    public CloudDataStore(BankAppApi bankAppApi, DataLruCache dataLruCache) {
        this.mBankAppApi = bankAppApi;
        mDataLruCache = dataLruCache;
    }

    private final Action1<ModuleEntity> saveModuleEntityToCache = new Action1<ModuleEntity>() {

        @Override
        public void call(ModuleEntity moduleEntity) {
            if (moduleEntity != null) {
                Log.d("TAG", "saveModuleEntityToCache()");
                //可以将key抽取为常量
                mDataLruCache.put(ModuleEntity.KEY, moduleEntity, ModuleEntity.SECOND);
            }
        }
    };

    private final Action1<ModuleEntity> saveApplicationToCache = new Action1<ModuleEntity>() {
        @Override
        public void call(ModuleEntity moduleEntity) {
            if (moduleEntity != null) {
                mDataLruCache.put(ModuleEntity.KEY, moduleEntity, 1 * 60);
            }
        }
    };
    private final Action1<AppFileEntity> saveAppFileInfoEntityToCache = new Action1<AppFileEntity>() {
        @Override
        public void call(AppFileEntity entity) {
            if (entity != null) {
                mDataLruCache.put(AppFileEntity.KEY, entity, 1 * 60);
            }
        }
    };

    @Override
    public Observable<List<ModulItemEntity>> getMoudleList(String uid, String token) {
        //TODO 先取缓存，然后再网络
        Observable<List<ModulItemEntity>> cache = mDataLruCache.getModuleList();

        Observable<List<ModulItemEntity>> network = mBankAppApi.getModules(uid, token)
                .doOnNext(saveModuleEntityToCache) //添加到缓存中
                .map(new Func1<ModuleEntity, List<ModulItemEntity>>() {
                    @Override
                    public List<ModulItemEntity> call(ModuleEntity moudleEntity) {
                        return moudleEntity.getData();
                    }
                });
        return Observable.concat(cache, network).first();
    }

    @Override
    public Observable<List<ModulItemEntity>> getApplicatoinList(String uid, String token, String mid) {
        return mBankAppApi.getApplications(uid, token, mid)
                .doOnNext(saveApplicationToCache)
                .map(new Func1<ModuleEntity, List<ModulItemEntity>>() {
                    @Override
                    public List<ModulItemEntity> call(ModuleEntity moudleEntity) {
                        return moudleEntity.getData();
                    }
                });
    }

    @Override
    public Observable<List<AppFileItemEntity>> getAppFileList(String uid, String token, String appid) {
        return mBankAppApi.getFiles(uid, token, appid)
                .doOnNext(saveAppFileInfoEntityToCache)
                .map(new Func1<AppFileEntity, List<AppFileItemEntity>>() {
                    @Override
                    public List<AppFileItemEntity> call(AppFileEntity appFileInfoEntity) {
                        return appFileInfoEntity.getData();
                    }
                });
    }
}
