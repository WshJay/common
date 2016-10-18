import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.wsh.common.service.api.UserService;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016-10-17 16:26
 */
public class ContextServiceTest {

    public static void main(String[] args) {
        //创建IoC容器管理的Bean的xml配置文件，即定位资源
        ClassPathResource resource = new ClassPathResource("common-bean.xml");
        //创建BeanFactory
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory ();
        //创键Bean定义读取器
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        //使用Bean定义读取器读入Bean配置信息，即载入配置
        int beanNum = reader.loadBeanDefinitions(resource);
        UserService userService = (UserService)factory.getBean("userService");
        System.out.println(userService);
        String[] beanArray = factory.getBeanDefinitionNames();
        for (String s : beanArray) {
            System.out.println(s);
        }
        System.out.println(String.valueOf(beanNum));
    }
}
