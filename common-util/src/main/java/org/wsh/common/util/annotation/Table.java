package org.wsh.common.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  表注解
 * since Date： 2016-10-18 17:09
 */
@Target(ElementType.TYPE) // 可以用于注解类、接口(包括注解类型) 或enum声明
public @interface Table {

    /**
     * 数据表名称注解，默认值为类名称
     * @return tableName
     */
    public String tableName() default "className";
}
