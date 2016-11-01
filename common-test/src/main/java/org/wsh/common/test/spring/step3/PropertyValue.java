package org.wsh.common.test.spring.step3;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  用于bean的属性注入
 * since Date： 2016/10/27 17:07
 */
public class PropertyValue {

    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
