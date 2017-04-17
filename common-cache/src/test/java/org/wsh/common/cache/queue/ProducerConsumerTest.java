package org.wsh.common.cache.queue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    private TaskQueueService taskQueueService;

    @Test
    public void produce(){
        Producer p = new Producer("张三",taskQueueService);
        p.run();
    }

    @Test
    public void produceA(){
        Producer p = new Producer("李四",taskQueueService);
        p.run();
    }
}
