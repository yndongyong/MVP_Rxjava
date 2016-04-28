package org.yndongyong.mvp.sample.data.repository;

import org.yndongyong.mvp.sample.data.AppFileItemEntity;
import org.yndongyong.mvp.sample.data.ModulItemEntity;
import org.yndongyong.mvp.sample.data.mapper.EntityDataMapper;
import org.yndongyong.mvp.sample.domain.AppFileItem;
import org.yndongyong.mvp.sample.domain.ModulItem;
import org.yndongyong.mvp.sample.domain.repository.DataRepository;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Dong on 2016/1/5.
 */
public class DataRespositoryImp implements DataRepository {
    
    private DataStoreFactory mDataStoreFactory;
    
    private EntityDataMapper mapper;

    public DataRespositoryImp(DataStoreFactory mDataStoreFactory, EntityDataMapper mapper) {
        this.mDataStoreFactory = mDataStoreFactory;
        this.mapper = mapper;
    }

    @Override
    public Observable<List<ModulItem>> modules(String uid, String token) {
        DataStore dataStore = mDataStoreFactory.create(uid);
        return dataStore.getMoudleList(uid,token)
                .map(new Func1<List<ModulItemEntity>, List<ModulItem>>() {
            @Override
            public List<ModulItem> call(List<ModulItemEntity> modulItemEntities) {
                return mapper.transformModulItemEntity(modulItemEntities);
            }
        });
    }

    @Override
    public Observable<List<ModulItem>> apps(String uid, String token, String mid) {
        DataStore dataStore = mDataStoreFactory.create(uid);
        return dataStore.getApplicatoinList(uid,token,mid)
                .map(new Func1<List<ModulItemEntity>, List<ModulItem>>() {
                    @Override
                    public List<ModulItem> call(List<ModulItemEntity> modulItemEntities) {
                        return mapper.transformModulItemEntity(modulItemEntities);
                    }
                });
    }

    @Override
    public Observable<List<AppFileItem>> appFiles(String uid, String token, String appid) {
        DataStore dataStore = mDataStoreFactory.create(uid);
        return dataStore.getAppFileList(uid, token, appid)
                .map(new Func1<List<AppFileItemEntity>, List<AppFileItem>>() {
                    @Override
                    public List<AppFileItem> call(List<AppFileItemEntity> appFileItemEntities) {
                        return mapper.transformAppFileItemEntity(appFileItemEntities);
                    }
                });
    }
}
