package com.xxx.common.provider;

import org.wsh.common.consumer.service.DemoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author wsh
 * @JDK-version: JDK1.8
 * @comments: 对此类的描述，可以引用系统设计中的描述
 * @since Date： 2016-09-10 15:03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/application-provider.xml"})
public class ProviderServiceTest {

    @Resource
    private DemoService demoService;

    @Test
    public void test(){
        demoService.sayHello();
    }

}
