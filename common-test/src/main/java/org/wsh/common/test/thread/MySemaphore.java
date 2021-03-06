package org.wsh.common.test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 计数信号量
 * File Name: <MySemaphore.java>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used: <JDK1.6> 
 * @author wsh[wsh.ck@qq.com]
 * @since Date： 2015-5-4 上午10:33:19
 */
public class MySemaphore extends Thread {
	Semaphore position;
	private int id;

	public MySemaphore(int i, Semaphore s) {
		this.id = i;
		this.position = s;
	}

	public void run() {
		try {
			if (position.availablePermits() > 0) {
				System.out.println("顾客[" + this.id + "]进入厕所，有空位");
			} else {
				System.out.println("顾客[" + this.id + "]进入厕所，没空位，排队");
			}
			position.acquire();
			System.out.println("顾客[" + this.id + "]获得坑位");
			Thread.sleep((int) (Math.random() * 1000));
			System.out.println("顾客[" + this.id + "]使用完毕");
			position.release();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		ExecutorService list = Executors.newCachedThreadPool();
		Semaphore position = new Semaphore(1);
		for (int i = 0; i < 10; i++) {
			list.submit(new MySemaphore(i + 1, position));
		}
		list.shutdown();
		position.acquireUninterruptibly(1);
		System.out.println("使用完毕，需要清扫了");
		position.release(2);
	}
}
