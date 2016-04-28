package org.yndongyong.mvp.sample.data.cache;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import org.yndongyong.mvp.sample.data.AppFileItemEntity;
import org.yndongyong.mvp.sample.data.AppFileEntity;
import org.yndongyong.mvp.sample.data.ModulItemEntity;
import org.yndongyong.mvp.sample.data.ModuleEntity;
import org.yndongyong.mvp.sample.data.SerializableEntity;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Dong on 2016/1/5.
 */
public class DataLruCacheImp implements DataLruCache {
    
    //最好传 applicationcontext
    private Context mContext;
    
    public DataLruCacheImp(Context appContext) {
       this.mContext = appContext;
    }

    @Override
    public boolean isCached(String key) {
        return  ACache.get(mContext).getAsObject(key) != null;
    }
    
    @Override
    public void put(String key, SerializableEntity entity,int saveTime) {
        ACache.get(mContext).put(key,entity,saveTime);
       /* try {
            File cacheDir = getDiskCacheDir(mContext, "cache");
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            DiskLruCache.Editor editor = DiskLruCache.open(cacheDir,
                    getAppVersion(mContext), 1, 10 * 1024 * 1024).edit(hashKeyForDisk(key));
            OutputStream outputStream = editor.newOutputStream(0);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

   /* public void putAppInfoEntity(String key, AppFileEntity entity, int saveTime) {
        ACache.get(mContext).put(key,entity,saveTime);
    }*/

    @Override
    public Observable<List<ModulItemEntity>> getModuleList() {
        Log.d("TAG", "getModuleList from cache");
            return Observable.create(new Observable.OnSubscribe<List<ModulItemEntity>>() {
                @Override
                public void call(Subscriber<? super List<ModulItemEntity>> subscriber) {
                    ModuleEntity asObject = (ModuleEntity)ACache.get(mContext).getAsObject
                            (ModulItemEntity.KEY);
                    if (asObject != null) {
                        subscriber.onNext(asObject.getData());
                        subscriber.onCompleted();
                    } else {
                        subscriber.onCompleted();
                    }
                }
            });
    }

    @Override
    public Observable<List<ModulItemEntity>> getApplicationList() {
        return getModuleList();
    }

    @Override
    public Observable<List<AppFileItemEntity>> getAppFileList() {
            return Observable.create(new Observable.OnSubscribe<List<AppFileItemEntity>>() {
                @Override
                public void call(Subscriber<? super List<AppFileItemEntity>> subscriber) {
                    AppFileEntity asObject = (AppFileEntity)ACache.get(mContext).getAsObject
                            (AppFileItemEntity.KEY);
                    if (asObject != null) {
                        subscriber.onNext(asObject.getData());
                        subscriber.onCompleted();
                    } else {
                        subscriber.onCompleted();
                    }
                }
            });
    }

    @Override
    public void remove(String key) {
        ACache.get(mContext).remove(key);
    }

    @Override
    public void evictAll() {
        ACache.get(mContext).clear();
    }
    
    public File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }
    public int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }
    public String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
