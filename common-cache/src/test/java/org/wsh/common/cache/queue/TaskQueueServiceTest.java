package org.wsh.common.cache.queue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wsh.common.cache.queue.enums.TaskType;
import org.wsh.common.cache.queue.task.OrderTask;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-04-10 10:40
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/common-cache.xml"})
public class TaskQueueServiceTest {

    @Resource
    private TaskQueueService taskQueueService;

    @Test
    public void addQueue(){

        for (int i = 1; i <= 10; i++) {
            taskQueueService.addQueue(TaskType.DEFAULT.name(), String.class, String.valueOf(i));
        }

        for (int i = 11; i <= 20; i++) {
            OrderTask order = new OrderTask(String.valueOf(i));
            taskQueueService.addQueue(TaskType.ORDER.name(), OrderTask.class, order);
        }
    }

    @Test
    public void getQueueList(){
        List<String> stringList = taskQueueService.getQueueList(TaskType.DEFAULT.name(),String.class);
        for (String s : stringList) {
            System.out.println(s);
        }
        List<OrderTask> orderTaskList = taskQueueService.getQueueList(TaskType.ORDER.name(),OrderTask.class);
        for (OrderTask orderTask : orderTaskList) {
            System.out.println(orderTask.toString());
        }
    }

    @Test
    public void popQueue(){

        for (int i = 1; i <= 5; i++) {
            System.out.println(taskQueueService.popQueue(TaskType.DEFAULT.name(),String.class));
        }

        for (int i = 11; i <= 15; i++) {
            System.out.println(taskQueueService.popQueue(TaskType.ORDER.name(), OrderTask.class));
        }
    }

    @Test
    public void test(){

    }
}
