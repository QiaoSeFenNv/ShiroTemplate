package com.qiaose.controller.shiro;


import com.qiaose.entity.Result;
import com.qiaose.entity.shiro.User;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/common")
@Api(tags = "公共接口")
@RestController
public class CommonController {


    /**
     * 简单的登录接口，后续有需要可以配置添加盐，或者其他密码加密工具进行整合
     * @param user
     * @return
     */
    @PostMapping("login")
    public Result<?> login(@RequestBody User user){

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        subject.login(usernamePasswordToken);
        return Result.success();

    }

}
