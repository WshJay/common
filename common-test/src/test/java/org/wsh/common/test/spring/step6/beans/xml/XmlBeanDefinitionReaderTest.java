package org.wsh.common.test.spring.step6.beans.xml;

import org.junit.Assert;
import org.junit.Test;
import org.wsh.common.test.spring.step6.beans.BeanDefinition;
import org.wsh.common.test.spring.step6.beans.io.ResourceLoader;
import org.wsh.common.test.spring.step6.beans.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016/10/31 14:19
 */
public class XmlBeanDefinitionReaderTest {

	@Test
	public void test() throws Exception {
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
		xmlBeanDefinitionReader.loadBeanDefinitions("spring/ioc/step6/tinyioc.xml");
		Map<String, BeanDefinition> registry = xmlBeanDefinitionReader.getRegistry();
		Assert.assertTrue(registry.size() > 0);
	}
}
