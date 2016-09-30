package org.wsh.common.test.pattern.singleton;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wsh
 * @JDK-version: JDK1.8
 * @comments: 单例模式测试
 * @since Date： 2016-08-22 18:59
 */
public class SingletonTest {

    ExecutorService service = Executors.newCachedThreadPool();

    @Test
    public void test(){
        long currentTimeMillis = System.currentTimeMillis();
        for (int i = 1; i < 1000; i++){
            SingletonThread t = new SingletonThread();
            service.submit(t);
        }
        System.out.println(System.currentTimeMillis() - currentTimeMillis);
    }

}
