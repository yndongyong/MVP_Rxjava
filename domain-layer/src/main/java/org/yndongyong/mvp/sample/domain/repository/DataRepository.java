package org.yndongyong.mvp.sample.domain.repository;

import org.yndongyong.mvp.sample.domain.AppFileItem;
import org.yndongyong.mvp.sample.domain.ModulItem;

import java.util.List;

import rx.Observable;

/**
 * Created by Dong on 2016/1/5.
 */
public interface DataRepository {

    Observable<List<ModulItem>> modules(String uid, String token);

    Observable<List<ModulItem>> apps(String uid, String token, String mid);

    Observable<List<AppFileItem>> appFiles(String uid, String token, String appid);
}
