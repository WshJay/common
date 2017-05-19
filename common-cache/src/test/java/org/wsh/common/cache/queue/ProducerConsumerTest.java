package org.wsh.common.cache.queue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wsh.common.cache.queue.model.Producer;
import org.wsh.common.cache.service.ProducerService;

import javax.annotation.Resource;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  模拟生产者消费者
 * since Date： 2017-04-10 16:54
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/common-cache.xml"})
public class ProducerConsumerTest {

    @Resource
    private ProducerService producerService;

    @Test
    public void produce(){
        producerService.doCreateProduce("张三");
    }

    @Test
    public void produceA(){
        producerService.doCreateProduce("李四");
    }
}
