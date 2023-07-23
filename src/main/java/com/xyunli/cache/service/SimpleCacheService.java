package com.xyunli.cache.service;

import com.xyunli.cache.manager.CacheManager;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SimpleCacheService {
    @Autowired
    private CacheManager cacheManagerImpl;

    public void putCacheByKey(String key, String data){
        // 10s
        long expire = 10000;
        cacheManagerImpl.put(key, data, expire);
    }

    public void gettCacheByKey(String key){
        cacheManagerImpl.get(key);
    }
}
