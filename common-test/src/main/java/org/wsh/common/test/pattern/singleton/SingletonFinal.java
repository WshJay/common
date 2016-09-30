package org.wsh.common.test.pattern.singleton;

/**
 * @author wsh
 * @JDK-version: JDK1.8
 * @comments: 饿汉式,final static(Spring AOP pointCut使用)
 * @since Date： 2016-08-23 11:11
 */
public class SingletonFinal {

    // 类加载时就初始化
    private static final SingletonFinal instance = new SingletonFinal();

    private SingletonFinal() {
    }

    public static SingletonFinal getInstance() {
        return instance;
    }
}
