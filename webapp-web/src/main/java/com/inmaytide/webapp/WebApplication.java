package com.inmaytide.webapp;

import com.inmaytide.webapp.web.auth.ShiroRealm;
import com.inmaytide.webapp.web.auth.cache.RedisShiroCacheManager;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class WebApplication {

    @Bean
    public RedisCacheManager redisCacheManager(RedisTemplate redisTemplate) {
        return new RedisCacheManager(redisTemplate);
    }

    @Bean
    public DefaultWebSecurityManager securityManager(ShiroRealm realm, RedisShiroCacheManager cacheManager) {
        DefaultWebSecurityManager bean = new DefaultWebSecurityManager();
        bean.setRealm(realm);
        bean.setCacheManager(cacheManager);
        return bean;
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition inst = new DefaultShiroFilterChainDefinition();
        inst.addPathDefinition("/css/**", "anon");
        inst.addPathDefinition("/js/**", "anon");
        inst.addPathDefinition("/**", "authc");
        return inst;
    }

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
