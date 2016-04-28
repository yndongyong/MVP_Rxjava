package org.yndongyong.mvp.sample.data;

import java.util.List;

/**
 * Created by Dong on 2016/1/5.
 */
public class ModuleEntity extends SerializableEntity {
    public static final transient String KEY = SerializableEntity.class.getSimpleName();//缓存中的key
    public static final transient int SECOND = 1 * 60; //缓存中时间,秒

   /**
     * succeed : true
     * code : -1
     * msg : 模块查询成功
     * data : [{"id":null,"code":"module_gwgl","name":"公文管理","avatar":"http://10.180.120.231/static/icon/gwgl.png","todos":"52","sort":1,"openType":"0","openUrl":null},{"id":null,"code":"module_swgl","name":"事务管理","avatar":"http://10.180.120.231/static/icon/swgl.png","todos":"35","sort":2,"openType":"0","openUrl":null},{"id":null,"code":"module_hygl","name":"会议管理","avatar":"http://10.180.120.231/static/icon/hygl.png","todos":"0","sort":3,"openType":"0","openUrl":null},{"id":null,"code":"module_ggxxfb","name":"公共信息发布","avatar":"http://10.180.120.231/static/icon/ggxxfb.png","todos":"8","sort":4,"openType":"0","openUrl":null},{"id":null,"code":"module_zszx","name":"知识中心","avatar":"http://10.180.120.231/static/icon/zszx.png","todos":"0","sort":5,"openType":"1","openUrl":"http://10.180.120.231/banknotice/phone/knowledge/index?token=8a34003b4eb482c5014eb4a1e3b80012_1q7iagwvtsaez1gvfqcjlyxo7p&uid=8a34003b4eb482c5014eb4a1e3b80012"},{"id":null,"code":"module_mhjq","name":"门户集群","avatar":"http://10.180.120.231/static/icon/mhjq.png","todos":"0","sort":6,"openType":"1","openUrl":"http://10.180.120.231/banknotice/phone/portal/index?token=8a34003b4eb482c5014eb4a1e3b80012_1q7iagwvtsaez1gvfqcjlyxo7p&uid=8a34003b4eb482c5014eb4a1e3b80012"},{"id":null,"code":"module_wtsz","name":"委托设置","avatar":"http://10.180.120.231/static/icon/wtsz.png","todos":"0","sort":7,"openType":"1","openUrl":"http://10.180.120.231/bankappmobile/phone/agent/list?token=8a34003b4eb482c5014eb4a1e3b80012_1q7iagwvtsaez1gvfqcjlyxo7p&uid=8a34003b4eb482c5014eb4a1e3b80012"}]
     */

    private boolean succeed;
    private int code;
    private String msg;
    /**
     * id : null
     * code : module_gwgl
     * name : 公文管理
     * avatar : http://10.180.120.231/static/icon/gwgl.png
     * todos : 52
     * sort : 1
     * openType : 0
     * openUrl : null
     */

    private List<ModulItemEntity> data;

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(List<ModulItemEntity> data) {
        this.data = data;
    }

    public boolean isSucceed() {
        return succeed;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<ModulItemEntity> getData() {
        return data;
    }

    public ModuleEntity() {
    }
    
}
