package org.wsh.common.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016-10-18 17:11
 */
@Target(ElementType.FIELD) // 仅可用于注解类的成员变量
public @interface NoDBColumn {

}
