package org.wsh.common.test.spring.bean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wsh.common.service.api.UserService;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  获取Spring和Dubbo中的bean
 * since Date： 2016-10-18 11:05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/common-bean.xml"})
public class SpringBeanTest {

    @Autowired
    private UserService userService;

    @Test
    public void test(){
        System.out.println(userService);
    }
}


