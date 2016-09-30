package org.wsh.common.test.pattern.singleton;

/**
 * @author wsh
 * @JDK-version: JDK1.8
 * @comments: 单例模式,懒汉式使用静态内部类实现(推荐)
 * @since Date： 2016-08-23 11:15
 */
public class SingletonNested {

    private static class SingletonHolder{
        private static final SingletonNested INSTANCE = new SingletonNested();
    }

    public static SingletonNested getInstance(){
        return SingletonHolder.INSTANCE;
    }
}
