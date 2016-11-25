package org.wsh.common.cache.service.impl;

import org.springframework.stereotype.Service;
import org.wsh.common.cache.annotation.PageCache;
import org.wsh.common.cache.service.CacheService;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016-11-25 10:35
 */
@Service("cacheService")
public class CacheServiceImpl implements CacheService{

    @Override
    @PageCache(expire = 1000)
    public String getById(Long id){

        return "Hello Cache...";
    }
}
