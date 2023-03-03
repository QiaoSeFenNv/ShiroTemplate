package com.qiaose.controller.shiro;


import com.qiaose.entity.Result;
import com.qiaose.entity.shiro.User;
import com.qiaose.entity.shiro.UserRole;
import com.qiaose.shiro.service.UserRoleService;
import com.qiaose.shiro.service.UserService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/user")
@Api(tags = "用户接口")
@Validated
public class UserController {


    @Resource
    private UserService userService ;


    @Resource
    UserRoleService userRoleService;


    /**
     * 这里我简化的查询出来权限的名称：将 ”other“ 变为 自身对应 ”3“（id）
     * @param id
     * @return
     */
    @RequiresRoles("3")
    @GetMapping("select/{id}")
    public Result<?> getUser(@PathVariable String id){
        User user = userService.selectByPrimaryKey(Long.parseLong(id));
        return Result.success(user);
    }

    /**
     * 普通注册（添加）用户，可以通过传递 roleId 进行 角色赋予，默认为 3 other 角色
     * @param user
     * @param roleId
     * @return
     */
    @PostMapping("insert/{roleId}")
    public Result<?> insertUser(@RequestBody User user,@PathVariable String roleId){

        boolean insert = userService.save(user);
        if (Boolean.FALSE.equals(insert)) {
            return Result.error();
        }

        // mybatis-plus 添加成功后会返回实体，并且会对于返回 id 值 默认是 other 角色
        UserRole userRole = UserRole.builder().userId(user.getId()).userId(
                StringUtils.isEmpty(roleId) ? 3 : Long.parseLong(roleId)
        ).build();

        boolean save = userRoleService.save(userRole);
        if (Boolean.FALSE.equals(insert)) {
            return Result.error();
        }
        return   Result.success();
    }


    @PostMapping("update")
    public Result<?> updateUser(@RequestBody User user){

        boolean update = userService.updateById(user);
        return  Boolean.FALSE.equals(update)?  Result.error() : Result.success();
    }



}
