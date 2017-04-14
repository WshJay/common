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
 * comments:  模拟生产者消费者
 * since Date： 2017-04-10 16:54
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/common-cache.xml"})
public class ProducerConsumerTest {

    @Resource
    private TaskQueueService taskQueueService;

    @Test
    public void test(){

        ProducerConsumerTest pc = new ProducerConsumerTest();
        ExecutorService service = Executors.newCachedThreadPool();
        Producer p = pc.new Producer("张三",taskQueueService);
        Producer p2 = pc.new Producer("李四",taskQueueService);
        Consumer c = pc.new Consumer("老王",taskQueueService);
//        Consumer c2 = pc.new Consumer("老刘");
//        Consumer c3 = pc.new Consumer("老林");
//        service.submit(p);
//        service.submit(p2);
//        service.submit(c);
//        service.submit(c2);
//        service.submit(c3);

        c.run();
    }

//    public static void main(String[] args) {
//        ProducerConsumerTest pc = new ProducerConsumerTest();
//        ExecutorService service = Executors.newCachedThreadPool();
//        Producer p = pc.new Producer("张三");
//        Producer p2 = pc.new Producer("李四");
//        Consumer c = pc.new Consumer("老王");
//        Consumer c2 = pc.new Consumer("老刘");
//        Consumer c3 = pc.new Consumer("老林");
//        service.submit(p);
//        service.submit(p2);
////        service.submit(c);
////        service.submit(c2);
////        service.submit(c3);
//
//    }

    private volatile long taskId = 0;

    public synchronized long createTaskId(){
        taskId++;
        return taskId;
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
                while (true) {
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
                    Thread.sleep(500);
                }
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

        }
    }
}
