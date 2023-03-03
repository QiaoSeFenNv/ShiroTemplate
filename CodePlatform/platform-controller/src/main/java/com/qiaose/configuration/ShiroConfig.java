package com.qiaose.configuration;



import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AopAllianceAnnotationsAuthorizingMethodInterceptor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;

import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class ShiroConfig  {


    /**
     * 引入 Realm 对象。在后续 SecurityManage 进行注入
     * @return
     */
    @Bean
    public MyRealm userRealm() {
        return new MyRealm();
    }

    /**
     * 创建默认的 SecurityManager ,将引入的 Realm 进行委托
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
        return securityManager;
    }

    /**
     * Shiro 权限注解 需要 SpringbootAOP 支持
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * 将 SecurityManager 对象注入到 切面中才可以从中获取到对应权限信息
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager());
        return advisor;
    }

    /**
     * 过滤链，可以通过 Realm 进行配置或者这边静态配置，推荐动态配置权限
     * @return
     */
    @Bean
    public DefaultShiroFilterChainDefinition shiroFilterChainDefinition(){
        DefaultShiroFilterChainDefinition chain = new DefaultShiroFilterChainDefinition();
        // 设置哪些请求可以匿名访问
        chain.addPathDefinition("/common/login","anon");
        chain.addPathDefinition("/user/insert","anon");

        // 由于使用Swagger调试，因此设置所有Swagger相关的请求可以匿名访问
        chain.addPathDefinition("/swagger-ui/index.html", "anon");
        chain.addPathDefinition("/swagger-resources", "anon");
        chain.addPathDefinition("/swagger-resources/configuration/security", "anon");
        chain.addPathDefinition("/swagger-resources/configuration/ui", "anon");
        chain.addPathDefinition("/v2/api-docs", "anon");
        chain.addPathDefinition("/webjars/springfox-swagger-ui/**", "anon");

        //除了以上的请求外，其它请求都需要登录
        chain.addPathDefinition("/**", "authc");

        return chain;
    }


    /**
     * 核心组件，旧版本之前使用的 @Bean("ShiroFilter") 高版本使用 ”shiroFilterFactoryBean“ 需要注意
     * @return
     */
    @Bean(value = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        shiroFilterFactoryBean.setFilterChainDefinitionMap( shiroFilterChainDefinition().getFilterChainMap());

        return shiroFilterFactoryBean;
    }

}
