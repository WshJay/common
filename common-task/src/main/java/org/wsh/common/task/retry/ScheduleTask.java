package org.wsh.common.task.retry;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  任务定时执行管理器
 * since Date： 2017-04-14 15:04
 */
public interface ScheduleTask<T> {

    /**
     * 添加任务
     * @param parameterObject T
     */
    public void addData(final T parameterObject);

}
