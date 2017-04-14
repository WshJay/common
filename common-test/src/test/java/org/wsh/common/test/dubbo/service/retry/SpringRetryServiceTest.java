package org.wsh.common.test.dubbo.service.retry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wsh.common.service.api.retry.SpringRetryService;

import javax.annotation.Resource;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  Spring重试机制
 * since Date： 2017-04-14 17:17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/common-dubbo-bean.xml"})
public class SpringRetryServiceTest {

    @Resource
    private SpringRetryService springRetryService;

    @Test
    public void test() throws Exception {
        springRetryService.addUser();
    }
}
