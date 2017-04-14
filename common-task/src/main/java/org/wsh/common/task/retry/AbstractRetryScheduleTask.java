package org.wsh.common.task.retry;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  重试调度器
 * since Date： 2017-04-14 15:07
 */
public abstract class AbstractRetryScheduleTask<T> extends AbstractScheduleTask<T> implements RetryScheduleTask<T> {

    /** 重试调度模式，供外部添加调用 */
    public static final int RETRY_MODE_30S = 1;
    public static final int RETRY_MODE_5MIN = 2;
    public static final int RETRY_MODE_30MIN = 3;

    /** 重试任务队列 */
    protected final BlockingQueue<T> retryDataQueue30S;
    protected final BlockingQueue<T> retryDataQueue5Min;
    protected final BlockingQueue<T> retryDataQueue30Min;

    /** 重试调度任务 */
    protected final ScheduledExecutorService retryScheduledExecutorService;
    /** 重试定时任务数 */
    private int retryScheduleSize = 3;
    /** 重试定时任务开始执行时间 */
    private int retryScheduleInitialDelay = 0;
    /** 重试任务间隔时间 */
    private int retryScheduleDelay30S = 1000; // 30 * 1000;
    private int retryScheduleDelay5Min = 5000; // 5 * 60 * 1000;
    private int retryScheduleDelay30Min = 10000; // 30 * 60 * 1000;
    /** 队列大小 */
    private int retryQueueSize = 2000;
    /** 线程批处理大小; */
    private int retryDataSize = 100;

    public AbstractRetryScheduleTask() {
        super();
        // LinkedBlockingQueue is Thread safety.
        retryDataQueue30S = new LinkedBlockingQueue<T>(retryQueueSize);
        retryDataQueue5Min = new LinkedBlockingQueue<T>(retryQueueSize);
        retryDataQueue30Min = new LinkedBlockingQueue<T>(retryQueueSize);
        retryScheduledExecutorService = Executors.newScheduledThreadPool(retryScheduleSize);
        schedule();
    }

    private void schedule() {
        // init 30s retry scheduler.
        retryScheduledExecutorService.scheduleWithFixedDelay(new RetryScheduleHandler(retryDataQueue30S),
                retryScheduleInitialDelay, retryScheduleDelay30S, TimeUnit.MILLISECONDS);
        // init 5min retry scheduler.
        retryScheduledExecutorService.scheduleWithFixedDelay(new RetryScheduleHandler(retryDataQueue5Min),
                retryScheduleInitialDelay, retryScheduleDelay5Min, TimeUnit.MILLISECONDS);
        // init 30min retry scheduler.
        retryScheduledExecutorService.scheduleWithFixedDelay(new RetryScheduleHandler(retryDataQueue30Min),
                retryScheduleInitialDelay, retryScheduleDelay30Min, TimeUnit.MILLISECONDS);
    }

    /**
     * 具体业务重试数据处理
     *
     * @param data
     * @return
     */
    protected abstract Integer doRetryData(final List<T> data);

    private final class RetryScheduleHandler implements Runnable {
        /**
         * 定时任务实现
         *
         * @see java.lang.Runnable#run()
         */
        private BlockingQueue<T> dataQueue;

        public RetryScheduleHandler(BlockingQueue<T> retryQueue) {
            dataQueue = retryQueue;
        }

        public void run() {
            List<T> dataList = new ArrayList<T>(retryDataSize);
            while (!dataQueue.isEmpty()) {
                if (dataList.size() >= retryDataSize)
                    break;
                try {
                    T data = dataQueue.take();
                    dataList.add(data);
                } catch (InterruptedException e) {
                    // LogUtil.error(logger, e, "出队列执行异常！");
                }
            }

            Callable<Integer> task = getRetryTask(dataList);
            if (task == null)
                return;
            Future<Integer> future = executorService.submit(task);
            try {
                int result = future.get();
                // LogUtil.info(logger, "任务执行结果：" + result);
            } catch (InterruptedException e) {
                // LogUtil.error(logger, e, "任务执行异常！");
            } catch (ExecutionException e) {
                // LogUtil.error(logger, e, "任务执行异常！");
            }
        }
    }

    /**
     * 创建任务
     *
     * @param data
     * @return
     */
    private Callable<Integer> getRetryTask(final List<T> data) {
        Callable<Integer> task = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                if (data == null) {
                    return 0;
                }
                try {
                    int result = doRetryData(data);
                    return result;
                } catch (Throwable e) {
                    // LogUtil.error(logger, e, "执行任务异常");
                }
                return 0;
            }
        };
        return task;
    }

    /**
     * 添加数据到队列
     *
     * @see com.alipay.csCheck.biz.bench.schedule.ScheduleTask#addData(java.lang.Object)
     */
    @Override
    public void addRetryData(T parameterObject, int retryMode) {
        switch (retryMode) {
            case RETRY_MODE_30S:
                addRetryDateForQueue(parameterObject, retryDataQueue30S);
                break;
            case RETRY_MODE_5MIN:
                addRetryDateForQueue(parameterObject, retryDataQueue5Min);
                break;
            case RETRY_MODE_30MIN:
                addRetryDateForQueue(parameterObject, retryDataQueue30Min);
                break;
            default: // never reach here.
                break;
        }
    }

    /**
     * 按照对应模式添加重试数据到对应队列
     *
     * @param parameterObject
     * @param retryDataQueue
     */
    private void addRetryDateForQueue(T parameterObject, final BlockingQueue<T> retryDataQueue) {
        if (parameterObject != null) {
            if (retryDataQueue.size() >= this.getQueueSize()) {
                // LogUtil.warn(logger, "消费队列数据过大！！！！");

            }
            try {
                retryDataQueue.put(parameterObject);
            } catch (InterruptedException e) {
                // LogUtil.warn(logger, "添加队列数据异常！！！！", e.getMessage());
            }
        }
    }
}