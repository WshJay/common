package com.xxx.common.test.dubbo;

import com.xxx.consumer.service.DemoService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author wsh
 * @JDK-version: JDK1.8
 * @comments: 对此类的描述，可以引用系统设计中的描述
 * @since Date： 2016-09-10 15:30
 */
public class ConsumerTest {

    @Resource
    private DemoService demoService;

    @Test
    public void test(){
        String hello = demoService.sayHello();
        System.out.println(hello);
    }
}
