package org.wsh.common.cache.message;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wsh.common.cache.message.listener.RedisMessageListener;
import org.wsh.common.cache.queue.task.OrderTask;
import org.wsh.common.cache.service.RedisService;

import javax.annotation.Resource;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-04-11 17:25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/common-cache.xml"})
public class RedisMessageListenerTest {

    @Resource
    private RedisMessageListenerContainer redisContainer;

    @Resource
    private RedisService redisService;

    @Resource
    private JdkSerializationRedisSerializer jdkSerializer;

    @Test
    public void test() throws InterruptedException {
        while (true) {
            if (redisContainer.isRunning()) {
                System.out.println("RedisMessageListenerContainer is running..");
            }
            Thread.sleep(5000);
        }
    }

    @Test
    public void publish() throws InterruptedException {

        for (int i = 1; i <= 10; i++) {
            OrderTask order = new OrderTask("" + i);
            redisService.getJedis().publish("order".getBytes(),jdkSerializer.serialize(order));

//            redisService.getJedis().publish("credit","" + i);
            Thread.sleep(1000);
        }

    }

}
