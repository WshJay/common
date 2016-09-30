package org.wsh.common.test.spring.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.Resource;

/**
 * XmlBeanFactory只提供最基本的IoC容器功能,主要读取以XML形式定义的BeanDefinition。
 * File Name: <XmlBeanFactory.java>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used: <JDK1.6> 
 * @author wsh[wsh.ck@qq.com]
 * @since Date： 2015-4-27 下午3:44:35
 */
@Deprecated
public class XmlBeanFactory extends DefaultListableBeanFactory {

	//读取XML形式的Bean定义，然后解析XML生成IoC管理的Bean
	private final XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(this);


	/**Resouce是Spring中用来封装IO操作的接口
	 * Create a new XmlBeanFactory with the given resource,
	 * which must be parsable using DOM.
	 * @param resource XML resource to load bean definitions from
	 * @throws BeansException in case of loading or parsing errors
	 */
	public XmlBeanFactory(Resource resource) throws BeansException {
		this(resource, null);
	}

	/**调用父类的构造方法，同时调用loadBeanDefinitions方法从指定XML文件加载Bean定义
	 * Create a new XmlBeanFactory with the given input stream,
	 * which must be parsable using DOM.
	 * @param resource XML resource to load bean definitions from
	 * @param parentBeanFactory parent bean factory
	 * @throws BeansException in case of loading or parsing errors
	 */
	public XmlBeanFactory(Resource resource, BeanFactory parentBeanFactory) throws BeansException {
		super(parentBeanFactory);
		this.reader.loadBeanDefinitions(resource);
	}
}

