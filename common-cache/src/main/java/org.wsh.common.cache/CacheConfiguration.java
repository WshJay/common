package org.wsh.common.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016-11-23 17:33
 */
@Configuration
public class CacheConfiguration {
    @Bean
    CacheManager cacheManager()
    {
        ConcurrentMapCacheManager cmcm = new ConcurrentMapCacheManager("cacheValue");
        return cmcm;
    }
}
