package org.wsh.common.test.dubbo.service.mysql.lock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wsh.common.model.basic.UserBasicDO;
import org.wsh.common.service.api.mysql.lock.OptimisticLockService;
import org.wsh.common.util.concurrent.ConcurrentUtil;
import org.wsh.common.util.concurrent.Task;

import javax.annotation.Resource;

/**
 * mysql乐观锁测试
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-01-09 10:47
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/common-dubbo-bean.xml"})
public class OptimisticLockServiceTest {

    @Resource
    private OptimisticLockService optimisticLockService;

    /**
     * 乐观锁
     */
    @Test
    public void optimisticLockUpdate() {
        try {
            UserBasicDO user = new UserBasicDO();
            user.setId(6L);
            user.setFaceUrl("321");
            int count = 1;
            ConcurrentUtil.start(new Task(count,optimisticLockService,"updateForLock",user), count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
