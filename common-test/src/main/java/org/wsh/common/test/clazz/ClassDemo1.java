package org.wsh.common.test.clazz;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  观察静态区块何时被运行
 * since Date： 2016-10-27 15:06
 */
public class ClassDemo1 {

    public static void main(String[] args) {

        try {
            System.out.println("载入 StaticClass ");
            // Class.forName(xxx.xx.xx)的作用是要求JVM查找并加载指定的类，JVM会执行该类的静态代码段.
            Class c = Class.forName("org.wsh.common.test.clazz.StaticClass");
            System.out.println("使用 StaticClass 声明参考名称");
            StaticClass test = null;
            System.out.println("使用 StaticClass 建立对象");
            test = new StaticClass();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("没有指定类名称");
        } catch (ClassNotFoundException e) {
            System.out.println("找不到指定的类");
        }
    }

}
