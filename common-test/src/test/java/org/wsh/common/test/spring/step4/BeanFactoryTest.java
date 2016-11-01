package org.wsh.common.test.spring.step4;

import org.junit.Test;
import org.wsh.common.test.spring.step4.factory.AutowireCapableBeanFactory;
import org.wsh.common.test.spring.step4.factory.BeanFactory;
import org.wsh.common.test.spring.step4.io.ResourceLoader;
import org.wsh.common.test.spring.step4.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016/10/31 14:21
 */
public class BeanFactoryTest {

	@Test
	public void test() throws Exception {
		// 1.读取配置
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
		xmlBeanDefinitionReader.loadBeanDefinitions("spring/ioc/step4/tinyioc.xml");

		// 2.初始化BeanFactory并注册bean
		BeanFactory beanFactory = new AutowireCapableBeanFactory();
		for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
			beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
		}

		// 3.获取bean
		HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
		helloWorldService.helloWorld();

	}
}
