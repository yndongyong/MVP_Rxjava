package org.yndongyong.mvp.sample.data.repository;

import android.content.Context;

import org.yndongyong.mvp.sample.data.cache.DataLruCache;
import org.yndongyong.mvp.sample.data.cache.DataLruCacheImp;
import org.yndongyong.mvp.sample.data.retrofit.BankAppApi;
import org.yndongyong.mvp.sample.data.retrofit.RetrofitUtils;

/**
 * Created by Dong on 2016/1/5.
 */
public class DataStoreFactory {

    public static final String paasUrl = "http://10.180.120.231";
    
    private static DataStoreFactory ourInstance ;

    private static DataLruCache mDataLruCache;

    private static Context mContext;
    
    public static DataStoreFactory getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new DataStoreFactory(context);
        }
        return ourInstance;
    }

    private DataStoreFactory(Context _context) {
        mContext = _context;
        mDataLruCache = new DataLruCacheImp(_context);
    }
    public DataStore create(String  key) {
        DataStore ds;
        if (mDataLruCache.isCached(key)) {
            ds = new CachaDataStore(mDataLruCache);
        }else{
            ds = createCouldDataStore();
        }
        return ds;
    }

    public DataStore createCouldDataStore() {
        DataStore ds = new CloudDataStore(RetrofitUtils.createApi(BankAppApi.class, paasUrl),mDataLruCache);
        return ds;
    }
}
