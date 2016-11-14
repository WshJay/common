package org.wsh.common.server;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  服务启动主入口
 * since Date： 2016-11-14 16:19
 */
public class MainService {

    private static Logger log = LoggerFactory.getLogger(MainService.class);

    private ClassPathXmlApplicationContext context = null;

    public void start(String configLocation) {
        synchronized (this) {
            if (context == null) {
                context = new ClassPathXmlApplicationContext("classpath:" + configLocation);
                context.start();
            }
        }
    }

    public static void main(String[] args) {
        try {
            String configLocation = null;
            if (args != null && args.length > 0) {
                configLocation = args[0];
            } else if (StringUtils.isBlank(configLocation)) {
                configLocation = "common-server.xml";
            }
            new MainService().start(configLocation);
            log.info("++++++[dubbo-common服务已启动完成]++++++" + "You can view the log details");
            while (true) {
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
