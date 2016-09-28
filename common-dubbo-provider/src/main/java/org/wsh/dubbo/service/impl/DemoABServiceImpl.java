package org.wsh.dubbo.service.impl;

import com.xxx.consumer.service.DemoAService;
import com.xxx.consumer.service.DemoBService;
import com.xxx.consumer.service.DemoService;
import org.springframework.stereotype.Service;
import org.wsh.dubbo.service.base.BaseService;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016-09-23 10:25
 */
@Service("demoAService")
public class DemoABServiceImpl extends BaseService implements DemoAService, DemoBService {

    @Override
    public void aMethod() {
        System.out.println("A Method...");
    }

    @Override
    public void bMethod() {
        System.out.println("B Method...");
    }
}
