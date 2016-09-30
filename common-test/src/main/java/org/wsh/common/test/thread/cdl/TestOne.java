package org.wsh.common.test.thread.cdl;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;

public class TestOne {
	
	private final int N = 10;

	@Test
	public void test() {
		try {
			task();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	void task() throws InterruptedException {
		CountDownLatch doneSignal = new CountDownLatch(10); // 线程计数信号
		CountDownLatch startSignal = new CountDownLatch(1); // 开始执行信号

		for (int i = 1; i <= N; i++) {
			new Thread(new Worker(i, doneSignal, startSignal, "Thread" + i)).start();// 线程启动了
		}
		System.out.println("begin------------");
		startSignal.countDown();// 开始执行啦 ，startSignal每调用一次countDown，计数都减1，直到0
		doneSignal.await();// 等待所有的线程执行完毕，即doneSignal计数变为0
		System.out.println("Ok");
	}
	
	class Worker implements Runnable {
		private final CountDownLatch doneSignal;
		private final CountDownLatch startSignal;
		private int beginIndex;
		private String name;

		Worker(int beginIndex, CountDownLatch doneSignal,
				CountDownLatch startSignal, String name) {
			this.startSignal = startSignal;
			this.beginIndex = beginIndex;
			this.doneSignal = doneSignal;
			this.name = name;
		}

		public void run() {
			try {
				startSignal.await(); // 等待开始执行信号的发布 ,在startSignal计数等于0的时候开始执行
				beginIndex = (beginIndex - 1) * 10 + 1;
				for (int i = beginIndex; i < beginIndex + 10; i++) {
					System.out.println(this.name + "::" + i);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				doneSignal.countDown(); // doneSignal计数减1
			}
		}
	}

}



