package org.wsh.common.test.oop.extend.animal;

import org.junit.Test;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016-10-24 10:41
 */
public class AnimalTest {

    @Test
    public void test(){
        Animal animal = new Dog();
        animal.setName("DOG");
        System.out.println(animal.getName());
    }
}

