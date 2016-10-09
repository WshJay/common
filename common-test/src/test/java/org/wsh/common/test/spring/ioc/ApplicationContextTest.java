package org.wsh.common.test.spring.ioc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wsh.common.model.basic.MenuDO;
import org.wsh.common.service.api.MenuService;
import org.wsh.common.util.date.DateUtil;
import org.wsh.common.util.logger.LoggerService;

import java.util.Date;
import java.util.List;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016-10-08 11:43
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml","classpath:/common-bean.xml"})
public class ApplicationContextTest extends LoggerService{

    @Autowired
    private org.springframework.context.ApplicationContext context;

    @Autowired
    private org.springframework.beans.factory.BeanFactory beanFactory;

    @Test
    public void test(){
        MenuService menuService = (MenuService)beanFactory.getBean("menuService");
        List<MenuDO> menuDoList = menuService.getAllRootMenu();
        logger.info("栏目数量:" + menuDoList.size());

        // 获取ApplicationContext的id
        String contextId = context.getId();
        logger.info("contextId: " + contextId);

        logger.info("contextName: " + context.getDisplayName());

        //获取ApplicationContext第一次加载的时间戳
        logger.info("startupDate: " + context.getStartupDate());
        logger.info("startupDate: " + DateUtil.parseDate(new Date(context.getStartupDate()), DateUtil.YYYY_MM_DD_HH_MM_SS));
    }
}
