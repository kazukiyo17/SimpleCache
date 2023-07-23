package com.xyunli.cache.job;

import com.xyunli.cache.manager.CacheManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service("CacheCheckJob")
@Slf4j
public class CacheCheckJob {
    @Autowired
    private CacheManager cacheManagerImpl;

    @Scheduled(cron = "0/5 * * * * ?")
    public void startListen() {
        log.error("do CacheCheckJob");
        for(String key : cacheManagerImpl.getAllKeys()) {
            if (cacheManagerImpl.isExpired(key)) {
                cacheManagerImpl.clear(key);
                log.info(key + "缓存被清除");
            }
        }
    }
}
