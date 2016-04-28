package org.yndongyong.mvp.sample.data;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.google.gson.Gson;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
    
    public void testGson(){
        ModuleEntity me = new ModuleEntity();
        me.setCode(-1);
        me.setMsg("suc");
        me.setData(null);

        Gson gson = new Gson();
        String json = gson.toJson(me);
        System.out.println(json);

    }
    public void testSaveModleEntity() {
        
    }
}