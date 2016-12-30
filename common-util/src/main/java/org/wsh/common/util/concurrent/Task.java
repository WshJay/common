package org.wsh.common.util.concurrent;

import java.lang.reflect.Method;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  并发任务
 * since Date： 2016-10-10 13:42
 */
public class Task implements Runnable {

    private CyclicBarrier cyclicBarrier;

    private Object obj;

    private String methodName;

    private Object[] args;

    @Deprecated
    public Task(int count, Object obj, String methodName, Object... args) {
        this.cyclicBarrier = new CyclicBarrier(count);
        this.obj = obj;
        this.methodName = methodName;
        this.args = args;
    }

    public Task(CyclicBarrier cyclicBarrier, Object obj, String methodName, Object... args) {
        this.cyclicBarrier = cyclicBarrier;
        this.obj = obj;
        this.methodName = methodName;
        this.args = args;
    }

    @Override
    public void run() {
        try {
            // 等待所有任务准备就绪
            Thread.sleep(1000 * (new Random()).nextInt(8));// 线程等待几秒,主要用于查看执行过程
            cyclicBarrier.await();
            System.out.println("任务开始执行...");
            invokeMethod(obj, methodName, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 反射调用方法
     * @param owner
     * @param methodName
     * @param args
     * @return
     * @throws Exception
     */
    private Object invokeMethod(Object owner, String methodName, Object... args) throws Exception {
        Class ownerClass = owner.getClass();
        Class[] argsClass = new Class[args.length];
        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }
        Method method = ownerClass.getMethod(methodName,argsClass);
        return method.invoke(owner, args);
    }
}
