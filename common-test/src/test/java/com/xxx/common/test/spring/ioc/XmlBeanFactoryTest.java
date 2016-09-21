package com.xxx.common.test.spring.ioc;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * XmlBeanFactory实现IoC容器的基本过程
 * File Name: <XmlBeanFactoryTest.java>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used: <JDK1.6> 
 * @author wsh[wsh.ck@qq.com]
 * @since Date： 2015-4-27 下午3:53:45
 */
public class XmlBeanFactoryTest {
	
	private final Logger log = LoggerFactory.getLogger(XmlBeanFactoryTest.class);

	@Test
	public void test() {
		//创建IoC容器管理的Bean的xml配置文件，即定位资源  
		ClassPathResource resource = new ClassPathResource("beans.xml");  
		//创建BeanFactory  
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory ();  
		//创键Bean定义读取器  
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);  
		//使用Bean定义读取器读入Bean配置信息，即载入配置  
		int beanNum = reader.loadBeanDefinitions(resource);  
		log.info(String.valueOf(beanNum));
		
	}

}

