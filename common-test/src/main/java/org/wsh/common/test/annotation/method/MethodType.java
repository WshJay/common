package org.wsh.common.test.annotation.method;

import java.lang.annotation.*;

/**
 * @author wsh
 * @JDK-version: JDK1.8
 * @comments: 定义一个作用到方法的注解
 * @since Date： 2016-08-25 17:15
 */
@Documented//文档
@Retention(RetentionPolicy.RUNTIME)//在运行时可以获取
@Target({ ElementType.TYPE, ElementType.METHOD })//作用到类，方法，接口上等
public @interface MethodType {

    //枚举类型
    public enum MethodTypeEnum {
        TYPE1, TYPE2
    }

    //实际的值
    public MethodTypeEnum methodType() default MethodTypeEnum.TYPE1;
}
