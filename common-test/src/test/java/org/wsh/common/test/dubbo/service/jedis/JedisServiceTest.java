package org.wsh.common.test.dubbo.service.jedis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wsh.common.model.jedis.OrderDO;
import org.wsh.common.service.api.jedis.JedisService;
import org.wsh.common.util.concurrent.ConcurrentUtil;
import org.wsh.common.util.logger.LoggerService;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
     */
    @Test
    public void normalSet() throws Exception{
        int count = 2;
        for (int i = 0; i < count; i++) {
            OrderDO orderDO = new OrderDO(6L,new BigDecimal(3));
            jedisService.normalSet(orderDO);
        }
    }

    /**
     * Redis普通SET并发测试
     */
    @Test
    public void normalSetConCurrent() throws Exception{
        int count = 6;
        List<Object[]> params = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Object[] obj = new Object[1];
            OrderDO orderDO = new OrderDO(1L,new BigDecimal(1));
            obj[0] = orderDO;
            params.add(obj);
        }
        ConcurrentUtil.start(jedisService,"normalSet",params, count);
    }

    /**
     * Redis事务SET测试
     */
    @Test
    public void transaction() throws Exception{
        int count = 1;
        for (int i = 0; i < count; i++) {
            OrderDO orderDO = new OrderDO(2L,new BigDecimal(1));
            jedisService.transaction(orderDO);
        }
    }

    /**
     * Redis事务并发测试
     */
    @Test
    public void transactionConCurrentnt() throws Exception{
        int count = 6;
        List<Object[]> params = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Object[] obj = new Object[1];
            OrderDO orderDO = new OrderDO(2L,new BigDecimal(1));
            obj[0] = orderDO;
            params.add(obj);
        }
        ConcurrentUtil.start(jedisService,"transaction",params, count);
    }

    /**
     * Redis管道测试
     */
    @Test
    public void pipelined() throws Exception{
        List<OrderDO> orderDOs = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            OrderDO orderDO = new OrderDO((long)i,new BigDecimal(i));
            orderDOs.add(orderDO);
        }
        jedisService.pipelined(orderDOs);
    }
    /**
     * 管道中调用事务测试
     */
    @Test
    public void pipelineAndTransaction() throws Exception{
        List<OrderDO> orderDOs = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            OrderDO orderDO = new OrderDO((long)i,new BigDecimal(i));
            orderDOs.add(orderDO);
        }
        jedisService.pipelineAndTransaction(orderDOs);
    }
}

