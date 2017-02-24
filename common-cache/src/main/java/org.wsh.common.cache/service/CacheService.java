package org.wsh.common.cache.service;

import org.springframework.stereotype.Service;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016-11-25 10:35
 */
@Service
public interface CacheService {

    public String getById(Long Id);

    public String getDemoDOById(Long id);
}
