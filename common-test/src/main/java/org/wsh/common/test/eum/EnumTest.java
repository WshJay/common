package org.wsh.common.test.eum;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016-09-28 16:26
 */
public class EnumTest {

    //测试Person的values（）方法
    public static void testValues(){
        Person []persons = Person.values();
        for(Person p:persons){
            System.out.println(p);
        }

    }
    //测试Person的valuesof()方法
    public static void testValuesOf(){
        Person person2 = Person.valueOf("MAN");
        System.out.println(person2);
    }

}
