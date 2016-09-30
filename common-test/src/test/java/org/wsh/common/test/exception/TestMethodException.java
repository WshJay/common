package org.wsh.common.test.exception;

import org.junit.Test;

/**
 * @author wsh
 * @JDK-version: JDK1.8
 * @comments: 对此类的描述，可以引用系统设计中的描述
 * @since Date： 2016-09-12 15:44
 */
public class TestMethodException {

    @Test
    public void test(){

        MethodException methodException = new MethodException();
        try {
            methodException.methodTwo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
