package org.wsh.common.cache.queue;

import org.wsh.common.cache.queue.enums.TaskType;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  消费者
 * since Date： 2017-04-17 19:51
 */
public class Consumer implements Runnable {

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
