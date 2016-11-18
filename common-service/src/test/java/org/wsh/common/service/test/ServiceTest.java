package org.wsh.common.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wsh.common.enums.msg.MessageType;
import org.wsh.common.model.message.MessageDO;
import org.wsh.common.pager.pagination.Pagination;
import org.wsh.common.service.api.MenuService;
import org.wsh.common.service.api.message.MessageService;
import org.wsh.common.support.base.AbstractLogger;
import org.wsh.common.support.beans.OptionsResponseDO;
import org.wsh.common.support.exception.BusinessException;
import org.wsh.common.support.response.ResponseDO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2016-09-29 17:30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:common-bean.xml")
public class ServiceTest extends AbstractLogger{

    @Resource
    private MenuService menuService;

    @Resource
    private MessageService messageService;

    @Test
    public void test(){
//        List<MenuDO> menuDOList = menuService.getAllRootMenu();
//        for (MenuDO menuDO : menuDOList) {
//            logger.info("menuName==>" + menuDO.getName());
//        }
//
        doAddMessageDO();

        getListMessageDO();

        getById();

        update();

        delete();
    }

    private MessageDO doAddMessageDO() {
        MessageDO messageDO = new MessageDO();
        try {
            messageDO.setTopic("Test");
            messageDO.setFromId(1L);
            messageDO.setType(MessageType.TO_ONE.toString());
            messageDO.setTargetId(2L);
            messageDO.setContent("Test...");
            messageDO.setStatus("SENDED");
            messageDO.setGmtCreated(new Date());
            messageDO.setGmtModified(new Date());
            messageService.addMessageDO(messageDO);
        } catch (BusinessException e) {
            logger.error("添加消息异常!",e);
        }
        return messageDO;
    }

    private void getListMessageDO() {
        try {
            OptionsResponseDO<List<MessageDO>> responseDO = messageService.getMessageDOListForPage(null,new Pagination());
            List<MessageDO> messageDOList = responseDO.getData();
            for (MessageDO messageDO : messageDOList) {
                logger.info("id=>[" + messageDO.getId() + "]");
            }
        } catch (BusinessException e) {
            logger.error("获取消息异常!",e);
        }
    }

    private void getById() {
        try {
            ResponseDO responseDO = messageService.getMessageDOById(1L);
            logger.info("id=>[" + responseDO.getData() + "]");
        } catch (BusinessException e) {
            logger.error("获取消息异常!",e);
        }
    }

    private void update() {
        try {
            ResponseDO<MessageDO> oldresponseDO = messageService.getMessageDOById(2L);
            MessageDO messageDO = new MessageDO();
            messageDO.setId(oldresponseDO.getData().getId());
            messageDO.setTopic("Test Update");
            ResponseDO responseDO = messageService.updateMessageDO(messageDO);
            logger.info("id=>[" + responseDO.getData() + "]");
        } catch (BusinessException e) {
            logger.error("修改消息异常!",e);
        }
    }

    private void delete() {
        try {
            ResponseDO responseDO = messageService.deleteMessageDO(1L);
            logger.info("id=>[" + responseDO.getData() + "]");
        } catch (BusinessException e) {
            logger.error("添加消息异常!",e);
        }
    }

}
