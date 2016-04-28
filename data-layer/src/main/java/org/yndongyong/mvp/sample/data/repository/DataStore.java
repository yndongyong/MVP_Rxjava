package org.yndongyong.mvp.sample.data.repository;

import org.yndongyong.mvp.sample.data.AppFileItemEntity;
import org.yndongyong.mvp.sample.data.ModulItemEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by Dong on 2016/1/5.
 */
public interface DataStore {

    Observable<List<ModulItemEntity>> getMoudleList(String uid, String token);

    Observable<List<ModulItemEntity>> getApplicatoinList(String uid, String token, String mid);

    Observable<List<AppFileItemEntity>> getAppFileList(String uid, String token, String appid);

}
