package org.wsh.common.test.zookeeper.concurrent;

import org.wsh.common.test.zookeeper.concurrent.ConcurrentTest.ConcurrentTask;

/**
 * Zookeeper测试
 * Project:     <common-test>
 * File Name:   <ZkTest.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2016年5月5日 下午8:03:41
 */
public class ZkTest {
	
	public static void main(String[] args) {
		Runnable task1 = new Runnable() {
			public void run() {
				DistributedLock lock = null;
				try {
					lock = new DistributedLock("121.40.111.195:2181", "test1");
					// lock = new DistributedLock("127.0.0.1:2182","test2");
					lock.lock();
					Thread.sleep(3000);
					System.out.println("===Thread "
							+ Thread.currentThread().getId() + " running");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (lock != null)
						lock.unlock();
				}
			}
		};
		new Thread(task1).start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		ConcurrentTask[] tasks = new ConcurrentTask[10];
		for (int i = 0; i < tasks.length; i++) {
			ConcurrentTask task3 = new ConcurrentTask() {
				public void run() {
					DistributedLock lock = null;
					try {
						lock = new DistributedLock("120.26.83.35:2181", "test2");
						lock.lock();
						System.out.println("Thread "
								+ Thread.currentThread().getId() + " running");
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						lock.unlock();
					}

				}
			};
			tasks[i] = task3;
		}
		new ConcurrentTest(tasks);
	}
}
