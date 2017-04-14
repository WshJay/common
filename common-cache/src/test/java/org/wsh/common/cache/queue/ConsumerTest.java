package org.wsh.common.cache.queue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wsh.common.cache.queue.enums.TaskType;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-04-10 17:52
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/common-cache.xml"})
public class ConsumerTest {

    @Resource
    private TaskQueueService taskQueueService;

    @Test
    public void test() {
        ConsumerTest c = new ConsumerTest();
        Consumer consumer = c.new Consumer("老王", taskQueueService);
        consumer.run();
    }

    /**
     * 消费者
     */
    class Consumer implements Runnable {
        private String name;

        private TaskQueueService taskService;

        public Consumer(String name, TaskQueueService taskQueueService) {
            this.name = name;
            this.taskService = taskQueueService;
        }

        public void run() {
            try {
                while (taskService.getTotalNum(TaskType.DEFAULT.name()) > 0) {
                    System.out.println(name + "准备消费产品.");
                    String consumerId = taskService.popQueue(TaskType.DEFAULT.name(),String.class);
                    System.out.println(name + "已消费(" + consumerId + ").");
                    System.out.println("===============");
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
