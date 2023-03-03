package com.qiaose.controller.shiro;


import com.qiaose.entity.Result;
import com.qiaose.entity.shiro.Permission;

import com.qiaose.shiro.service.PermissionService;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/permission")
@Api(tags = "权限接口")
@Validated
public class PermissionController {


    @Resource
    PermissionService permissionService;

    @GetMapping("select/{id}")
    public Result<?> getUser(@PathVariable String id){
        Permission role = permissionService.selectByPrimaryKey(Long.parseLong(id));
        return Result.success(role);
    }

    @PostMapping("insert")
    public Result<?> insertUser(@RequestBody Permission permission){

        boolean insert = permissionService.save(permission);
        if (Boolean.FALSE.equals(insert)) {
            return Result.error();
        }
        return   Result.success();
    }

    @PostMapping("update")
    public Result<?> updateUser(@RequestBody Permission permission){

        boolean update = permissionService.updateById(permission);
        return  Boolean.FALSE.equals(update)?  Result.error() : Result.success();
    }
}
