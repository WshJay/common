package org.wsh.common.test.spring.step3;

import org.junit.Test;
import org.wsh.common.test.spring.step3.factory.AutowireCapableBeanFactory;
import org.wsh.common.test.spring.step3.factory.BeanFactory;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016/10/27 17:10
 */
public class BeanFactoryTest {

	@Test
	public void test() throws Exception {
		// 1.初始化beanfactory
		BeanFactory beanFactory = new AutowireCapableBeanFactory();

		// 2.bean定义
		BeanDefinition beanDefinition = new BeanDefinition();
		beanDefinition.setBeanClassName("org.wsh.common.test.spring.step3.HelloWorldService");

		// 3.设置属性
		PropertyValues propertyValues = new PropertyValues();
		propertyValues.addPropertyValue(new PropertyValue("text", "Hello World!"));
        beanDefinition.setPropertyValues(propertyValues);

		// 4.生成bean
		beanFactory.registerBeanDefinition("helloWorldService", beanDefinition);

		// 5.获取bean
		HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
		helloWorldService.helloWorld();

	}
}
