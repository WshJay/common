package org.wsh.common.cache.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.wsh.common.cache.annotation.PageCache;
import org.wsh.common.cache.service.CacheService;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016-11-25 10:35
 */
@Service("cacheService")
public class CacheServiceImpl implements CacheService{

    @Override
    @PageCache(key="'common:id:' + #id",expire = 1000)
    public String getById(Long id){

        return "Hello Cache...";
    }

    @Cacheable(key="'common:demo:id:' + #id")
    public String getDemoDOById(Long id){

        return "Hello Cache...";
    }
}
