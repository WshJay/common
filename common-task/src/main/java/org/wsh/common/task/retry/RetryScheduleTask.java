package org.wsh.common.task.retry;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  任务定时执行管理器 包含重试任务
 * since Date： 2017-04-14 15:09
 */
public interface RetryScheduleTask<T> extends ScheduleTask<T> {

    /**
     * 添加重试数据
     *
     * @param parameterObject
     */
    public void addRetryData(T parameterObject, int retryMode);

}
