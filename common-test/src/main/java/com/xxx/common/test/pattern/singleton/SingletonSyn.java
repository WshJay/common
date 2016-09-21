package com.xxx.common.test.pattern.singleton;

/**
 * @author wsh
 * @JDK-version: JDK1.8
 * @comments: 单例模式(懒汉式,线程安全)
 * @since Date： 2016-08-23 10:41
 */
public class SingletonSyn {

    private static SingletonSyn instance;

    private SingletonSyn(){};

    public static synchronized SingletonSyn getInstance(){
        if (instance == null){
            instance = new SingletonSyn();
        }
        return instance;
    }
}
