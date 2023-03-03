package com.qiaose.configuration;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.qiaose.entity.shiro.RolePermission;
import com.qiaose.entity.shiro.User;
import com.qiaose.entity.shiro.UserRole;
import com.qiaose.shiro.service.RolePermissionService;
import com.qiaose.shiro.service.UserRoleService;
import com.qiaose.shiro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Slf4j
public class MyRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;
    @Autowired
    RolePermissionService rolePermissionService;

    @Autowired
    UserRoleService userRoleService;



    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        log.info("账户信息：{}",token.getPrincipal());
        log.info("密码信息：{}",token.getCredentials());

        String principal = (String)token.getPrincipal();
        String credentials = String.valueOf((char[])token.getCredentials());

        log.info("账户信息：{}",principal);
        log.info("密码信息：{}",credentials);

//        new QueryWrapper<User>().eq 我觉得这种方法是写死代码，虽然方便，但是灵活性降低.推荐 lambda
        User user = userService.getBaseMapper().selectOne(
                new LambdaQueryWrapper<User>() .eq(User::getUsername,principal)

        );

        log.info("sql 账号：{}",user.getUsername());
        log.info("sql 密码：{}",user.getPassword());

        if (user == null){
            throw new AccountException("账号错误");
        }

        if (!credentials.equals(user.getPassword())) {
            throw new AccountException("密码错误");
        }

        return new SimpleAuthenticationInfo(user,credentials,getName());
    }

    /**
     * 这里我简化的查询出来权限的名称：将 ”admin“ 变为 自身对应 ”1“（id）
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        User primaryPrincipal = (User) principalCollection.getPrimaryPrincipal();

        log.info("个人信息：{}",primaryPrincipal);

        //设置权限角色
        List<UserRole> userRoles = userRoleService.getBaseMapper().selectList(
                new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, primaryPrincipal.getId())
        );
        List<String> roleIds = userRoles.stream().map(UserRole::getRoleId).map(Object::toString).collect(Collectors.toList());
        simpleAuthorizationInfo.addRoles(roleIds);

        //设置接口权限
        List<RolePermission> rolePermissions = rolePermissionService.getBaseMapper().selectList(
                new LambdaQueryWrapper<RolePermission>().in(RolePermission::getRoleId, roleIds)
        );
        List<String> permissionIds = rolePermissions.stream().map(RolePermission::getPermissionId).map(Objects::toString).collect(Collectors.toList());
        simpleAuthorizationInfo.addStringPermissions(permissionIds);

        log.info("个人信息权限信息：{},{}",roleIds,permissionIds);

        return  simpleAuthorizationInfo;
    }


}
