package org.wsh.common.test.dubbo.service.redpacket;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.wsh.common.enums.redpacket.RedPacketType;
import org.wsh.common.model.redpacket.RedPacketReceiveDO;
import org.wsh.common.model.redpacket.RedPacketSendDO;
import org.wsh.common.service.api.redpacket.RedPacketReceiveService;
import org.wsh.common.service.api.redpacket.RedPacketSendService;
import org.wsh.common.support.response.ResponseDO;
import org.wsh.common.util.concurrent.ConcurrentUtil;
import org.wsh.common.util.concurrent.Task;
import org.wsh.common.util.logger.LoggerService;
import org.wsh.common.util.sn.SNUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.wsh.common.enums.SessionKey.user;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  红包接口测试
 * since Date： 2017-05-11 9:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/common-dubbo-bean.xml"})
public class RedPacketServiceTest extends LoggerService {

    @Resource
    private RedPacketSendService redPacketSendService;

    @Resource
    private RedPacketReceiveService redPacketReceiveService;

    @Test
    public void add() throws Exception{
        RedPacketSendDO redPacketSendDO = new RedPacketSendDO();
        redPacketSendDO.setType(RedPacketType.REDOM);
        redPacketSendDO.setUserId("1");
        redPacketSendDO.setAccountId("1");
        String businessNumber = SNUtils.getSN();
        redPacketSendDO.setBillNumber(businessNumber);
        redPacketSendDO.setBusinessNumber(businessNumber);
        redPacketSendDO.setTotalNum(1000);
        redPacketSendDO.setRemainNum(1000);
        redPacketSendDO.setTotalAmount(new BigDecimal(1000));
        redPacketSendDO.setRemainAmount(new BigDecimal(1000));
//        redPacketSendDO.setAverageAmount(new BigDecimal(1));
        redPacketSendDO.setDescription("红包");
        ResponseDO<RedPacketSendDO> responseDO = redPacketSendService.addRedPacketSendDO(redPacketSendDO);
        Assert.isTrue(responseDO.isSuccess(),responseDO.getErrorMsg());
        logger.info(responseDO.toString());
    }

    @Test
    public void doReceiveRedPacket() throws Exception{
        long t = System.currentTimeMillis();
        List<Object[]> list = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            RedPacketReceiveDO redPacketReceiveDO = new RedPacketReceiveDO();
            redPacketReceiveDO.setUserId(i + "");
            redPacketReceiveDO.setAccountId(i + "");
            redPacketReceiveDO.setSendId(16L);
            String businessNumber = SNUtils.getSN();
            redPacketReceiveDO.setBillNumber(businessNumber);
            redPacketReceiveDO.setBusinessNumber(businessNumber);
            redPacketReceiveDO.setDescription("红包领取");
            ResponseDO<RedPacketReceiveDO> responseDO = redPacketReceiveService.doReceiveRedPacket(redPacketReceiveDO);
            Assert.isTrue(responseDO.isSuccess(),responseDO.getErrorMsg());
            logger.info(responseDO.toString());
            Object[] obj = new Object[1];
            obj[0] = redPacketReceiveDO;
            list.add(obj);
        }

//        ConcurrentUtil.start(redPacketReceiveService,"doReceiveRedPacket",list,1000);
        System.out.println(System.currentTimeMillis() - t);
    }

    @Test
    public void doReceiveRedPacket1() throws Exception{
        long t = System.currentTimeMillis();
        List<Object[]> list = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            RedPacketReceiveDO redPacketReceiveDO = new RedPacketReceiveDO();
            redPacketReceiveDO.setUserId(i + "");
            redPacketReceiveDO.setAccountId(i + "");
            redPacketReceiveDO.setSendId(17L);
            String businessNumber = SNUtils.getSN();
            redPacketReceiveDO.setBillNumber(businessNumber);
            redPacketReceiveDO.setBusinessNumber(businessNumber);
            redPacketReceiveDO.setDescription("红包领取");
            ResponseDO<RedPacketReceiveDO> responseDO = redPacketReceiveService.doReceiveRedPacket(redPacketReceiveDO);
            Assert.isTrue(responseDO.isSuccess(),responseDO.getErrorMsg());
            logger.info(responseDO.toString());
            Object[] obj = new Object[1];
            obj[0] = redPacketReceiveDO;
            list.add(obj);
        }

//        ConcurrentUtil.start(redPacketReceiveService,"doReceiveRedPacket",list,1000);
        System.out.println(System.currentTimeMillis() - t);
    }

}
