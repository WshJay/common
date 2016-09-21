package com.xxx.common.test.annotation.type;

import java.lang.annotation.*;

/**
 * @author wsh
 * @JDK-version: JDK1.8
 * @comments: 定义一个用户名的自定义注解
 * @since Date： 2016-08-25 17:08
 */
@Documented //文档
@Retention(RetentionPolicy.RUNTIME) //在运行时可以获取
@Target({ ElementType.TYPE, ElementType.METHOD}) //作用到类，方法，接口上等
@Inherited //子类会继承
public @interface UserNameAnnotations {
    public String value() default ""; //使用的时候 @UserNameAnnotations(value="xxx")
}

