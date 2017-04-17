package org.wsh.common.cache.queue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wsh.common.cache.queue.enums.TaskType;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

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

    @Test
    public void test() {
        Producer p = new Producer("张三",taskQueueService);
        p.run();
    }

    @Test
    public void testA() {

        Producer p = new Producer("李四",taskQueueService);
        p.run();
    }


    private volatile long taskId = 0;

    public synchronized long createTaskId() {
        taskId++;
        return taskId;
    }

    /**
     * 生产者
     */
    class Producer implements Runnable {
        private String name;

        private TaskQueueService taskService;

        public Producer(String name, TaskQueueService taskQueueService) {
            this.name = name;
            this.taskService = taskQueueService;
        }

        public void run() {
            try {
                while (true) {
                    long producerId = createTaskId();
                    System.out.println(name + "准备生产(" + producerId + ").");
                    taskService.addQueue(TaskType.DEFAULT.name(),String.class,String.valueOf(producerId));
                    System.out.println(name + "已生产(" + producerId + ").");
                    System.out.println("===============");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

        }
    }
}