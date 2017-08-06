package com.inmaytide.webapp;

import com.inmaytide.webapp.web.auth.ShiroRealm;
import com.inmaytide.webapp.web.auth.cache.RedisShiroCacheManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootApplication
public class WebApplication {

    @Resource
    RedisTemplate redisTemplate;

    @Bean
    public RedisCacheManager redisCacheManager() {
        return new RedisCacheManager(redisTemplate);
    }

    @Bean
    public DefaultWebSecurityManager securityManager(ShiroRealm realm, RedisShiroCacheManager cacheManager) {
        DefaultWebSecurityManager bean = new DefaultWebSecurityManager();
        bean.setRealm(realm);
        bean.setCacheManager(cacheManager);
        System.out.println(redisTemplate.getClientList().size());
        return bean;
    }


    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
