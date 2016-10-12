package org.wsh.common.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wsh.common.model.basic.UserBasicDO;
import org.wsh.common.service.api.MenuService;
import org.wsh.common.service.api.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016-10-12 15:06
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:common-bean.xml"})
public class ServiceTest {


    @Resource
    private MenuService menuService;

    @Resource
    private UserService userService;

    @Value("#{config.app.server.host}")
    private String SERVER_HOST;

    @Value("#{config.project.name}")
    private String PROJECT_NAME;

    @Test
    public void test(){
        System.out.println("--------Test Start-----------");

//        List<UserBasicDO> userBasicDOList = userService.getAllUserBasicList();
//        for (UserBasicDO userBasicDO : userBasicDOList) {
//            System.out.println(userBasicDO.getEmail());
//        }

        System.out.println(PROJECT_NAME);
        System.out.println(SERVER_HOST);


//        int count = 100;
//        ConcurrentUtil.start(new Task(count,menuService,"getAllChildrenMenu"),count);
//        System.out.println("--------Test End-----------");
    }
}
