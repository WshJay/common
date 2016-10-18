package org.wsh.common.util.annotation;

import java.lang.annotation.*;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  自定义水果名称注解
 * since Date： 2016-10-18 17:20
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitName {
    String value() default "";
}
