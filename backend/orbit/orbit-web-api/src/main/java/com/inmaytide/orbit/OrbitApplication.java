package com.inmaytide.orbit;

import com.inmaytide.orbit.http.CorsProperties;
import com.inmaytide.orbit.model.sys.User;
import com.inmaytide.orbit.utils.SessionHelper;
import com.inmaytide.orbit.web.auth.JWTOrAuthenticationFilter;
import com.inmaytide.orbit.web.auth.cache.RedisSessionDao;
import com.inmaytide.orbit.web.auth.cache.RedisShiroCacheManager;
import com.inmaytide.orbit.web.auth.realm.FormRealm;
import com.inmaytide.orbit.web.auth.realm.JWTRealm;
import com.inmaytide.orbit.web.auth.strategy.FirstExceptionStrategy;
import com.inmaytide.orbit.web.filter.CorsFilter;
import io.jsonwebtoken.lang.Assert;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mybatis.domains.AuditDateAware;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.sql.DataSource;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableCaching(proxyTargetClass = true)
@EnableTransactionManagement(proxyTargetClass = true)
@EnableAsync
public class OrbitApplication {

    @Value("#{ @environment['shiro.loginUrl'] ?: '/login.jsp' }")
    protected String loginUrl;

    @Value("#{ @environment['shiro.successUrl'] ?: '/' }")
    protected String successUrl;

    @Value("#{ @environment['shiro.unauthorizedUrl'] ?: null }")
    protected String unauthorizedUrl;

    @Resource
    private CorsProperties corsProperties;

    @Bean
    public AuditorAware<Long> auditorAware() {
        return () -> {
            Object object = SessionHelper.getPrincipal();
            Assert.isInstanceOf(User.class, object);
            return ((User) object).getId();
        };
    }

    @Bean
    public AuditDateAware<LocalDateTime> auditDateAware() {
        return LocalDateTime::now;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new CorsFilter(corsProperties));
        filterRegistrationBean.setUrlPatterns(Collections.singletonList(corsProperties.getMapping()));
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator bean = new DefaultAdvisorAutoProxyCreator();
        bean.setProxyTargetClass(true);
        return bean;
    }


    @Bean
    public RedisCacheManager redisCacheManager(RedisTemplate redisTemplate) {
        return new RedisCacheManager(redisTemplate);
    }

    @Bean
    public SessionManager sessionManager(RedisSessionDao sessionDao) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(sessionDao);
        return sessionManager;
    }

    @Bean
    protected Authenticator authenticator(FirstExceptionStrategy strategy) {
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        authenticator.setAuthenticationStrategy(strategy);
        return authenticator;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(RedisShiroCacheManager cacheManager, SessionManager sessionManager,
                                                     JWTRealm jwtRealm, FormRealm formRealm, Authenticator authenticator) {
        DefaultWebSecurityManager bean = new DefaultWebSecurityManager();
        bean.setCacheManager(cacheManager);
        bean.setSessionManager(sessionManager);
        bean.setAuthenticator(authenticator);
        bean.setRealms(Arrays.asList(jwtRealm, formRealm));
        return bean;
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition inst = new DefaultShiroFilterChainDefinition();
        inst.addPathDefinition("/login", "anon");
        inst.addPathDefinition("/captcha", "anon");
        inst.addPathDefinition("/lang/*", "anon");
        inst.addPathDefinition("/**", "authc");
        return inst;
    }

    @Bean
    protected ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager,
                                                            ShiroFilterChainDefinition shiroFilterChainDefinition) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();

        filterFactoryBean.setLoginUrl(loginUrl);
        filterFactoryBean.setSuccessUrl(successUrl);
        filterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);

        filterFactoryBean.setSecurityManager(securityManager);
        filterFactoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinition.getFilterChainMap());

        Map<String, Filter> filters = new HashMap<>();
        filters.put("authc", new JWTOrAuthenticationFilter(corsProperties));
        filterFactoryBean.setFilters(filters);
        return filterFactoryBean;
    }

    @Bean
    public ConfigurableCaptchaService configurableCaptchaService() {
        ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
        cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
        cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
        cs.setHeight(50);
        return cs;
    }

    public static void main(String[] args) {
        SpringApplication.run(OrbitApplication.class, args);
    }
}

