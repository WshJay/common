package org.wsh.common.test.spring.step1;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  Bean模型
 * since Date： 2016/10/27 14:38
 */
public class BeanDefinition {

    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }

}
