package org.wsh.common.test.clazz;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  使用第二个版本的 forName() 方法时，设定 initialize 为 false，所以加载类时并不会立即运行静态区块，而会在使用类建立对象时才运行静态区块，第二个版本的 forName() 方法会需要一个类加载器，范例中所使用的是主线程的类加载器.
 * since Date： 2016-10-27 15:11
 */
public class ClassDemo2 {

    public static void main(String[] args) {
        try {
            System.out.println("载入 StaticClass ");
            Class c = Class.forName("org.wsh.common.test.clazz.StaticClass", false, Thread.currentThread().getContextClassLoader());

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
