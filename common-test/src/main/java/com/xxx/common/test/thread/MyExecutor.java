package com.xxx.common.test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executor Test
 * File Name: <MyExecutor.java>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used: <JDK1.6> 
 * @author wsh[wsh.ck@qq.com]
 * @since Date： 2015-5-4 上午10:06:32
 */
public class MyExecutor extends Thread {
	private int index;

	public MyExecutor(int i) {
		this.index = i;
	}

	public void run() {
		try {
			System.out.println("[" + this.index + "] start....");
			Thread.sleep((int) (10 * 1000));
			System.out.println("[" + this.index + "] end.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		// 固定大小的线程池
//		ExecutorService service = Executors.newFixedThreadPool(4);
		// 无界线程池，可以进行自动线程回收
//		ExecutorService service = Executors.newCachedThreadPool();
		// 单个后台线程
		ExecutorService service = Executors.newSingleThreadExecutor();
		
		for (int i = 0; i < 10; i++) {
			service.execute(new MyExecutor(i));
			// service.submit(new MyExecutor(i));
		}
		System.out.println("submit finish");
		service.shutdown();
	}
}
