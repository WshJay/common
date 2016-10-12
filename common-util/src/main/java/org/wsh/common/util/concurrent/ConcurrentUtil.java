package org.wsh.common.util.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  并发测试工具
 * since Date： 2016-10-10 11:57
 */
public class ConcurrentUtil {

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
}
