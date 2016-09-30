package org.wsh.common.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wsh.common.service.api.MenuService;
import org.wsh.common.model.basic.MenuDO;
import org.wsh.common.support.base.AbstractLogger;

import javax.annotation.Resource;
import java.util.List;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016-09-29 17:30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:common-bean.xml")
public class ServiceTest extends AbstractLogger{

    @Resource
    private MenuService menuService;

    @Test
    public void test(){
        List<MenuDO> menuDOList = menuService.getAllRootMenu();
        for (MenuDO menuDO : menuDOList) {
            logger.info("menuName==>" + menuDO.getName());
        }
    }

}
