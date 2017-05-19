package org.wsh.common.cache.queue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wsh.common.cache.queue.model.Producer;

import javax.annotation.Resource;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  生产者
 * since Date： 2017-04-10 17:46
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/common-cache.xml"})
public class ProducerTest {

    @Resource
    private TaskQueueService taskQueueService;

    @Resource
    private SNService snService;

    @Test
    public void test() {
        Producer p = new Producer("张三",taskQueueService);
        p.run();

        Producer p1 = new Producer("李四",taskQueueService);
        p1.run();
    }
}