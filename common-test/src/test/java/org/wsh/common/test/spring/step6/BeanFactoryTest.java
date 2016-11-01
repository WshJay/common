package org.wsh.common.test.spring.step6;

import org.junit.Test;
import org.wsh.common.test.spring.step6.beans.BeanDefinition;
import org.wsh.common.test.spring.step6.beans.factory.AbstractBeanFactory;
import org.wsh.common.test.spring.step6.beans.factory.AutowireCapableBeanFactory;
import org.wsh.common.test.spring.step6.beans.io.ResourceLoader;
import org.wsh.common.test.spring.step6.beans.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016/10/31 16:04
 */
public class BeanFactoryTest {

    @Test
    public void testLazy() throws Exception {
        // 1.读取配置
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("spring/ioc/step6/tinyioc.xml");

        // 2.初始化BeanFactory并注册bean(没有实例化bean)
        AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }

        // 3.获取bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }

	@Test
	public void testPreInstantiate() throws Exception {
		// 1.读取配置
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
		xmlBeanDefinitionReader.loadBeanDefinitions("spring/ioc/step6/tinyioc.xml");

		// 2.初始化BeanFactory并注册bean(没有实例化bean)
		AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
		for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
			beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
		}

        // 3.实例化bean,类似于设置懒加载为false
        beanFactory.preInstantiateSingletons();

		// 4.获取bean
//		HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
//		helloWorldService.helloWorld();

		OutputService outputService = (OutputService)beanFactory.getBean("outputService");
		outputService.output("Spring Ioc...");
		HelloWorldService helloWorldService = outputService.getHelloWorldService();
		helloWorldService.helloWorld();
	}
}
