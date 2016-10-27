package org.wsh.common.test.spring.step1;

import org.junit.Test;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  bean工厂测试类
 * since Date： 2016/10/27 14:45
 */
public class BeanFactoryTest {

	@Test
	public void test() {
		// 1.初始化beanfactory
		BeanFactory beanFactory = new BeanFactory();

		// 2.注入bean
		BeanDefinition beanDefinition = new BeanDefinition(new HelloWorldService());
		beanFactory.registerBeanDefinition("helloWorldService", beanDefinition);

        // 3.获取bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.helloWorld();


    }
}
