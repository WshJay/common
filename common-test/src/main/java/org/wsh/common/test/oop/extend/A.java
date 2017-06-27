package org.wsh.common.test.oop.extend;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  定义一个实体类
 * since Date： 2017/6/27 21:31
 */
public class A {

    private String id;

    public A(String id) {
        System.out.println(String.format("A-id=>[%s]", id));
        System.out.println("父类构造方法!");
    }
}
