package com.inmaytide.orbit;

import com.inmaytide.orbit.web.auth.ShiroRealm;
import com.inmaytide.orbit.web.auth.cache.RedisSessionDao;
import com.inmaytide.orbit.web.auth.cache.RedisShiroCacheManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class OrbitApplication {

    @Bean
    public WebMvcConfigurerAdapter corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/*").allowedOrigins("*");
            }
        };
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
    public DefaultWebSecurityManager securityManager(ShiroRealm realm, RedisShiroCacheManager cacheManager, SessionManager sessionManager) {
        DefaultWebSecurityManager bean = new DefaultWebSecurityManager();
        bean.setRealm(realm);
        bean.setCacheManager(cacheManager);
        bean.setSessionManager(sessionManager);
        return bean;
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition inst = new DefaultShiroFilterChainDefinition();
        inst.addPathDefinition("/css/**", "anon");
        inst.addPathDefinition("/js/**", "anon");
        inst.addPathDefinition("/login", "anon");
        inst.addPathDefinition("/**", "authc");
        return inst;
    }

    public static void main(String[] args) {
        SpringApplication.run(OrbitApplication.class, args);
    }
}
