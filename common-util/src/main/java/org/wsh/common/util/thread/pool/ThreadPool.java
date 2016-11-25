package org.wsh.common.util.thread.pool;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  线程池对象<兼容commons-pool 1.5.4的写法>
 * since Date： 2016/11/24 13:47
 */
public class ThreadPool extends GenericObjectPool {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    public ThreadPool(ThreadPoolableFactory factory, PoolConfig config) {
        super(factory, config);
    }

    /**
     * 取线程池中最大活动线程数
     */
    public int maxActive() {
        return this.getMaxActive();
    }

    /**
     * 当前活动线程数
     */
    public int crtActive() {
        return this.getNumActive();
    }

    /**
     * 最大空闲线程数
     */
    public int maxIdle() {
        return this.getMaxIdle();
    }

    /**
     * 当前空闲线程数
     */
    public int crtIdle() {
        return this.getNumIdle();
    }

    // 记录线程都跑哪些任务了
    static Map<String, Integer> threadListenerMap = new HashMap<String, Integer>();

    /**
     * 打印线程当前使用情况
     */
    public void display() {
        log.error("==================线程池当前使用情况==================");
        for (String key : threadListenerMap.keySet()) {
            log.error(key + ":" + threadListenerMap.get(key));
        }
    }

    /**
     * 记录线程执行
     */
    static void recordThreadStart(Class<?> clazz) {
        String key = clazz.getName();
        if (threadListenerMap.containsKey(key)) {
            Integer c = threadListenerMap.get(key);
            threadListenerMap.put(key, ++c);
        } else {
            threadListenerMap.put(key, 1);
        }
    }

    /**
     * 记录线程执行完成
     */
    static void recordThreadEnd(Class<?> clazz) {
        String key = clazz.getName();
        if (threadListenerMap.containsKey(key)) {
            Integer c = threadListenerMap.get(key);
            threadListenerMap.put(key, --c);
        } else {
            threadListenerMap.put(key, 0);
        }
    }

    /**
     * 用线程池执行任务
     *
     * @param task 任务代码
     * @throws NoSuchElementException 无法借出线程时抛出的异常。可能当时池子里已没有空闲对象
     * @throws Exception
     */
    public void doTask(ITask task) throws NoSuchElementException, Exception {

        // 如果ITask是空的话干脆就不跑了
        if (task == null) {
            return;
        }

        try {
            final ThreadObject thread = (ThreadObject) this.borrowObject();
            final Class<?> taskClass = task.getClass();

            // 记录线程执行
            recordThreadStart(taskClass);

            if (thread != null) {
                // 线程回调
                thread.setCallback(new ThreadObject.Callback() {
                    // 线程跑完的回调，让池子回收线程对象。回收失败直接杀掉
                    @SuppressWarnings("unchecked")
                    public void afterTask() {
                        try {
                            ThreadPool.this.returnObject(thread);

                            // 记录线程完成
                            recordThreadEnd(taskClass);

                        } catch (Exception e) {
                            log.warn("还回线程时抛出异常. 线程将被销毁", e);
                            thread.kill();
                        }
                    }
                });
                thread.setTask(task);
                synchronized (thread) {
                    thread.notify();
                }
            }
            // 如果借出对象为空，说明资源比较紧张
            else {
                throw new NoSuchElementException("当前无法借出线程，资源紧张.");
            }
        } catch (NoSuchElementException e) {
            log.warn(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            throw e;
        }

    }

}
