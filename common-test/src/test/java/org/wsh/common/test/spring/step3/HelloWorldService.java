package org.wsh.common.test.spring.step3;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016/10/27 17:10
 */
public class HelloWorldService {

    private String text;

    public void helloWorld(){
        System.out.println(text);
    }

    public void setText(String text) {
        this.text = text;
    }
}
