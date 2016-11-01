package org.wsh.common.test.spring.step6;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  bean中注入bean实体
 * since Date： 2016/10/31 15:24
 */
public class BeanReference {

    private String name;

    private Object bean;

    public BeanReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
}
