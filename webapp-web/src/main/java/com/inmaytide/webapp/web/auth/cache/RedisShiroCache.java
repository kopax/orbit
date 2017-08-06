package com.inmaytide.webapp.web.auth.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.util.Assert;

import java.util.*;

public class RedisShiroCache<K, V> implements Cache<K, V> {

    private static final Logger log = LoggerFactory.getLogger(RedisShiroCache.class);

    private final RedisCache cache;

    private final RedisOperations redisOperations;

    public RedisShiroCache(org.springframework.cache.Cache cache) {
        Assert.notNull(cache, "Cache argument cannot be null.");
        Assert.isInstanceOf(RedisCache.class, cache, "Cache argument must be RedisCache instance.");
        this.cache = (RedisCache) cache;
        redisOperations = (RedisOperations) cache.getNativeCache();
    }

    @Override
    @SuppressWarnings("unchecked")
    public V get(K k) throws CacheException {
        log("Getting object", k);
        if (k == null) {
            return null;
        }
        org.springframework.cache.Cache.ValueWrapper value = cache.get(k);
        if (null == value) {
            log.debug("Element for [{}] is null.");
            return null;
        }
        return (V) value.get();
    }

    @Override
    @SuppressWarnings("unchecked")
    public V put(K k, V v) throws CacheException {
        log("Putting object", k);
        org.springframework.cache.Cache.ValueWrapper value = cache.putIfAbsent(k, v);
        return value == null ? null : (V) value.get();
    }

    @Override
    @SuppressWarnings("unchecked")
    public V remove(K k) throws CacheException {
        log("Removing object", k);
        org.springframework.cache.Cache.ValueWrapper value = cache.get(k);
        if (value == null) {
            return null;
        }
        redisOperations.delete(k);
        return (V) value.get();
    }

    @Override
    public void clear() throws CacheException {
        log.debug("Clearing all objects from cache [" + cache + "]");
        cache.clear();
    }

    @Override
    public int size() {
        Object size = redisOperations.execute(RedisServerCommands::dbSize);
        return Integer.valueOf(Objects.toString(size, "0"));
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<K> keys() {
        return redisOperations.keys("*");
    }

    @Override
    public Collection<V> values() {
        Set<K> key = keys();
        List list = new ArrayList<V>(key.size());
        key.forEach(k -> list.add(get(k)));
        return list;
    }

    private void log(String operation, K k) {
        log.debug("{} using cache [{}] for key [{}]", operation, cache, k);
    }
}
