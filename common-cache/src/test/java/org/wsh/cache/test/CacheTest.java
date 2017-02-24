package org.wsh.cache.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wsh.common.cache.annotation.PageCache;
import org.wsh.common.cache.service.CacheService;

import javax.annotation.Resource;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016-11-25 10:26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/common-cache.xml"})
public class CacheTest {

    @Resource
    private CacheService cacheService;

    @Test
    public void Test(){
        cacheService.getDemoDOById(1L);
    }
}
