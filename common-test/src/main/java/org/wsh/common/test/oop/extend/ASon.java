package org.wsh.common.test.oop.extend;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  A的子类继承A的抽象方法
 * since Date： 2017/6/27 21:39
 */
public class ASon extends AbstractA{

    public ASon(String id, String name) {
        super(id, name);
    }

    public static void main(String[] args) {
        ASon son = new ASon("1", "王王王");
    }
}
