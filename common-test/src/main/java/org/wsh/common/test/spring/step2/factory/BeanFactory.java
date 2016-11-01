package org.wsh.common.test.spring.step2.factory;


import org.wsh.common.test.spring.step2.BeanDefinition;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  bean工厂接口
 * since Date： 2016/10/27 15:53
 */
public interface BeanFactory {

    Object getBean(String name);

    void registerBeanDefinition(String name, BeanDefinition beanDefinition);
}
