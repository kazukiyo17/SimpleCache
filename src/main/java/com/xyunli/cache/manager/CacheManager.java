package com.xyunli.cache.manager;

import com.xyunli.cache.entity.CacheEntity;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public interface CacheManager<V> {
    void put(String key, CacheEntity cache);
    void put(String key, Object data, long expireTime);

    V get(String key);
    List<String> getAllKeys();

    void clear(String key);

    boolean isExpired(String key);

    boolean contains(String key);
}
