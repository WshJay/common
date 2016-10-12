package common.xxx.consumer;

import org.wsh.common.consumer.service.DemoAService;
import org.wsh.common.consumer.service.DemoBService;
import org.wsh.common.consumer.service.DemoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wsh.common.service.api.MenuService;
import org.wsh.common.util.concurrent.ConcurrentUtil;
import org.wsh.common.util.concurrent.Task;

import javax.annotation.Resource;

/**
 * @author wsh
 * @JDK-version: JDK1.8
 * @comments: 对此类的描述，可以引用系统设计中的描述
 * @since Date： 2016-09-09 15:23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/application-consumer.xml"})
public class ConsumerTest {

    @Resource
    private DemoService demoService;

    @Resource
    private DemoAService demoAService;

    @Resource
    private DemoBService demoBService;

    @Resource
    private MenuService menuService;

    @Test
    public void test(){
        System.out.println("--------Test Start-----------");
        String hello = demoService.sayHello();
        System.out.println(hello);

        demoAService.aMethod();
        demoBService.bMethod();

        int count = 100;
        ConcurrentUtil.start(new Task(count,menuService,"getAllChildrenMenu"),count);
        System.out.println("--------Test End-----------");
    }
}
