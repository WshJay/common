package org.wsh.common.task.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wsh.common.task.QuartzManagerService;
import org.wsh.common.util.date.DateUtil;
import org.wsh.common.util.logger.LoggerService;

import javax.annotation.Resource;
import java.util.Date;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016-11-02 9:56
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/common-task.xml"})
public class TaskServiceTest extends LoggerService{

    @Resource
    private QuartzManagerService quartzManagerService;

    @Test
    public void test() throws InterruptedException {

        long currentTime = System.currentTimeMillis();
        logger.info(DateUtil.parseDate(new Date(currentTime),DateUtil.YYYY_MM_DD_HH_MM_SS));
        quartzManagerService.addJob("JOB_ONE","GROUP_ONE","TIGGER_ONE","TIGGER_GROUP_ONE",OneJob.class,new Date(currentTime + 5 * 1000));
        quartzManagerService.modifyJobTime("TIGGER_ONE","TIGGER_GROUP_ONE",new Date(currentTime + 10 * 1000));
        quartzManagerService.removeJob("JOB_ONE","GROUP_ONE","TIGGER_ONE","TIGGER_GROUP_ONE");
        Thread.sleep(20000);

    }
}
