package org.wsh.common.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  启动监听类
 * since Date： 2016-10-21 14:49
 */
public class ApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        SpringApplication app = event.getSpringApplication();
//        app.setShowBanner(false);// 不显示banner信息
        logger.info("==自定义启动监听器==");
    }
}
