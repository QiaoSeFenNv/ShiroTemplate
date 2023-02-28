package com.qiaose.configuration;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.config.ShiroAnnotationProcessorConfiguration;
import org.apache.shiro.spring.config.ShiroBeanConfiguration;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroWebConfiguration;
import org.apache.shiro.spring.web.config.ShiroWebFilterConfiguration;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig  {


    @Bean
    public MyRealm userRealm() {
        return new MyRealm();
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
        return securityManager;
    }


    @Bean
    public DefaultShiroFilterChainDefinition shiroFilterChainDefinition(){
        DefaultShiroFilterChainDefinition chain = new DefaultShiroFilterChainDefinition();
        // 设置哪些请求可以匿名访问
        chain.addPathDefinition("/login/**", "anon");

        // 由于使用Swagger调试，因此设置所有Swagger相关的请求可以匿名访问
        chain.addPathDefinition("/swagger-ui/index.html", "authc");
        chain.addPathDefinition("/swagger-resources", "authc");
        chain.addPathDefinition("/swagger-resources/configuration/security", "authc");
        chain.addPathDefinition("/swagger-resources/configuration/ui", "authc");
        chain.addPathDefinition("/v2/api-docs", "authc");
        chain.addPathDefinition("/webjars/springfox-swagger-ui/**", "authc");

        //除了以上的请求外，其它请求都需要登录
        chain.addPathDefinition("/**", "authc");
        return chain;
    }

    /**
     * 路径过滤规则
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
