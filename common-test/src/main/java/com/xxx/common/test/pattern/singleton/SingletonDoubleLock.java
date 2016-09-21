package com.xxx.common.test.pattern.singleton;

/**
 * @author wsh
 * @JDK-version: JDK1.8
 * @comments: 双重检验锁,线程安全(Spring创建bean使用该方式)
 * @since Date： 2016-08-23 10:54
 */
public class SingletonDoubleLock {

    private static volatile SingletonDoubleLock instance;

    private SingletonDoubleLock(){

    }

    public static SingletonDoubleLock getInstance(){
        if (instance == null){ // Single checked
            synchronized(SingletonDoubleLock.class){
                if (instance == null){ // Double checked
                    instance = new SingletonDoubleLock();
                }
            }
        }
        return instance;
    }
}
