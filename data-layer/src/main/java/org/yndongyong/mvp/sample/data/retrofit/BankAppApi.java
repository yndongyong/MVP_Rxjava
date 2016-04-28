package org.yndongyong.mvp.sample.data.retrofit;

import org.yndongyong.mvp.sample.data.AppFileEntity;
import org.yndongyong.mvp.sample.data.ModuleEntity;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Dong on 2016/1/5.
 */
public interface BankAppApi {
    
    //获取模块,baseUrl一定要以/结尾
    @GET("bankappmobile/phone/client/getModules")
    Observable<ModuleEntity> getModules(@Query("uid") String uid, @Query("token") String token);
    
    //获取模块下的应用
    @GET("bankappmobile/phone/client/getApplications")
    Observable<ModuleEntity> getApplications(@Query("uid") String uid, @Query("token") String token ,
                                             @Query("mid") String mid);
    
    //获取应用下的文件
    @GET("bankappmobile/phone/client/getFiles")
    Observable<AppFileEntity> getFiles(@Query("uid") String uid, @Query("token") String token ,
                                       @Query("appid") String appid);
    //使用方法
    // RetrofitUtils.createApi(MainActivity.this, GitHubApi.class,baseUrl);
    
//    Observable<AppFileItemEntity> getFiles(@Body ModulItemEntity uid);
}
