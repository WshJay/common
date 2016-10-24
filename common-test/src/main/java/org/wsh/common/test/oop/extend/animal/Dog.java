package org.wsh.common.test.oop.extend.animal;

import lombok.Getter;
import lombok.Setter;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  狗
 * since Date： 2016-10-24 10:37
 */
public class Dog extends Animal{

    /**
     * 有爪子
     */
    @Getter
    @Setter
    private String paws;
}
