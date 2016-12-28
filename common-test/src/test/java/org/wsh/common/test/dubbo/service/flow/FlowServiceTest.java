package org.wsh.common.test.dubbo.service.flow;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wsh.common.model.flow.FlowDO;
import org.wsh.common.service.api.flow.FlowService;
import org.wsh.common.support.exception.BusinessException;
import org.wsh.common.support.response.ResponseDO;
import org.wsh.common.util.concurrent.ConcurrentUtil;
import org.wsh.common.util.concurrent.Task;
import org.wsh.common.util.logger.LoggerService;

import javax.annotation.Resource;

import static org.wsh.common.enums.SessionKey.user;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016-12-28 15:19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/application-consumer.xml"})
public class FlowServiceTest extends LoggerService{

    @Resource
    private FlowService flowService;

    private Long id = 1L;

    @Test
    public void selectById() {
        try {
            ResponseDO<FlowDO> responseDO = flowService.getFlowDOById(id);
            if (responseDO.isSuccess()) {
                logger.info(responseDO.getData().toString());
            }
        } catch (Exception e) {
            logger.error("根据ID查询表数据异常!", e);
        }
    }

    @Test
    public void updateById(){
        try {
            ResponseDO<FlowDO> oldResponseDO = flowService.getFlowDOById(id);
            FlowDO flowDO = oldResponseDO.getData();
            ResponseDO<FlowDO> responseDO = flowService.modifyFlowDO(flowDO);
            logger.info("Result:" + responseDO.isSuccess());
        } catch (Exception e) {
            logger.error("根据ID更新表数据异常!", e);
        }
    }

    /**
     * 并发乐观锁失败后重试
     */
    @Test
    public void concurrentTest() {
        try {
            FlowDO flowDO = new FlowDO();
            flowDO.setId(id);
            int count = 2;
            ConcurrentUtil.start(new Task(count,flowService,"modifyFlowDO",flowDO), count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
