package org.wsh.common.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wsh.common.enums.msg.MessageType;
import org.wsh.common.model.msg.MessageDO;
import org.wsh.common.service.api.MenuService;
import org.wsh.common.service.api.message.MessageService;
import org.wsh.common.support.base.AbstractLogger;
import org.wsh.common.support.exception.BusinessException;

import javax.annotation.Resource;
import java.util.Date;

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
//        doAddMessageDO();

        Long messageId = 1L;
        try {
            messageService.doDelMessageById(messageId);
//            ResponseDO<MessageDO> responseDO = messageService.getMessageById(messageId);
//            logger.info("messageText:" + responseDO.getData().getContent());
        } catch (BusinessException e) {
            logger.error("根据ID=>["+messageId+"]查询消息异常!",e);
        }
    }

    private void doAddMessageDO() {
        try {
            MessageDO messageDO = new MessageDO();
            messageDO.setFromUserId("1");
            messageDO.setType(MessageType.TO_ONE);
            messageDO.setToUserId("2");
            messageDO.setContent("Test...");
            messageDO.setGmtCreated(new Date());
            messageDO.setGmtModified(new Date());
            messageService.doAddMessage(messageDO);
        } catch (BusinessException e) {
            logger.error("添加消息异常!",e);
        }
    }

}
