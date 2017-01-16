package org.wsh.common.application;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.naming.ConfigurationException;
import javax.ws.rs.ApplicationPath;

/**
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @jdk version used:  <JDK1.8>
 * @comments: <对此类的描述，可以引用系统设计中的描述>
 * @since Date： 2016/8/10
 */
@ApplicationPath("/*")
public class RESTApplication extends ResourceConfig {


    private Logger logger = LoggerFactory.getLogger(RESTApplication.class);


    public RESTApplication() {

        //给出要扫描的包,也就是上面heloword所在的包,扫描多个包使用分号隔开
        packages("org.wsh.common.resource");
        //打开日志,便于调试
        register(LoggingFilter.class);

        /**
         * 对于链接,先执行请求过滤,有异常则执行异常过滤,最后执行回复过滤
         */
        initResource();
        System.out.println("加载RESTApplication");
    }

    private void initResource() {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
