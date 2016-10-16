import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wsh.common.consumer.service.DemoAService;
import org.wsh.common.consumer.service.DemoBService;
import org.wsh.common.consumer.service.DemoService;
import org.wsh.common.model.basic.UserBasicDO;
import org.wsh.common.rest.security.ShiroFilerChainManager;
import org.wsh.common.service.api.MenuService;
import org.wsh.common.service.api.UserService;
import org.wsh.common.util.concurrent.ConcurrentUtil;
import org.wsh.common.util.concurrent.Task;

import javax.annotation.Resource;
import java.util.List;

import static com.sun.corba.se.impl.util.Version.PROJECT_NAME;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016-10-12 15:06
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:common-bean.xml"})
public class ServiceTest {

    @Autowired
    private DemoService demoService;

    @Resource
    private DemoAService demoAService;

    @Resource
    private DemoBService demoBService;

    @Resource
    private MenuService menuService;

    @Resource
    private UserService userService;

    @Resource
    private ShiroFilerChainManager shiroFilerChainManager;

    @Value("#{system.serverHost}")
    private String SERVER_HOST;

    @Test
    public void test(){
//        System.out.println("--------Test Start-----------");
//        String hello = demoService.sayHello();
//        System.out.println(hello);
//
//        demoAService.aMethod();
//        demoBService.bMethod();
//
//        List<UserBasicDO> userBasicDOList = userService.getAllUserBasicList();
//        for (UserBasicDO userBasicDO : userBasicDOList) {
//            System.out.println(userBasicDO.getEmail());
//        }
        System.out.println(shiroFilerChainManager);
        System.out.println(SERVER_HOST);


//        int count = 100;
//        ConcurrentUtil.start(new Task(count,menuService,"getAllChildrenMenu"),count);
//        System.out.println("--------Test End-----------");
    }
}
