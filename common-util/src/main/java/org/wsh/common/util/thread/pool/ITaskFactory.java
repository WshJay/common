package org.wsh.common.util.thread.pool;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  任务的创建工厂
 * since Date： 2016/11/24 13:43
 */
public interface ITaskFactory {

	public ITask makeTask();

}
