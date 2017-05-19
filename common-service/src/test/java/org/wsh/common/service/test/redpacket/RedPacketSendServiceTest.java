package org.wsh.common.service.test.redpacket;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wsh.common.cache.queue.SNUtils;
import org.wsh.common.enums.redpacket.RedPacketType;
import org.wsh.common.model.redpacket.RedPacketSendDO;
import org.wsh.common.pager.pagination.Pagination;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wsh.common.service.api.redpacket.RedPacketSendService;
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
public class RedPacketSendServiceTest extends LoggerService{

    @Resource
    private RedPacketSendService redPacketSendService;

    private Long id = 1L;

    @Test
    public void add() throws Exception{
        RedPacketSendDO redPacketSendDO = new RedPacketSendDO();
        redPacketSendDO.setType(RedPacketType.REDOM);
        redPacketSendDO.setUserId("1");
        redPacketSendDO.setAccountId("1");
        String businessNumber = SNUtils.getSN();
        redPacketSendDO.setBillNumber(businessNumber);
        redPacketSendDO.setBusinessNumber(businessNumber);
        redPacketSendDO.setTotalNum(5);
        redPacketSendDO.setRemainNum(5);
        redPacketSendDO.setTotalAmount(new BigDecimal(100));
        redPacketSendDO.setRemainAmount(new BigDecimal(100));
//        redPacketSendDO.setAverageAmount(new BigDecimal(1));
        redPacketSendDO.setDescription("红包");
        ResponseDO<RedPacketSendDO> responseDO = redPacketSendService.addRedPacketSendDO(redPacketSendDO);
        Assert.isTrue(responseDO.isSuccess(),responseDO.getErrorMsg());
        id = responseDO.getData().getId();
        logger.info(responseDO.toString());
    }

    @Test
    public void selectListForPage() throws Exception{
        RedPacketSendDO redPacketSendDO = new RedPacketSendDO();
        OptionsResponseDO<List<RedPacketSendDO>> responseDO = redPacketSendService.queryRedPacketSendDOListForPage(redPacketSendDO, new Pagination());
        logger.info(responseDO.toString());
    }

    @Test
    public void selectById() throws Exception{
        ResponseDO<RedPacketSendDO> responseDO = redPacketSendService.getRedPacketSendDOById(id);
        Assert.isTrue(responseDO.isSuccess(),responseDO.getErrorMsg());
        logger.info(responseDO.toString());
    }

    @Test
    public void selectByParams() throws Exception{
        RedPacketSendDO redPacketSendDO = new RedPacketSendDO();
        ResponseDO<RedPacketSendDO> responseDO = redPacketSendService.getRedPacketSendDOByParams(redPacketSendDO);
        Assert.isTrue(responseDO.isSuccess(),responseDO.getErrorMsg());
        logger.info(responseDO.toString());
    }

    @Test
    public void updateById() throws Exception{
        ResponseDO<RedPacketSendDO> oldResponseDO = redPacketSendService.getRedPacketSendDOById(id);
        RedPacketSendDO redPacketSendDO = oldResponseDO.getData();
        ResponseDO<RedPacketSendDO> responseDO = redPacketSendService.modifyRedPacketSendDO(redPacketSendDO);
        Assert.isTrue(responseDO.isSuccess(),responseDO.getErrorMsg());
        logger.info(responseDO.toString());
    }

    @Test
    public void delete() throws Exception{
        RedPacketSendDO redPacketSendDO = new RedPacketSendDO();
        redPacketSendDO.setId(id);
        ResponseDO<RedPacketSendDO> responseDO = redPacketSendService.delRedPacketSendDO(id);
        Assert.isTrue(responseDO.isSuccess(),responseDO.getErrorMsg());
        logger.info(responseDO.toString());
    }
}