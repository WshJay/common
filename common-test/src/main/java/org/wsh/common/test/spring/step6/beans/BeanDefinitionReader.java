package org.wsh.common.test.spring.step6.beans;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  从配置中读取BeanDefinitionReader
 * since Date： 2016/10/31 14:18
 */
public interface BeanDefinitionReader {

    void loadBeanDefinitions(String location) throws Exception;
}
