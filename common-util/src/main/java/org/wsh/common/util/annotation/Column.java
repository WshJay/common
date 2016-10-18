package org.wsh.common.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  Column属性值是RUTIME,这样注解处理器可以通过反射，获取到该注解的属性值，从而去做一些运行时的逻辑处理
 * since Date： 2016-10-18 17:12
 */
@Target(ElementType.FIELD) // 仅可用于注解类的成员变量
@Retention(RetentionPolicy.RUNTIME) // 运行时有效
public @interface Column {
    public String name() default "fieldName";
    public String setFuncName() default "setField";
    public String getFuncName() default "getField";
    public boolean defaultDBValue() default false;
}
