package org.yndongyong.mvp.sample.presenter;

import org.yndongyong.mvp.sample.Model.ModleDataMapper;
import org.yndongyong.mvp.sample.Model.ModulItemModle;
import org.yndongyong.mvp.sample.data.cache.DataLruCache;
import org.yndongyong.mvp.sample.data.cache.DataLruCacheImp;
import org.yndongyong.mvp.sample.data.executor.JobExecutor;
import org.yndongyong.mvp.sample.data.mapper.EntityDataMapper;
import org.yndongyong.mvp.sample.data.repository.CloudDataStore;
import org.yndongyong.mvp.sample.data.repository.DataRespositoryImp2;
import org.yndongyong.mvp.sample.data.retrofit.BankAppApi;
import org.yndongyong.mvp.sample.data.retrofit.RetrofitUtils;
import org.yndongyong.mvp.sample.domain.ModulItem;
import org.yndongyong.mvp.sample.domain.executor.PostExecutionThread;
import org.yndongyong.mvp.sample.domain.interactor.DefaultSubscriber;
import org.yndongyong.mvp.sample.domain.interactor.GetModulsForDataStore;
import org.yndongyong.mvp.sample.domain.interactor.UseCase;
import org.yndongyong.mvp.sample.view.MoudleView;

import java.util.List;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Dong on 2016/1/5.
 */
public class MoudleListPresenter implements Presenter {

    private MoudleView moudleView;
    private UseCase getModulUseCase;
    private ModleDataMapper mapper;


    DataLruCache cache;
    CloudDataStore dataStore;
    DataRespositoryImp2 respositoryImp2;

    public MoudleListPresenter() {
        this.mapper = ModleDataMapper.getInstance();
    }

    public void setView(MoudleView view) {
        this.moudleView = view;
//        这样可以很好的是实现绑定，从而不用再在Activity里绑定
        moudleView.setPresenter(this);
    }

    public void initialize() {
        loadUserList();
    }

    public void onItemClicked(ModulItemModle itemModle) {
        moudleView.viewItem(itemModle);
    }

    private void loadUserList() {
        this.moudleView.hideRetry();
        this.moudleView.showLoading();
        this.getUserList();
    }

    private void getUserList() {
        if (cache == null) {
            cache = new DataLruCacheImp(this.moudleView.getContext().getApplicationContext());
        }
        if (dataStore == null) {
            dataStore = new CloudDataStore(
                    RetrofitUtils.createApi(BankAppApi.class, "http://10.180.120.231"),
                    cache
            );
        }
        if (respositoryImp2 == null) {
            respositoryImp2 = new DataRespositoryImp2(EntityDataMapper
                    .getInstance(), dataStore
            );
        }

        if (getModulUseCase == null) {
            getModulUseCase = new GetModulsForDataStore(
                    JobExecutor.getInstance(),

                    new PostExecutionThread() {
                        @Override
                        public Scheduler getScheduler() {
                            return AndroidSchedulers.mainThread();
                        }
                    },
                    this.moudleView.getToken(),
                    this.moudleView.getUid(),
                    respositoryImp2

            );
        }
        getModulUseCase.execute(new ModuleListSubscriber());
    }

    private void showModulsInView(List<ModulItem> modulItems) {
        List<ModulItemModle> modles = mapper.transformModulItemEntity(modulItems);
        this.moudleView.renderMoudles(modles);
    }

    private void hideLoadingView() {
        this.moudleView.hideLoading();
    }

    private void showErrorMessage(Throwable e) {
        this.moudleView.showError(e.getMessage());
    }

    private void showRetryView() {
        this.moudleView.showRetry();
    }

    private final class ModuleListSubscriber extends DefaultSubscriber<List<ModulItem>> {
        @Override
        public void onCompleted() {
            MoudleListPresenter.this.hideLoadingView();
        }

        @Override
        public void onError(Throwable e) {
            //可以在这里拦截错误异常，进行处理
            MoudleListPresenter.this.hideLoadingView();
            MoudleListPresenter.this.showErrorMessage(e);
            MoudleListPresenter.this.showRetryView();
        }

        @Override
        public void onNext(List<ModulItem> modulItem) {
            MoudleListPresenter.this.showModulsInView(modulItem);
        }
    }

    @Override
    public void start() {
        
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        getModulUseCase.unsubscribe();
    }
}
