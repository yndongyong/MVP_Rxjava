package org.yndongyong.mvp.sample.data.cache;

import org.yndongyong.mvp.sample.data.AppFileItemEntity;
import org.yndongyong.mvp.sample.data.AppFileEntity;
import org.yndongyong.mvp.sample.data.ModulItemEntity;
import org.yndongyong.mvp.sample.data.ModuleEntity;
import org.yndongyong.mvp.sample.data.SerializableEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by Dong on 2016/1/5.
 */
public interface DataLruCache {

    boolean isCached(String key);
    /**
     * 
     * @param key
     * @param entity
     * @param saveTime 缓存时间秒
      */
    void put(String key , SerializableEntity entity , int saveTime);
    
    //模块 应用,因为内容一样
//    Observable<List<ModulItemEntity>> getModuleList(String key);
    //不需要key 了，直接通过Entity的KEY
    Observable<List<ModulItemEntity>> getModuleList();
    
    //
    Observable<List<ModulItemEntity>> getApplicationList();
    
    //文件
    Observable<List<AppFileItemEntity>> getAppFileList();

    void remove(String key);
    
    /**
     * Evict all elements of the cache.
     */
    void evictAll();

    
}
