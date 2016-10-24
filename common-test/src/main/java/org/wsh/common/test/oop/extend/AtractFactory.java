package org.wsh.common.test.oop.extend;

import org.wsh.common.test.oop.impl.BeanFactory;

/**
 * @author wsh
 * @JDK-version: JDK1.8
 * @comments: 对此类的描述，可以引用系统设计中的描述
 * @since Date： 2016-09-12 11:26
 */
public abstract class AtractFactory implements BeanFactory {

    /**
     * 定义一个抽象方法
     */
    public abstract void atractMethod();

    /**
     * 抽象类中的方法
     */
    public void methodA(){
        System.out.println("执行抽象类中的方法A...");
    }
}
