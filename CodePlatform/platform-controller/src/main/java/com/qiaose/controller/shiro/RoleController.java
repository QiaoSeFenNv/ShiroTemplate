package com.qiaose.controller.shiro;


import com.qiaose.entity.Result;
import com.qiaose.entity.shiro.Role;
import com.qiaose.shiro.service.RoleService;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/role")
@Api(tags = "角色接口")
@Validated
public class RoleController {


    @Resource
    RoleService roleService;

    /**
     * 这里我简化的查询出来权限的名称：将 ”other“ 变为 自身对应 ”3“（id）
     * @param id
     * @return
     */
    @GetMapping("select/{id}")
    public Result<?> getUser(@PathVariable String id){
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("3")) {
            Role role = roleService.selectByPrimaryKey(Long.parseLong(id));
            return Result.success(role);
        }
        return Result.error();
    }


    @PostMapping("insert")
    public Result<?> insertUser(@RequestBody Role role){

        boolean insert = roleService.save(role);
        if (Boolean.FALSE.equals(insert)) {
            return Result.error();
        }
        return   Result.success();
    }

    @PostMapping("update")
    public Result<?> updateUser(@RequestBody Role role){

        boolean update = roleService.updateById(role);
        return  Boolean.FALSE.equals(update)?  Result.error() : Result.success();
    }

}
