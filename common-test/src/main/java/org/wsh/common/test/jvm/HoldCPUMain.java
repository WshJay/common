package org.wsh.common.test.jvm;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-02-03 15:08
 */
public class HoldCPUMain {

    public static class HoldCPUTask implements Runnable{

        @Override
        public void run() {
            while (true){
                double a = Math.random() * Math.random(); // 占用CPU
            }
        }
    }

    public static class LazyTask implements Runnable{

        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(1000);// 空闲线程
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new HoldCPUTask()).start();// 开启线程,占用CPU
        new Thread(new LazyTask()).start();// 空闲线程
        new Thread(new LazyTask()).start();// 空闲线程
        new Thread(new LazyTask()).start();// 空闲线程

    }
}
