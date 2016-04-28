package org.yndongyong.mvp.sample.view;


import org.yndongyong.mvp.sample.Model.ModulItemModle;

import java.util.List;

/**
 * Created by Dong on 2016/1/5.
 */
public interface MoudleView extends LoadDataView {

    //需要什么样的数据
    String getUid();
    String getToken();
    
    /**
     * 显示集合
     * @param modles
     */
    void renderMoudles(List<ModulItemModle> modles);

    /**
     * 单击某个一个如何显示
     * @param itemModle
     */
    void viewItem(ModulItemModle itemModle);
}
