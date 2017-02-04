package org.wsh.common.rest.application;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.spi.Container;
import org.glassfish.jersey.server.spi.ContainerLifecycleListener;
import org.jvnet.hk2.spring.bridge.api.SpringBridge;
import org.jvnet.hk2.spring.bridge.api.SpringIntoHK2Bridge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.wsh.common.service.api.UserService;

import javax.naming.ConfigurationException;
import javax.ws.rs.ApplicationPath;

import static org.wsh.common.enums.SessionKey.user;

/**
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @jdk version used:  <JDK1.8>
 * @comments: <对此类的描述，可以引用系统设计中的描述>
 * @since Date： 2016/8/10
 */
@ApplicationPath("/*")
public class RESTApplication extends ResourceConfig {

    private Logger logger = LoggerFactory.getLogger(RESTApplication.class);

    // won't be initialized until onStartup()
    ServiceLocator serviceLocator;

    public RESTApplication() {

        //给出要扫描的包,也就是上面heloword所在的包,扫描多个包使用分号隔开
        packages("org.wsh.common.rest.resource");
        //打开日志,便于调试
        register(LoggingFilter.class);

        initResource();
        System.out.println("加载RESTApplication");
    }

    public ServiceLocator getServiceLocator()
    {
        return serviceLocator;
    }


    private void initResource() {
        try {
            new ContainerLifecycleListener(){
                public void onStartup(Container container)
                {
                    // access the ServiceLocator here
                    serviceLocator = container.getApplicationHandler().getServiceLocator();

                    // ... do what you need with ServiceLocator ...
//                    MyService service = serviceLocator.createAndInitialize(MyService.class);

                    BeanFactory beanFactory = new FileSystemXmlApplicationContext(new String[]{"classpath:beans.xml"});
//                    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:beans.xml"});
//                    context.start();
//                    System.in.read();
                    SpringBridge.getSpringBridge().initializeSpringBridge(serviceLocator);
                    doBeanFactoryToLocator(serviceLocator,beanFactory);
                    UserService service = serviceLocator.createAndInitialize(UserService.class);
                    System.out.println(service);
                }
                public void onReload(Container container) {/*...*/}
                public void onShutdown(Container container) {/*...*/}
            };


        } catch (Exception e) {
            logger.error("Jersey加载Dubbo服务失败!");
            e.printStackTrace();
        }
    }

    public void doBeanFactoryToLocator(ServiceLocator aServiceLocator, BeanFactory springFactory) {
        SpringIntoHK2Bridge springBridge = aServiceLocator.getService(SpringIntoHK2Bridge.class);
        springBridge.bridgeSpringBeanFactory(springFactory);
    }

}
