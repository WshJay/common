package org.wsh.common.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wsh.common.dao.MenuDAO;
import org.wsh.common.model.basic.MenuDO;
import org.wsh.common.support.base.AbstractLogger;

import javax.annotation.Resource;
import java.util.List;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  数据持久层接口测试
 * since Date： 2016/9/29 13:55
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:common-bean.xml")
public class DaoTest extends AbstractLogger{

    @Resource
    private MenuDAO menuDAO;

    @Test
    public void test(){
        List<MenuDO> menuDOList = menuDAO.queryAllChildrenMenu();
        for (MenuDO menuDO : menuDOList) {
            logger.info("name:" + menuDO.getName());
        }
    }
	
}

