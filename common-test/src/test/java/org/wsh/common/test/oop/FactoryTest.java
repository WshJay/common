package org.wsh.common.test.oop;

import org.wsh.common.test.oop.extend.AutowareBeanFactory;
import org.wsh.common.test.oop.impl.BeanFactory;
import org.junit.Test;

/**
 * @author wsh
 * @JDK-version: JDK1.8
 * @comments: 对此类的描述，可以引用系统设计中的描述
 * @since Date： 2016-09-12 10:55
 */
public class FactoryTest {

    @Test
    public void test(){
        BeanFactory factory = new AutowareBeanFactory();
        System.out.println(factory.getBean("123"));
        AutowareBeanFactory autowareBeanFactory = new AutowareBeanFactory();
        autowareBeanFactory.atractMethod();
    }
}
