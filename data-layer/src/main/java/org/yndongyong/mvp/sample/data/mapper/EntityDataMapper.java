package org.yndongyong.mvp.sample.data.mapper;

import org.yndongyong.mvp.sample.data.AppFileItemEntity;
import org.yndongyong.mvp.sample.data.ModulItemEntity;
import org.yndongyong.mvp.sample.domain.AppFileItem;
import org.yndongyong.mvp.sample.domain.ModulItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dong on 2016/1/5.
 */
public class EntityDataMapper {
    private static EntityDataMapper ourInstance = new EntityDataMapper();

    public static EntityDataMapper getInstance() {
        return ourInstance;
    }

    private EntityDataMapper() {
    }
    public ModulItem transformModulItemEntity(ModulItemEntity userEntity) {
        ModulItem item = null;
        if (userEntity != null) {
            item = new ModulItem();
            item.setAvatar(userEntity.getAvatar());
            item.setCode(userEntity.getCode());
            item.setId(userEntity.getId());
            item.setName(userEntity.getName());
            item.setOpenType(userEntity.getOpenType());
            item.setOpenUrl(userEntity.getOpenUrl());
            item.setSort(userEntity.getSort());
            item.setTodos(userEntity.getTodos());
        }

        return item;
    }
    
    public List<ModulItem>  transformModulItemEntity(List<ModulItemEntity> modulItemEntities) {
        List<ModulItem> modulItems = new ArrayList<ModulItem>();
        for (ModulItemEntity entity : modulItemEntities) {
            modulItems.add(transformModulItemEntity(entity));
        }

        return modulItems;
    }

    public AppFileItem transformAppFileItemEntity(AppFileItemEntity entity) {
        AppFileItem item = null;

        if (entity != null) {
            item.setId(entity.getId());
            item.setBold(entity.isBold());
            item.setTitle(entity.getTitle());
            item.setUrl(entity.getUrl());
        }

        return item;

    }
    public List<AppFileItem> transformAppFileItemEntity(List<AppFileItemEntity> entity) {
        List<AppFileItem> appFileItems = new ArrayList<AppFileItem>();
        for (AppFileItemEntity item : entity) {
            appFileItems.add(transformAppFileItemEntity(item));
        }

        return appFileItems;

    }
    
}
