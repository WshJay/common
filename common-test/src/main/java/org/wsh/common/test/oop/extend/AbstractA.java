package org.wsh.common.test.oop.extend;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  定义一个抽象类继承实体类
 * since Date： 2017/6/27 21:34
 */
public abstract class AbstractA extends A{

    public AbstractA(String id, String name) {
        super(id);
        System.out.println(String.format("AbstractA-id=>[%s]name=>[%s]", id, name));
        System.out.println("抽象类A的构造方法!");
    }

    public void init(){
        System.out.println("初始化方法!");
    }
}
