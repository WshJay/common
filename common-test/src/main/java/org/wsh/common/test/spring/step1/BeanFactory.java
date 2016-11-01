package org.wsh.common.test.spring.step1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  IoC最基本的角色有两个：容器(BeanFactory)和Bean本身。这里使用BeanDefinition来封装了bean对象，这样可以保存一些额外的元信息.
 * since Date： 2016/10/27 14:38
 */
public class BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

    public Object getBean(String name) {
        return beanDefinitionMap.get(name).getBean();
    }

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }

}
