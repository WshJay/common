package org.wsh.common.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;
import org.wsh.common.rest.controller.HomeController;
import org.wsh.common.rest.server.NettyServer;


public class Main {

    public static void main(String[] args)
            throws Exception {

        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:beans.xml");
        Assert.notNull(ac);
        Assert.notNull(ac.getBean(HomeController.class));

        NettyServer netty = ac.getBean(NettyServer.class);

        netty.start();
        System.out.println("Netty启动完成!");

    }
}
