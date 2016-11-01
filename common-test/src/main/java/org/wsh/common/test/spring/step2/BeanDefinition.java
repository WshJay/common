package org.wsh.common.test.spring.step2;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  Bean模型
 * since Date： 2016/10/27 15:53
 */
public class BeanDefinition {

	private Object bean;

	private Class beanClass;

	private String beanClassName;

	public BeanDefinition() {
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}

	public Class getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(Class beanClass) {
		this.beanClass = beanClass;
	}

	public String getBeanClassName() {
		return beanClassName;
	}

	public void setBeanClassName(String beanClassName) {
		this.beanClassName = beanClassName;
		try {
			this.beanClass = Class.forName(beanClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Object getBean() {
		return bean;
	}

}
