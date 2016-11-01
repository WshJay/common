package org.wsh.common.test.spring.step5.factory;

import org.wsh.common.test.spring.step5.BeanDefinition;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  bean的容器
 * since Date： 2016/10/31 14:14
 */
public interface BeanFactory {

    Object getBean(String name) throws Exception;

    void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception;
}
