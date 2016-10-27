package org.wsh.common.test.spring.step2;

import org.junit.Test;
import org.wsh.common.test.spring.step2.factory.BeanFactory;
import org.wsh.common.test.spring.step2.factory.AutowireCapableBeanFactory;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  Spring IOC模拟测试
 * since Date： 2016/10/27 15:58
 */
public class BeanFactoryTest {

	@Test
	public void test() {
		// 1.初始化beanfactory
		BeanFactory beanFactory = new AutowireCapableBeanFactory();

		// 2.注入bean
		BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName("org.wsh.common.test.spring.step2.HelloWorldService");
		beanFactory.registerBeanDefinition("helloWorldService", beanDefinition);

        // 3.获取bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
		helloWorldService.helloWorld();
    }
}
