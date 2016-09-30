package org.wsh.common.test.spring.ioc;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 * 从文件系统中读入Bean定义资源文件实现过程
 * File Name: <FileSystemXmlApplicationContext.java>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used: <JDK1.6> 
 * @author wsh[wsh.ck@qq.com]
 * @since Date： 2015-4-27 下午4:28:00
 */
public class FileSystemXmlApplicationContext extends AbstractXmlApplicationContext {

	/**
	 * Create a new FileSystemXmlApplicationContext for bean-style configuration.
	 * @see #setConfigLocation
	 * @see #setConfigLocations
	 * @see #afterPropertiesSet()
	 */
	public FileSystemXmlApplicationContext() {
	}

	/**
	 * Create a new FileSystemXmlApplicationContext for bean-style configuration.
	 * @param parent the parent context
	 * @see #setConfigLocation
	 * @see #setConfigLocations
	 * @see #afterPropertiesSet()
	 */
	public FileSystemXmlApplicationContext(ApplicationContext parent) {
		super(parent);
	}

	/**configLocation是Bean定义资源文件路径
	 * Create a new FileSystemXmlApplicationContext, loading the definitions
	 * from the given XML file and automatically refreshing the context.
	 * @param configLocation file path
	 * @throws BeansException if context creation failed
	 */
	public FileSystemXmlApplicationContext(String configLocation) throws BeansException {
		this(new String[] {configLocation}, true, null);
	}

	/**可以传入多个Bean定义资源文件路径 
	 * Create a new FileSystemXmlApplicationContext, loading the definitions
	 * from the given XML files and automatically refreshing the context.
	 * @param configLocations array of file paths
	 * @throws BeansException if context creation failed
	 */
	public FileSystemXmlApplicationContext(String... configLocations) throws BeansException {
		this(configLocations, true, null);
	}

	/**
	 * Create a new FileSystemXmlApplicationContext with the given parent,
	 * loading the definitions from the given XML files and automatically
	 * refreshing the context.
	 * @param configLocations array of file paths
	 * @param parent the parent context
	 * @throws BeansException if context creation failed
	 */
	public FileSystemXmlApplicationContext(String[] configLocations, ApplicationContext parent) throws BeansException {
		this(configLocations, true, parent);
	}

	/**
	 * Create a new FileSystemXmlApplicationContext, loading the definitions
	 * from the given XML files.
	 * @param configLocations array of file paths
	 * @param refresh whether to automatically refresh the context,
	 * loading all bean definitions and creating all singletons.
	 * Alternatively, call refresh manually after further configuring the context.
	 * @throws BeansException if context creation failed
	 * @see #refresh()
	 */
	public FileSystemXmlApplicationContext(String[] configLocations, boolean refresh) throws BeansException {
		this(configLocations, refresh, null);
	}

	/**FileSystemXmlApplicationContext IoC容器进行初始化的入口构造函数
	 * Create a new FileSystemXmlApplicationContext with the given parent,
	 * loading the definitions from the given XML files.
	 * @param configLocations array of file paths
	 * @param refresh whether to automatically refresh the context,
	 * loading all bean definitions and creating all singletons.
	 * Alternatively, call refresh manually after further configuring the context.
	 * @param parent the parent context
	 * @throws BeansException if context creation failed
	 * @see #refresh()
	 */
	public FileSystemXmlApplicationContext(String[] configLocations, boolean refresh, ApplicationContext parent)
			throws BeansException {
		
		// 调用父类AbstractRefreshableConfigApplicationContext的方法
		super(parent);
		// 设置Bean定义的资源文件，完成IoC容器Bean定义资源的定位
		setConfigLocations(configLocations);
		//调用父类AbstractApplicationContext的refresh()//函数启动载入Bean定义的过程，是Ioc容器载入Bean定义的入口
		if (refresh) {
			refresh();
		}
	}


	/**该方法覆盖父类DefaultResourceLoader的方法，通过Bean定义文件路径封装得到IoC容器要读入的定位Bean定义的资源 
	 * Resolve resource paths as file system paths.
	 * <p>Note: Even if a given path starts with a slash, it will get
	 * interpreted as relative to the current VM working directory.
	 * This is consistent with the semantics in a Servlet container.
	 * @param path path to the resource
	 * @return Resource handle
	 * @see org.springframework.web.context.support.XmlWebApplicationContext#getResourceByPath
	 */
	@Override
	protected Resource getResourceByPath(String path) {
		if (path != null && path.startsWith("/")) {
			path = path.substring(1);
		}
		//其他类型的IoC容器会用其他类型的Resource来定位Bean定义，如//ClasspathResource等
		return new FileSystemResource(path);
	}
}

