package org.wsh.common.cache.queue.model;

import org.wsh.common.cache.queue.SNService;
import org.wsh.common.cache.queue.SNUtils;
import org.wsh.common.cache.queue.TaskQueueService;
import org.wsh.common.cache.queue.enums.TaskType;
import org.wsh.common.cache.redis.JedisReadUtils;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  生产者
 * since Date： 2017-04-17 19:50
 */
public class Producer implements Runnable {

    private String name;

    private TaskQueueService taskService;

    public Producer() {
    }

    public Producer(String name, TaskQueueService taskQueueService) {
        this.name = name;
        this.taskService = taskQueueService;
    }

    public void run() {
        try {
            while (true) {
                long producerId = SNUtils.getTradeNO();
                System.out.println(name + "准备生产(" + producerId + ").");
//                taskService.addQueue(TaskType.DEFAULT.name(),String.class,String.valueOf(producerId));
                System.out.println(name + "已生产(" + producerId + ").");
                System.out.println("===============");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

    }
}