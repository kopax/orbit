package com.inmaytide.orbit.auz.cache;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
public class RedisSessionDao extends AbstractSessionDAO {

    private static final Logger log = LoggerFactory.getLogger(RedisSessionDao.class);

    public static final String DEFAULT_KEY_PREFIX = "shiro_redis_session:";

    private String keyPrefix;

    public RedisSessionDao() {
        this.keyPrefix = DEFAULT_KEY_PREFIX;
    }

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (sessionId == null) {
            log.error("session id is null");
        }
        ValueOperations<Serializable, Session> ops = redisTemplate.opsForValue();
        return ops.get(getKeyPrefix() + sessionId);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        saveSession(session);
    }

    @Override
    public void delete(Session session) {
        if (session == null || session.getId() == null) {
            log.error("session or session id is null");
            return;
        }
        redisTemplate.delete(getKeyPrefix() + session.getId());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<Session> sessions = new HashSet<Session>();
        Set<?> keys = redisTemplate.keys(getKeyPrefix() + "*");
        if (CollectionUtils.isNotEmpty(keys)) {
            final ValueOperations<Serializable, Session> ops = redisTemplate.opsForValue();
            keys.forEach(key -> sessions.add(ops.get(key)));
        }
        return sessions;
    }

    private void saveSession(Session session) {
        if (session == null || session.getId() == null) {
            log.error("session or session id is null");
            return;
        }

        ValueOperations<Serializable, Session> ops = redisTemplate.opsForValue();
        ops.set(getKeyPrefix() + session.getId(), session);
        session.setTimeout(redisTemplate.getExpire(session.getId()) * 1000);
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }
}
