package org.wsh.common.test.thread.cdl;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.Test;

public class CountDownLatchTest {

	private ExecutorService executorService = Executors.newCachedThreadPool();

	CountDownLatch TASK_COUNT = new CountDownLatch(10);

	@Test
	public void test() {
		try {
			task();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	class TestThread implements Runnable {

		private int taskId;

		public TestThread(int taskId) {
			this.taskId = taskId;
		}

		public void run() {
			System.out.println("taskId" + taskId + " Start Run ...");
			branchTask(taskId);
			System.out.println("taskId" + taskId + " End run ...");
			// 任务数减一
			TASK_COUNT.countDown();
		}

	}

	/**
	 * 分支任务
	 */
	public void branchTask(int taskId) {
		System.out.println("taskId" + taskId + " BranchTask Run...");
	}

	/**
	 * 主任务
	 */
	public void mainTask() {
		System.out.println("MainTask Run...");
	}

	public void task() throws InterruptedException {
		System.out.println("Task Start...");
		for (int i = 0; i < 10; i++) {
			executorService.submit(new TestThread(i));
		}
		TASK_COUNT.await();
		mainTask();
	}

}
