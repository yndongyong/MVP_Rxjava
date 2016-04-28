package org.yndongyong.mvp.sample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.yndongyong.mvp.sample.Model.ModulItemModle;
import org.yndongyong.mvp.sample.data.cache.DataLruCache;
import org.yndongyong.mvp.sample.data.cache.DataLruCacheImp;
import org.yndongyong.mvp.sample.data.executor.JobExecutor;
import org.yndongyong.mvp.sample.data.mapper.EntityDataMapper;
import org.yndongyong.mvp.sample.data.repository.CloudDataStore;
import org.yndongyong.mvp.sample.data.repository.DataRespositoryImp2;
import org.yndongyong.mvp.sample.data.retrofit.BankAppApi;
import org.yndongyong.mvp.sample.data.retrofit.RetrofitUtils;
import org.yndongyong.mvp.sample.domain.executor.PostExecutionThread;
import org.yndongyong.mvp.sample.domain.interactor.GetModulsForDataStore;
import org.yndongyong.mvp.sample.presenter.MoudleListPresenter;
import org.yndongyong.mvp.sample.presenter.Presenter;
import org.yndongyong.mvp.sample.view.MoudleView;

import java.util.List;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity implements MoudleView {

    private MoudleListPresenter presenter;

    @ViewById
    RelativeLayout rl_progress;
    @ViewById
    RelativeLayout rl_retry;
    @ViewById
    Button bt_retry;
    @ViewById
    TextView tv_moduls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*DataStoreFactory factory = DataStoreFactory.getInstance(getApplicationContext());

        DataRespositoryImp dataRespositoryImp = new DataRespositoryImp(
                factory, EntityDataMapper.getInstance());
        
        GetModuls getModuls = new GetModuls(
                JobExecutor.getInstance(),
                new PostExecutionThread() {
                    @Override
                    public Scheduler getScheduler() {
                        return AndroidSchedulers.mainThread();
                    }
                },
                dataRespositoryImp,
                uid, token
        );*/


        presenter = new MoudleListPresenter();
        presenter.setView(this);
    }
    
    @Override
    public void setPresenter(Presenter _presenter) {
        presenter = (MoudleListPresenter) _presenter;
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.initialize();
    }

    @Click
    void bt_retry() {
        presenter.initialize();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public String getUid() {
        return "8a34003b4eb482c5014eb4a1e3b80012";
    }

    @Override
    public String getToken() {
        return "8a34003b4eb482c5014eb4a1e3b80012_1em0owqx54o1s8b9gdmygt2vz";
    }

    @Override
    public void renderMoudles(List<ModulItemModle> modles) {
        Log.d("TAG", "renderMoudles() " + modles);
        tv_moduls.setText(modles.toString());
    }

    @Override
    public void viewItem(ModulItemModle itemModle) {
        Log.d("TAG", "viewItem() " + itemModle);
    }


    

    @Override
    public void showLoading() {
        Log.d("TAG", "showLoading() ");
        rl_progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        Log.d("TAG", "hideLoading() ");
        rl_progress.setVisibility(View.GONE);
    }

    @Override
    public void showRetry() {
        Log.d("TAG", "showRetry() ");
        rl_retry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        Log.d("TAG", "hideRetry() ");
        rl_retry.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError(String message) {
        Log.d("TAG", message);
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
