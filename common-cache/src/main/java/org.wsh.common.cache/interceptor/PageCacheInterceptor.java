package org.wsh.common.cache.interceptor;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.wsh.common.cache.annotation.PageCache;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  分页缓存拦截器
 * since Date： 2016-11-24 15:59
 */
@Aspect
@Component
public class PageCacheInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(PageCacheInterceptor.class);

    private static final String CACHE_NAME = "Your unique cache name";

    private static final String CACHE_KEY = "common";

//    @Autowired
//    private CentralizeCacheService centralizeCacheService;

    @Autowired
    private CacheManager cacheManager;

    /**
     * 搭配 AspectJ 指示器“@annotation()”可以使本切面成为某个注解的代理实现
     */
    @Around("@annotation(org.wsh.common.cache.annotation.PageCache)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("joinPoint=>" + joinPoint);
        String cacheKey = getCacheKey(joinPoint);
//        Serializable serializable = cacheManager.get(CACHE_NAME, cacheKey);
//        if (serializable != null) {
//            logger.info("cache hit，key [{}]", cacheKey);
//            return serializable;
//        } else {
//            logger.info("cache miss，key [{}]", cacheKey);
            Object result = joinPoint.proceed(joinPoint.getArgs());
            if (result == null) {
                logger.error("fail to get data from source，key [{}]", cacheKey);
            } else {
                PageCache methodCache = getAnnotation(joinPoint, PageCache.class);
                logger.info("key:" + methodCache.key());
                logger.info("expire:" + methodCache.expire());
////                centralizeCacheService.put(CACHE_NAME, methodCache.expire(), cacheKey, (Serializable) result);
            }
            logger.info("result:" + result);
            return result;
//           }
    }

    /**
     * 根据类名、方法名和参数值获取唯一的缓存键
     * @return 格式为 "包名.类名.方法名.参数类型.参数值"，类似 "your.package.SomeService.getById(int).123"
     */
    private String getCacheKey(ProceedingJoinPoint joinPoint) {
        return String.format("%s.%s",
                joinPoint.getSignature().toString().split("\\s")[1], StringUtils.join(joinPoint.getArgs(), ","));
    }

    private <T extends Annotation> T getAnnotation(ProceedingJoinPoint jp, Class<T> clazz) {
        MethodSignature sign = (MethodSignature) jp.getSignature();
        Method method = sign.getMethod();
        return method.getAnnotation(clazz);
    }

}
