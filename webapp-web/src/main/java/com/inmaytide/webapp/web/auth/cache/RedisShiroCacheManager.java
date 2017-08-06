package com.inmaytide.webapp.web.auth.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RedisShiroCacheManager implements CacheManager, Destroyable {

    @Resource
    private RedisCacheManager cacheManager;

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return new RedisShiroCache<K, V>(cacheManager.getCache(name));
    }

    @Override
    public void destroy() throws Exception {

    }

}
