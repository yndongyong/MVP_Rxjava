package org.yndongyong.mvp.sample.data.repository;

import org.yndongyong.mvp.sample.data.ModulItemEntity;
import org.yndongyong.mvp.sample.data.mapper.EntityDataMapper;
import org.yndongyong.mvp.sample.domain.AppFileItem;
import org.yndongyong.mvp.sample.domain.ModulItem;
import org.yndongyong.mvp.sample.domain.repository.DataRepository;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Dong on 2016/1/12.
 */
public class DataRespositoryImp2 implements DataRepository {
    private EntityDataMapper mapper;
    private CloudDataStore mCloudDataStore;

    public DataRespositoryImp2(EntityDataMapper mapper, CloudDataStore mCloudDataStore) {
        this.mapper = mapper;
        this.mCloudDataStore = mCloudDataStore;
    }

    @Override
    public Observable<List<ModulItem>> modules(String uid, String token) {
        return mCloudDataStore.getMoudleList(uid,token).map(new Func1<List<ModulItemEntity>, List<ModulItem>>() {
            @Override
            public List<ModulItem> call(List<ModulItemEntity> modulItemEntities) {
                return mapper.transformModulItemEntity(modulItemEntities);
            }
        });
    }

    @Override
    public Observable<List<ModulItem>> apps(String uid, String token, String mid) {
        return null;
    }

    @Override
    public Observable<List<AppFileItem>> appFiles(String uid, String token, String appid) {
        return null;
    }
}
