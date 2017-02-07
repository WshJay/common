package org.wsh.common.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wsh.common.model.system.SystemDO;
import org.wsh.common.service.api.SystemService;
import org.wsh.common.service.api.flow.FlowService;
import org.wsh.common.support.response.ResponseDO;

import javax.annotation.Resource;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-02-07 14:06
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:common-bean.xml")
public class SystemServiceTest {

    @Resource
    private SystemService systemService;

    @Test
    public void getSystemDO(){
        ResponseDO<SystemDO> responseDO = systemService.getSystemDO();
        System.out.println(responseDO.toJson());

    }
}
