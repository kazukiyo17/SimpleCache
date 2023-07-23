package com.xyunli.cache.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CacheEntity<V> {
    private V data;
    private long expireTime;
    private long refreshTime;
}
