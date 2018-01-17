package org.wsh.common.cache.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Service;
import org.wsh.common.cache.annotation.PageCache;
import org.wsh.common.cache.service.CacheService;
import org.wsh.common.cache.service.RedisService;
import org.wsh.common.util.logger.LoggerService;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016-11-25 10:35
 */
@Service("cacheService")
public class CacheServiceImpl extends LoggerService implements CacheService {

    @Resource
    private JedisConnectionFactory jedisConnectionFactory;

    @Resource
    private RedisService redisService;

    public static int exceptionNum;

    @Override
    public String getById(Long id) {

        return "Hello Cache...";
    }

    @Override
    @Cacheable(key = "'common:demo:id:' + #id")
    public String getDemoDOById(Long id) {

        return "Hello Cache...";
    }

    @Override
    public boolean setCache(String key, String value) {
        String result = jedisConnectionFactory.getShardInfo().createResource().set(key, value);
        if (StringUtils.isNotBlank(result)) {
            return true;
        }
        return false;
    }

    @Override
    public Object getCache(String key) {
        String value = null;
        try {
            value = jedisConnectionFactory.getShardInfo().createResource().get(key);
//            value = redisService.get(key);
            logger.info("ExceptionNum:" + exceptionNum);
        } catch (Exception e) {
            logger.error("获取缓存信息异常!", e);
            exceptionNum++;
        }
        return value;
    }
}
