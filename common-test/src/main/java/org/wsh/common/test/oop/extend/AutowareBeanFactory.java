package org.wsh.common.test.oop.extend;

/**
 * @author wsh
 * @JDK-version: JDK1.8
 * @comments: 对此类的描述，可以引用系统设计中的描述
 * @since Date： 2016-09-12 10:53
 */
public class AutowareBeanFactory extends AtractFactory{


    @Override
    public String getBean(String name) {
        return "AutoWare";
    }
}
