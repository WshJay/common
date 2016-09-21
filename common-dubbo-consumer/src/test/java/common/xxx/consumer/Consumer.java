package common.xxx.consumer;

import com.xxx.consumer.service.DemoService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * @author wsh
 * @JDK-version: JDK1.8
 * @comments: 对此类的描述，可以引用系统设计中的描述
 * @since Date： 2016-09-10 14:42
 */
public class Consumer {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:/application-consumer.xml"});
        context.start();
        DemoService demoService = (DemoService) context.getBean("demoService"); //
        String hello = demoService.sayHello(); // ִ
        System.out.println(hello); //
    }
}
