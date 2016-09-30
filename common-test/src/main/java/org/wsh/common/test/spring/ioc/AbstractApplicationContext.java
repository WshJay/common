package org.wsh.common.test.spring.ioc;

import org.springframework.beans.BeansException;

public abstract class AbstractApplicationContext {

	public void refresh() throws BeansException, IllegalStateException {  
		
	/*	synchronized (this.startupShutdownMonitor) {  
            //调用容器准备刷新的方法，获取容器的当时时间，同时给容器设置同步标识  
            prepareRefresh();  
            //告诉子类启动refreshBeanFactory()方法，Bean定义资源文件的载入从  
            //子类的refreshBeanFactory()方法启动  
            ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();  
            //为BeanFactory配置容器特性，例如类加载器、事件处理器等  
            prepareBeanFactory(beanFactory);  
            try {  
                //为容器的某些子类指定特殊的BeanPost事件处理器  
                postProcessBeanFactory(beanFactory);  
                //调用所有注册的BeanFactoryPostProcessor的Bean  
                invokeBeanFactoryPostProcessors(beanFactory);  
                //为BeanFactory注册BeanPost事件处理器.  
                //BeanPostProcessor是Bean后置处理器，用于监听容器触发的事件  
                registerBeanPostProcessors(beanFactory);  
                //初始化信息源，和国际化相关.  
                initMessageSource();  
                //初始化容器事件传播器.  
                initApplicationEventMulticaster();  
                //调用子类的某些特殊Bean初始化方法  
                onRefresh();  
                //为事件传播器注册事件监听器.  
                registerListeners();  
                //初始化所有剩余的单态Bean.  
                finishBeanFactoryInitialization(beanFactory);  
                //初始化容器的生命周期事件处理器，并发布容器的生命周期事件  
                finishRefresh();  
            }  
            catch (BeansException ex) {  
                //销毁以创建的单态Bean  
                destroyBeans();  
                //取消refresh操作，重置容器的同步标识.  
                cancelRefresh(ex);  
                throw ex;  
            }  
        } */ 
    }  
}

