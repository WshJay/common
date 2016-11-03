package org.wsh.common.task.test;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.wsh.common.util.date.DateUtil;
import org.wsh.common.util.logger.LoggerService;

import java.util.Date;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016-11-02 10:12
 */
public class OneJob extends LoggerService implements Job{

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info(DateUtil.parseDate(new Date(),DateUtil.YYYY_MM_DD_HH_MM_SS));
        System.out.println("Start Job...");
    }
}
