package org.wsh.common.test.dubbo.service.jedis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wsh.common.model.jedis.OrderDO;
import org.wsh.common.service.api.jedis.JedisService;
import org.wsh.common.support.response.ResponseDO;
import org.wsh.common.util.concurrent.ConcurrentUtil;
import org.wsh.common.util.concurrent.Task;
import org.wsh.common.util.logger.LoggerService;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

import static org.wsh.common.enums.SessionKey.user;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-02-17 11:28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/common-dubbo-bean.xml"})
public class JedisServiceTest extends LoggerService{

    @Resource
    private JedisService jedisService;

    /**
     * Redis普通SET测试
     * 无法处理并发
     */
    @Test
    public void normalSet(){
        try {
            int count = 6;
            List<Object[]> params = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                Object[] obj = new Object[1];
                OrderDO orderDO = new OrderDO(1L,new BigDecimal(i + 1));
                obj[0] = orderDO;
                params.add(obj);
            }
            ConcurrentUtil.start(jedisService,"normalSet",params, count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Redis事务SET测试
     */
    @Test
    public void transaction(){
        try {
            int count = 6;
            List<Object[]> params = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                Object[] obj = new Object[1];
                OrderDO orderDO = new OrderDO(2L,new BigDecimal(i + 1));
                obj[0] = orderDO;
                params.add(obj);
            }
            ConcurrentUtil.start(jedisService,"transaction",params, count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

