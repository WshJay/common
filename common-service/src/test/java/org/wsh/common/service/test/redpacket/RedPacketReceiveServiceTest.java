package org.wsh.common.service.test.redpacket;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wsh.common.cache.queue.SNUtils;
import org.wsh.common.model.redpacket.RedPacketReceiveDO;
import org.wsh.common.pager.pagination.Pagination;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wsh.common.service.api.redpacket.RedPacketReceiveService;
import org.wsh.common.support.beans.OptionsResponseDO;
import org.wsh.common.support.response.ResponseDO;
import org.wsh.common.util.logger.LoggerService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  服务接口单元测试
* since Date： 2017-05-10 11:04:46
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:common-bean.xml")
public class RedPacketReceiveServiceTest extends LoggerService{

    @Resource
    private RedPacketReceiveService redPacketReceiveService;

    private Long id = 1L;

    @Test
    public void add() throws Exception{
        RedPacketReceiveDO redPacketReceiveDO = new RedPacketReceiveDO();
        redPacketReceiveDO.setUserId("2");
        redPacketReceiveDO.setAccountId("2");
        redPacketReceiveDO.setSendId(1L);
        String businessNumber = SNUtils.getSN();
        redPacketReceiveDO.setBillNumber(businessNumber);
        redPacketReceiveDO.setBusinessNumber(businessNumber);
        redPacketReceiveDO.setAmount(new BigDecimal(0));
        redPacketReceiveDO.setDescription("红包领取");
        ResponseDO<RedPacketReceiveDO> responseDO = redPacketReceiveService.addRedPacketReceiveDO(redPacketReceiveDO);
        Assert.isTrue(responseDO.isSuccess(),responseDO.getErrorMsg());
        id = responseDO.getData().getId();
        logger.info(responseDO.toString());
    }

    @Test
    public void selectListForPage() throws Exception{
        RedPacketReceiveDO redPacketReceiveDO = new RedPacketReceiveDO();
        OptionsResponseDO<List<RedPacketReceiveDO>> responseDO = redPacketReceiveService.queryRedPacketReceiveDOListForPage(redPacketReceiveDO, new Pagination());
        logger.info(responseDO.toString());
    }

    @Test
    public void selectById() throws Exception{
        ResponseDO<RedPacketReceiveDO> responseDO = redPacketReceiveService.getRedPacketReceiveDOById(id);
        Assert.isTrue(responseDO.isSuccess(),responseDO.getErrorMsg());
        logger.info(responseDO.toString());
    }

    @Test
    public void selectByParams() throws Exception{
        RedPacketReceiveDO redPacketReceiveDO = new RedPacketReceiveDO();
        ResponseDO<RedPacketReceiveDO> responseDO = redPacketReceiveService.getRedPacketReceiveDOByParams(redPacketReceiveDO);
        Assert.isTrue(responseDO.isSuccess(),responseDO.getErrorMsg());
        logger.info(responseDO.toString());
    }

    @Test
    public void updateById() throws Exception{
        ResponseDO<RedPacketReceiveDO> oldResponseDO = redPacketReceiveService.getRedPacketReceiveDOById(id);
        RedPacketReceiveDO redPacketReceiveDO = oldResponseDO.getData();
        ResponseDO<RedPacketReceiveDO> responseDO = redPacketReceiveService.modifyRedPacketReceiveDO(redPacketReceiveDO);
        Assert.isTrue(responseDO.isSuccess(),responseDO.getErrorMsg());
        logger.info(responseDO.toString());
    }

    @Test
    public void delete() throws Exception{
        RedPacketReceiveDO redPacketReceiveDO = new RedPacketReceiveDO();
        redPacketReceiveDO.setId(id);
        ResponseDO<RedPacketReceiveDO> responseDO = redPacketReceiveService.delRedPacketReceiveDO(id);
        Assert.isTrue(responseDO.isSuccess(),responseDO.getErrorMsg());
        logger.info(responseDO.toString());
    }

    @Test
    public void doReceiveRedPacket() throws Exception{
        long t = System.currentTimeMillis();
        for (int i = 1; i <= 5; i++) {
            RedPacketReceiveDO redPacketReceiveDO = new RedPacketReceiveDO();
            redPacketReceiveDO.setUserId(i + "");
            redPacketReceiveDO.setAccountId(i + "");
            redPacketReceiveDO.setSendId(3L);
            String businessNumber = SNUtils.getSN();
            redPacketReceiveDO.setBillNumber(businessNumber);
            redPacketReceiveDO.setBusinessNumber(businessNumber);
            redPacketReceiveDO.setDescription("红包领取");
            ResponseDO<RedPacketReceiveDO> responseDO = redPacketReceiveService.doReceiveRedPacket(redPacketReceiveDO);
            Assert.isTrue(responseDO.isSuccess(),responseDO.getErrorMsg());
            id = responseDO.getData().getId();
            logger.info(responseDO.toString());
        }
        System.out.println(System.currentTimeMillis() - t);
    }
}