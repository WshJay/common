package org.wsh.common.test.clazz;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  使用Class的静态forName()方法实现动态加载类
 * since Date： 2016-10-27 15:00
 */
public class ClassDemo {

    public static void main(String[] args) {
        try {
            Class c = Class.forName("org.wsh.common.test.clazz.ClassDemo");
            System.out.println("类名称：" + c.getName());
            System.out.println("是否为接口：" + c.isInterface());
            System.out.println("是否为基本类型：" + c.isPrimitive());
            System.out.println("是否为数组：" + c.isArray());
            System.out.println("父类：" + c.getSuperclass().getName());

            ClassDemo classDemo = (ClassDemo)Class.forName("org.wsh.common.test.clazz.ClassDemo").newInstance();
            ClassDemo classDemo1 = new ClassDemo();
            System.out.println(classDemo);
            System.out.println(classDemo1);
            System.out.println((ClassDemo)c.newInstance());

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("没有指定类名称");
        } catch (ClassNotFoundException e) {
            System.out.println("找不到指定的类");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
