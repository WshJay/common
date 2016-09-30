package org.wsh.common.test.spring.ioc;

import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * ApplicationContext是一个在BeanFactory基础进行功能扩展的，最常用的IoC容器。 
 * File Name:<ApplicationContext.java> 
 * Comments: <对此类的描述，可以引用系统设计中的描述> 
 * JDK version used: <JDK1.6>
 * @author wsh[wsh.ck@qq.com]
 * @since Date： 2015-4-27 下午4:11:51
 */
public interface ApplicationContext extends ListableBeanFactory,
		HierarchicalBeanFactory, MessageSource, ApplicationEventPublisher,
		ResourcePatternResolver {
	
	/**
	 * ApplicationContext的特性：
	 * ApplicationContext是Spring提供的一个高级的IoC容器，它除了能够提供IoC容器的基本功能外，还为用户提供了以下的附加服务：
	 * a.支持不同的信息源：
	 * ApplicationContext扩展了MessageSource接口，可以支持国际化的实现。
	 * b.访问资源：
	 * ApplicationContext继承了DefaultResourceLoader的子类，通过ResourceLoader和Resource的支持，ApplicationContext可以加载不同地方的Bean定义资源文件，尤其可以从不同IO途径获取Bean定义信息。可以使用户程序灵活地定义Bean定义信息。
	 * c.支持应用事件：
	 * ApplicationContext继承了ApplicationEventPublisher接口，在程序上下文中引入了事件机制，这些事件和Bean生命周期的结合为Bean的过来提供了便利。
	 */
	
	/**获取ApplicationContext的id
	 * Return the unique id of this application context.
	 * @return the unique id of the context, or <code>null</code> if none
	 */
	String getId();

	/**获取ApplicationContext的displayName
	 * Return a friendly name for this context.
	 * @return a display name for this context (never <code>null</code>)
	*/
	String getDisplayName();

	/**获取ApplicationContext第一次加载的时间戳
	 * Return the timestamp when this context was first loaded.
	 * @return the timestamp (ms) when this context was first loaded
	 */
	long getStartupDate();

	/**获取ApplicationContext容器的父容器
	 * Return the parent context, or <code>null</code> if there is no parent
	 * and this is the root of the context hierarchy.
	 * @return the parent context, or <code>null</code> if there is no parent
	 */
	ApplicationContext getParent();

	/**获取自动装配功能的BeanFactory
	 * Expose AutowireCapableBeanFactory functionality for this context.
	 * <p>This is not typically used by application code, except for the purpose
	 * of initializing bean instances that live outside the application context,
	 * applying the Spring bean lifecycle (fully or partly) to them.
	 * <p>Alternatively, the internal BeanFactory exposed by the
	 * {@link ConfigurableApplicationContext} interface offers access to the
	 * AutowireCapableBeanFactory interface too. The present method mainly
	 * serves as convenient, specific facility on the ApplicationContext
	 * interface itself.
	 * @return the AutowireCapableBeanFactory for this context
	 * @throws IllegalStateException if the context does not support
	 * the AutowireCapableBeanFactory interface or does not hold an autowire-capable
	 * bean factory yet (usually if <code>refresh()</code> has never been called)
	 * @see ConfigurableApplicationContext#refresh()
	 * @see ConfigurableApplicationContext#getBeanFactory()
	 */
	AutowireCapableBeanFactory getAutowireCapableBeanFactory() throws IllegalStateException;
	
}
