package com.inmaytide.orbit.auz.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

public class RedisShiroCache<K, V> implements Cache<K, V> {

    private static final Logger log = LoggerFactory.getLogger(RedisShiroCache.class);

    private static String KEY_PREFIX = "shiro_cache";

    private final RedisCache cache;

    private final RedisCacheWriter redisCacheWriter;

    public RedisShiroCache(org.springframework.cache.Cache cache) {
        Assert.notNull(cache, "Cache argument cannot be null.");
        Assert.isInstanceOf(RedisCache.class, cache, "Cache argument must be RedisCache instance.");
        this.cache = (RedisCache) cache;
        this.redisCacheWriter = (RedisCacheWriter) cache.getNativeCache();
    }

    private String buildKey(K k) {
        return new StringBuilder(KEY_PREFIX).append("_").append(Objects.toString(k, "")).toString();
    }

    @Override
    @SuppressWarnings("unchecked")
    public V get(K k) throws CacheException {
        log("Getting object", k);
        ValueWrapper value = cache.get(buildKey(k));
        if (null == value) {
            log.info("Element for [{}] is null.");
            return null;
        }
        return (V) value.get();
    }

    @Override
    @SuppressWarnings("unchecked")
    public V put(K k, V v) throws CacheException {
        log("Putting object", k);
        ValueWrapper value = cache.putIfAbsent(buildKey(k), v);
        return value == null ? null : (V) value.get();
    }

    @Override
    @SuppressWarnings("unchecked")
    public V remove(K k) throws CacheException {
        log("Removing object", k);
        ValueWrapper value = cache.get(buildKey(k));
        if (value == null) {
            return null;
        }
        this.redisCacheWriter.remove(cache.getName(), buildKey(k).getBytes());
        return (V) value.get();
    }

    @Override
    public void clear() throws CacheException {
        log.info("Clearing all objects from cache [" + cache + "]");
        cache.clear();
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keys() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException();
    }

    private void log(String operation, K k) {
        log.info("{} using cache [{}] for key [{}]", operation, cache.getName(), k);
    }
}
