package com.xxx.common.test.pattern.singleton;

/**
 * @author wsh
 * @JDK-version: JDK1.8
 * @comments: 单例模式(懒汉式,线程不安全)
 * @since Date： 2016-08-22 18:57
 */
public class Singleton {

    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
