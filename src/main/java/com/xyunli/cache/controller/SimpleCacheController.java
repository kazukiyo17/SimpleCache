package com.xyunli.cache.controller;

import com.xyunli.cache.service.SimpleCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/simple/cache")
public class SimpleCacheController {
    @Autowired
    private SimpleCacheService simpleCacheService;

    @RequestMapping(value = "/put", method = RequestMethod.GET)
    public void put(@RequestParam("key") String key, @RequestParam("data") String data) {
        simpleCacheService.putCacheByKey(key, data);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public void get(@RequestParam("key") String key) {
        simpleCacheService.gettCacheByKey(key);
    }

}
