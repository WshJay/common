package org.wsh.common.test.spring.step2.factory;


import org.wsh.common.test.spring.step2.BeanDefinition;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  bean工厂实现
 * since Date： 2016/10/27 15:53
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object doCreateBean(BeanDefinition beanDefinition) {
        try {
            Object bean = beanDefinition.getBeanClass().newInstance();
            return bean;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
