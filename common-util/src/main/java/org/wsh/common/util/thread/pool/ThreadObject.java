package org.wsh.common.util.thread.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wsh.common.util.logger.LoggerService;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  线程池中的线程对象,线程启动后就会进入等待，等待外界给他task并激活,不管task执行成功或失败，ThreadObject都将被pool回收
 * since Date： 2016/11/24 13:45
 */
public class ThreadObject extends Thread {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	protected boolean being = false;

	protected ITask task;

	protected Callback callback;

	@Override
	public void run() {

		while (being) {
			try {
				// 如果没有任务就会一直等下去
				if (task == null) {
					synchronized (this) {
						this.wait();
					}
				}

				// 醒来如果任务不为空则时开始执行任务
				if (task != null) {
					try {
						task.doTask();
					} catch (Throwable e) {
						log.warn(e.getMessage(), e);
						task.throwableCallback(e);
					}
				}
				// 不管任务执行成功或失败都将任务制空，继续等待下次任务
				task = null;
			} catch (InterruptedException e) {
				log.warn(e.getMessage(), e);
			} catch (Exception e) {
				log.warn(e.getMessage(), e);
			} finally {
				if (callback != null) {
					// afterTask回调中会把线程回收掉
					callback.afterTask();
				}
			}
		}
	}

	@Override
	public synchronized void start() {
		this.being = true;
		super.start();
	}

	public synchronized void kill() {
		this.being = false;
		try {
			this.notify();
		} catch (Exception e) {
			log.warn(e.getMessage(), e);
		}
	}

	public boolean isBeing() {
		return being;
	}

	public ITask getTask() {
		return task;
	}

	public void setTask(ITask task) {
		this.task = task;
	}

	public void setCallback(Callback callback) {
		this.callback = callback;
	}

	/**
	 * 线程对象的各种回调
	 */
	public static class Callback {

		/**
		 * 任务跑完或抛异常的回调
		 */
		public void afterTask() {
		}
	}
}
