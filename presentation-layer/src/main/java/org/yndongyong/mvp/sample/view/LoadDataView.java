package org.yndongyong.mvp.sample.view;

import android.content.Context;

import org.yndongyong.mvp.sample.presenter.Presenter;

/**
 * 从view的角度分析,需要哪些数据，方法名以show ,hide开头，除setPresenter，getContext()连个方法之之外
 * Created by Dong on 2016/1/5.
 */
public interface LoadDataView {

    void setPresenter(Presenter presenter);
    /**
     * Show a view with a progress bar indicating a loading process.
     */
    void showLoading();

    /**
     * Hide a loading view.
     */
    void hideLoading();

    /**
     * Show a retry view in case of an error when retrieving data.
     */
    void showRetry();

    /**
     * Hide a retry view shown if there was an error when retrieving data.
     */
    void hideRetry();

    /**
     * Show an error message
     *
     * @param message A string representing an error.
     */
    void showError(String message);

    /**
     * Get a {@link android.content.Context}.
     */
    Context getContext();
}
