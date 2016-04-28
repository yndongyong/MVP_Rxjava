package org.yndongyong.mvp.sample.presenter;

/**
 * 从事件的角度去分析，
 * Created by Dong on 2016/1/5.
 */
public interface Presenter {

    //业务逻辑的初始化地方
    void start();
    
    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onResume() method.
     */
    void resume();
   
    void pause();

    void destroy();
}
