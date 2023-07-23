package com.xyunli.cache.manager.impl;

import com.xyunli.cache.entity.CacheEntity;
import com.xyunli.cache.manager.CacheManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CacheManagerImpl<V> implements CacheManager {
    private final Map<String, CacheEntity<V>> cacheMap = new ConcurrentHashMap<>();

    @Override
    public synchronized void put(String key, CacheEntity cache) {
        cacheMap.put(key, cache);
    }

    @Override
    public synchronized void put(String key, Object data, long expireTime) {
        long refresh = System.currentTimeMillis();
        CacheEntity cache = new CacheEntity(data, expireTime, refresh);
        log.info("key: " + key + " expireTime: " + expireTime + " refresh: " + refresh);
        cacheMap.put(key, cache);
    }

    @Override
    public synchronized V get(String key) {
        return (V)cacheMap.getOrDefault(key, null).getData();
    }

    @Override
    public List<String> getAllKeys() {
        return new ArrayList<>(cacheMap.keySet());
    }

    @Override
    public synchronized void clear(String key) {
        cacheMap.remove(key);
    }

    @Override
    public synchronized boolean isExpired(String key) {
        if(!cacheMap.containsKey(key)){
            return true;
        }
        CacheEntity cacheEntity = cacheMap.get(key);
        long expireTime = cacheEntity.getExpireTime();
        long refresh = cacheEntity.getRefreshTime();
        long current = System.currentTimeMillis();
        log.info("key: " + key + " expireTime: " + expireTime + " refresh: " + refresh + " current: " + current);
        return current - refresh >= expireTime;
    }

    @Override
    public synchronized boolean contains(String key) {
        return cacheMap.containsKey(key);
    }
}
