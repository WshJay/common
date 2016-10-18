package org.wsh.common.util.annotation;

import java.lang.annotation.*;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  水果颜色注解
 * since Date： 2016-10-18 17:22
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitColor {
    /**
     * 颜色枚举
     * @author peida
     *
     */
    public enum Color{ BULE,RED,GREEN};

    /**
     * 颜色属性
     * @return Color
     */
    Color fruitColor() default Color.GREEN;

}
