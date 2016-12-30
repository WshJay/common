package org.wsh.common.util.concurrent;

import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  并发测试工具
 * since Date： 2016-10-10 11:57
 */
public class ConcurrentUtil {

    /**
     * 并发处理同一操作
     * @param task 任务
     * @param count 执行次数
     */
    @Deprecated
    public static void start(Task task, int count) {
        long startTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(count);
        for (int i = 0; i < count; i++) {
            executorService.execute(task);
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {// 所有线程未执行完进入此循环
        }
        System.out.println("耗时=>" + (System.currentTimeMillis() - startTime) + "毫秒");

    }

    /**
     * 并发处理同一任务(参数相同)
     * 注意:总任务数量应该是并发数量的倍数，否则任务无法全部执行完成;例如 concuNum = 3则总任务数为3,6,9...
     * @param obj 执行对象(接口/类)
     * @param methodName 执行方法
     * @param args 参数数组(方法所需参数)
     * @param concuNum 并发数量(每次执行线程数量)
     * @param taskNum 总任务数量
     */
    public static void start(Object obj, String methodName, Object[] args, int concuNum, int taskNum) {
        long startTime = System.currentTimeMillis();
        CyclicBarrier barrier = new CyclicBarrier(concuNum);
        ExecutorService executorService = Executors.newFixedThreadPool(taskNum);
        for (int i = 0; i < taskNum; i++) {
            executorService.execute(new Task(barrier,obj, methodName, args));
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {// 所有线程未执行完进入此循环
        }
        System.out.println("耗时=>" + (System.currentTimeMillis() - startTime) + "毫秒");

    }

    /**
     * 并发处理多个参数不同的任务,例如执行同一方法不同参数
     * 注意:总任务数量 = argsList.size(), 总任务数量应该是并发数量的倍数，否则任务无法全部执行完成;例如 concuNum = 3则总任务数为3,6,9...
     * @param obj 执行对象(接口/类)
     * @param methodName 执行方法
     * @param argsList 参数数组列表(方法所需参数)
     * @param concuNum 并发数量(每次执行线程数量)
     */
    public static void start(Object obj, String methodName, List<Object[]> argsList, int concuNum) {
        long startTime = System.currentTimeMillis();
        CyclicBarrier barrier = new CyclicBarrier(concuNum);
        ExecutorService executorService = Executors.newFixedThreadPool(argsList.size());
        for (int i = 0; i < argsList.size(); i++) {
            executorService.execute(new Task(barrier,obj, methodName, argsList.get(i)));
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {// 所有线程未执行完进入此循环
        }
        System.out.println("耗时=>" + (System.currentTimeMillis() - startTime) + "毫秒");

    }
}
