package org.yndongyong.mvp.sample.Model;

import org.yndongyong.mvp.sample.domain.ModulItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dong on 2016/1/5.
 */
public class ModleDataMapper {
    private static ModleDataMapper ourInstance = new ModleDataMapper();

    public static ModleDataMapper getInstance() {
        return ourInstance;
    }

    private ModleDataMapper() {
        
    }

    public ModulItemModle transformModulItemEntity(ModulItem entity) {
        ModulItemModle item = null;
        if (entity != null) {
            item = new ModulItemModle();
            item.setAvatar(entity.getAvatar());
            item.setCode(entity.getCode());
            item.setId(entity.getId());
            item.setName(entity.getName());
            item.setOpenType(entity.getOpenType());
            item.setOpenUrl(entity.getOpenUrl());
            item.setSort(entity.getSort());
            item.setTodos(entity.getTodos());
        }

        return item;
    }
    public List<ModulItemModle> transformModulItemEntity(List<ModulItem> entitys) {
        List<ModulItemModle> modles = new ArrayList<ModulItemModle>();
        for (ModulItem item : entitys) {
            modles.add(transformModulItemEntity(item));
        }
        return modles;
    }
}
