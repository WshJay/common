package org.wsh.common.test.spring.step6.beans.factory;

import org.wsh.common.test.spring.step6.beans.BeanDefinition;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  bean的容器
 * since Date： 2016/10/31 14:14
 */
public interface BeanFactory {

    Object getBean(String name) throws Exception;
}
