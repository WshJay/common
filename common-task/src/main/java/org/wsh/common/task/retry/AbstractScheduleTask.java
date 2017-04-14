package org.wsh.common.task.retry;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  异步任务定时执行管理
 * since Date： 2017-04-14 15:06
 */
public abstract class AbstractScheduleTask<T> implements ScheduleTask<T> {

    /** 任务队列 */
    protected final BlockingQueue<T> dataQueue;
    /** 线程池 */
    protected final ExecutorService executorService;
    /** 调度任务 */
    protected final ScheduledExecutorService scheduledExecutorService;
    /** 定时任务数 */
    private int scheduleSize = 1;
    /** 定时任务开始执行时间 */
    private int scheduleInitialDelay = 0;
    /** 定时任务间隔时间,正常单条数据的插入时间<3ms,队列的长度为1000,1000m秒的时间足够,避免队列的数据堆积 */
    private int scheduleDelay = 1000;
    /** 线程池大小 */
    private int threadPoolSize = 8;
    /** 队列大小 */
    private int queueSize = 2000;
    /** 线程批处理大小; */
    private int dataSize = 100;

    /** 默认构造方法，加载定时任务 */
    public AbstractScheduleTask() {
        dataQueue = new LinkedBlockingQueue<T>(queueSize);
        executorService = Executors.newFixedThreadPool(threadPoolSize);
        scheduledExecutorService = Executors.newScheduledThreadPool(scheduleSize);
        schedule();
    }

    /**
     * 具体业务数据处理
     *
     * @param data
     * @return
     */
    protected abstract Integer doData(final List<T> data);

    /**
     * 添加数据到队列
     */
    @Override
    public void addData(T parameterObject) {
        if (parameterObject != null) {
            if (dataQueue.size() >= this.getQueueSize()) {
                // 消费队列数据过大
            }
            try {
                dataQueue.put(parameterObject);
            } catch (InterruptedException e) {
                // 添加队列数据异常
            }
        }
    }

    /**
     * 设置定时任务 设定执行线程计划,初始10s延迟,每次任务完成后延迟10s再执行一次任务
     */
    private void schedule() {
        for (int i = 0; i < scheduleSize; i++) {
            scheduledExecutorService.scheduleWithFixedDelay(new ScheduleHandler(), scheduleInitialDelay, scheduleDelay,
                    TimeUnit.MILLISECONDS);
        }

    }

    /**
     * 创建任务
     *
     * @param data
     * @return
     */
    private Callable<Integer> getTask(final List<T> data) {
        Callable<Integer> task = new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                if (data == null) {
                    return 0;
                }
                try {
                    int result = doData(data);
                    // 任务执行完成
                    return result;
                } catch (Throwable e) {
                    // 执行任务出现异常
                }
                return 0;
            }
        };

        return task;
    }

    /**
     * 定时任务
     */
    private final class ScheduleHandler implements Runnable {
        /**
         * 定时任务实现
         *
         * @see java.lang.Runnable#run()
         */
        public void run() {
            List<T> dataList = new ArrayList<T>(dataSize);
            while (!dataQueue.isEmpty()) {
                if (dataList.size() == dataSize) {
                    break;
                }
                try {
                    T data = dataQueue.take();
                    dataList.add(data);
                } catch (InterruptedException e) {
                    // 出队列执行异常
                }
            }
            Callable<Integer> task = getTask(dataList);
            if (task == null) {
                return;
            }
            Future<Integer> future = executorService.submit(task);
            try {
                int result = future.get();
                // 任务执行结果: result
            } catch (InterruptedException e) {
                // 任务执行异常
            } catch (ExecutionException e) {
                // 任务执行异常
            }
        }

    }

    /**
     * Getter method for property <tt>scheduleSize</tt>.
     *
     * @return property value of scheduleSize
     */
    public int getScheduleSize() {
        return scheduleSize;
    }

    /**
     * Getter method for property <tt>scheduleInitialDelay</tt>.
     *
     * @return property value of scheduleInitialDelay
     */
    public int getScheduleInitialDelay() {
        return scheduleInitialDelay;
    }

    /**
     * Getter method for property <tt>scheduleDelay</tt>.
     *
     * @return property value of scheduleDelay
     */
    public int getScheduleDelay() {
        return scheduleDelay;
    }

    /**
     * Getter method for property <tt>threadPoolSize</tt>.
     *
     * @return property value of threadPoolSize
     */
    public int getThreadPoolSize() {
        return threadPoolSize;
    }

    /**
     * Getter method for property <tt>queueSize</tt>.
     *
     * @return property value of queueSize
     */
    public int getQueueSize() {
        return queueSize;
    }

    /**
     * Getter method for property <tt>dataSize</tt>.
     *
     * @return property value of dataSize
     */
    public int getDataSize() {
        return dataSize;
    }
}
