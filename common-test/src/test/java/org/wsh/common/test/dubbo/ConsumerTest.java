package org.wsh.common.test.dubbo;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wsh.common.consumer.service.DemoService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author wsh
 * @JDK-version: JDK1.8
 * @comments: 对此类的描述，可以引用系统设计中的描述
 * @since Date： 2016-09-10 15:30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/application-consumer.xml"})
public class ConsumerTest {

    @Resource
    private DemoService demoService;

    @Test
    public void test(){
        String hello = demoService.sayHello();
        System.out.println(hello);
    }
}
