package com.xxx.common.test.pattern.singleton;

/**
 * @author wsh
 * @JDK-version: JDK1.8
 * @comments: 对此类的描述，可以引用系统设计中的描述
 * @since Date： 2016-08-23 11:54
 */
public class SingletonThread implements Runnable{

    @Override
    public void run() {
        System.out.println("Singleton:" + Singleton.getInstance());
//        System.out.println("SingletonSyn:" + SingletonSyn.getInstance());
//        System.out.println("SingletonDoubleLock:" + SingletonDoubleLock.getInstance());
//        System.out.println("SingletonFinal:" + SingletonFinal.getInstance());
//        System.out.println("SingletonNested:" + SingletonNested.getInstance());
//        System.out.println("EasySingleton:" + EasySingleton.INSTANCE);
    }
}

