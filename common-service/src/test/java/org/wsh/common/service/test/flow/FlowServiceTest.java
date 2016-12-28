package org.wsh.common.service.test.flow;

import org.springframework.transaction.annotation.Transactional;
import org.wsh.common.model.flow.FlowDO;
import org.wsh.common.pager.pagination.Pagination;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wsh.common.service.api.flow.FlowService;
import org.wsh.common.support.beans.OptionsResponseDO;
import org.wsh.common.support.response.ResponseDO;
import org.wsh.common.util.logger.LoggerService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;
import java.util.List;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  服务接口单元测试
* since Date： 2016-12-28 10:12:23
*/
//@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:common-bean.xml")
public class FlowServiceTest extends LoggerService{

    @Resource
    private FlowService flowService;

    private Long id = 1L;

    @Test
    public void add() {
        try {
            FlowDO flowDO = insert();
            id = flowDO.getId();
            logger.info(flowDO.toString());
        } catch (Exception e) {
            logger.error("添加数据异常!", e);
        }
    }

    private FlowDO insert() throws Exception {
        FlowDO flowDO = new FlowDO("REFUND","退款","退款...",2);
        ResponseDO<FlowDO> responseDO = flowService.addFlowDO(flowDO);
        logger.info("Result:" + responseDO.isSuccess() + "ErrorCode:" + responseDO.getErrorCode() + "ErrorMsg:" + responseDO.getErrorMsg());
        return flowDO;
    }

    @Test
    public void selectListForPage() {
        try {
            FlowDO flowDO = new FlowDO();
            OptionsResponseDO<List<FlowDO>> responseDO = flowService.queryFlowDOListForPage(flowDO, new Pagination());
            if (responseDO.isSuccess()) {
                for (FlowDO flowDO1 : responseDO.getData()) {
                    logger.info(flowDO1.toString());
                }
            } else {
                logger.info("分页查询列表为空!");
            }
        } catch (Exception e) {
            logger.error("分页查询列表数据异常!", e);
        }
    }

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
    public void updateById() {
        try {
            ResponseDO<FlowDO> oldResponseDO = flowService.getFlowDOById(id);
            FlowDO flowDO = oldResponseDO.getData();
            ResponseDO<FlowDO> responseDO = flowService.modifyFlowDO(flowDO);
            logger.info("Result:" + responseDO.isSuccess());
        } catch (Exception e) {
            logger.error("根据ID更新表数据异常!", e);
        }
    }

    @Test
    public void delete() {
        try {
            FlowDO flowDO = new FlowDO();
            flowDO.setId(id);
            ResponseDO<FlowDO> responseDO = flowService.delFlowDO(id);
            logger.info("Result:" + responseDO.isSuccess());
        } catch (Exception e) {
            logger.error("根据ID逻辑删除表数据异常!", e);
        }
    }
}