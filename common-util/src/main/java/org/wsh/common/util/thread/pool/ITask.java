package org.wsh.common.util.thread.pool;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  ThreadObject的具体任务
 * since Date： 2016/11/24 13:42
 */
public interface ITask {

	/**
	 * 具体任务代码
	 * 
	 * @throws Throwable
	 */
	public void doTask() throws Throwable;

	/**
	 * doTask过程中如果抛异常的回调
	 * 
	 * @param t
	 */
	public void throwableCallback(Throwable t);

}
