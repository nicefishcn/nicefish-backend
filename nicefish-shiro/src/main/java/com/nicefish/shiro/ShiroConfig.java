package com.nicefish.shiro;

import com.nicefish.shiro.realm.service.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    private static Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean(name = "authenticationService")
    public AuthenticationService getSimpleAuthenticationService() {
        return new AuthenticationServiceImpl();
    }

    @Bean(name = "serviceRealm")
    public ServiceRealm getShiroRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher, @Autowired AuthenticationService authenticationService) {
        ServiceRealm serviceRealm = new ServiceRealm();
        serviceRealm.setCredentialsMatcher(matcher);
        serviceRealm.setAuthenticationService(authenticationService);
        serviceRealm.setPermissionResolver(new SimplePermissionResolver());
        return serviceRealm;
    }

//    @Bean(name = "shiroEhcacheManager")
//    public EhCacheManager getEhCacheManager() {
//        EhCacheManager em = new EhCacheManager();
//        em.setCacheManagerConfigFile("classpath:ehcache/ehcache-shiro.xml");
//        return em;
//    }

    @Bean(name = "credentialsMatcher")
    public HashedCredentialsMatcher getCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("sha-512");
        matcher.setHashIterations(1024);
        matcher.setStoredCredentialsHexEncoded(true);
        return matcher;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("serviceRealm") ServiceRealm shiroRealm) {
        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
        dwsm.setRealm(shiroRealm);
        //dwsm.setCacheManager(getEhCacheManager());
        return dwsm;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(@Qualifier("securityManager") DefaultWebSecurityManager dwsm) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(dwsm);
        return new AuthorizationAttributeSourceAdvisor();
    }

    @Bean(name = "restPerms")
    public RestPermissionsAuthorizationFilter getRestPerms() {
        return new RestPermissionsAuthorizationFilter();
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager dwsm) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(dwsm);

        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/user/find/*", "restPerms");
        filterChainDefinitionMap.put("/user/register", "anon");
        filterChainDefinitionMap.put("/post/**", "anon");
        filterChainDefinitionMap.put("/", "anon");
        //filterChainDefinitionMap.put("/**","restPerms"); // 缺filter

        /*
         shiroFilterFactoryBean.setLoginUrl("/admin/login");
         shiroFilterFactoryBean.setSuccessUrl("/admin/index");
         shiroFilterFactoryBean.setUnauthorizedUrl("/user/login");
         filterChainDefinitionMap.put("/user/login", "anon");
         filterChainDefinitionMap.put("/user/**", "authc");
         filterChainDefinitionMap.put("/static/**", "anon");
         filterChainDefinitionMap.put("/public/**", "anon");
         filterChainDefinitionMap.put("/admin/test/list", "perms[user:view]");
         filterChainDefinitionMap.put("/admin/**", "anon");
        */

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public FilterRegistrationBean delegatingFilterProxy() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        //  该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*");// 可以自己灵活的定义很多，避免一些根本不需要被Shiro处理的请求被包含进来
        return filterRegistration;
    }

}