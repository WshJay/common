package org.wsh.common.test.dubbo.service.basic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wsh.common.service.api.UserService;

import javax.annotation.Resource;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-01-09 17:17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/common-dubbo-bean.xml"})
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void addUser(){
        userService.addUser("用户41","123456","用户41","12345678911","123456@qq.com");
    }
}
