package com.qiaose.shiro.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.qiaose.entity.shiro.Permission;


public interface PermissionService extends IService<Permission> {


    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

}
