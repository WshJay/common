package org.wsh.common.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  分页缓存注解
 * since Date： 2016-11-24 15:57
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PageCache {

    /**
     * 缓存过期时间，单位是秒
     */
    int expire();
}
