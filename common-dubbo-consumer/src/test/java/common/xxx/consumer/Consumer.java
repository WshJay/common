package common.xxx.consumer;

import org.wsh.common.consumer.service.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author wsh
 * @JDK-version: JDK1.8
 * @comments: 对此类的描述，可以引用系统设计中的描述
 * @since Date： 2016-09-10 14:42
 */
public class Consumer {

    public static void main(String[] args) {

        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:beans.xml"});
            context.start();
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        DemoService demoService = (DemoService) context.getBean("demoService"); //
//        String hello = demoService.sayHello(); // ִ
//        System.out.println(hello); //
    }
}
