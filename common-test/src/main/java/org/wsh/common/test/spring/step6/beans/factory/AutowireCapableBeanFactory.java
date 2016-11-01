package org.wsh.common.test.spring.step6.beans.factory;

import org.wsh.common.test.spring.step6.BeanReference;
import org.wsh.common.test.spring.step6.beans.BeanDefinition;
import org.wsh.common.test.spring.step6.beans.PropertyValue;

import java.lang.reflect.Field;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  可自动装配内容的BeanFactory
 * since Date： 2016/10/31 14:15
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

	@Override
	protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
		Object bean = createBeanInstance(beanDefinition);
		beanDefinition.setBean(bean);
		applyPropertyValues(bean, beanDefinition);
		return bean;
	}

	protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
		return beanDefinition.getBeanClass().newInstance();
	}

	protected void applyPropertyValues(Object bean, BeanDefinition mbd) throws Exception {
		for (PropertyValue propertyValue : mbd.getPropertyValues().getPropertyValues()) {
			Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
			declaredField.setAccessible(true);
			Object value = propertyValue.getValue();
			if (value instanceof BeanReference) {
				BeanReference beanReference = (BeanReference) value;
				value = getBean(beanReference.getName());
			}
			declaredField.set(bean, value);
		}
	}
}
