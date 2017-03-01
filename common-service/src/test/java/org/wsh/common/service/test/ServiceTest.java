package org.wsh.common.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wsh.common.enums.flow.FileType;
import org.wsh.common.enums.msg.MessageType;
import org.wsh.common.model.basic.MenuDO;
import org.wsh.common.model.basic.UserBasicDO;
import org.wsh.common.model.flow.FileDO;
import org.wsh.common.model.message.MessageDO;
import org.wsh.common.pager.pagination.Pagination;
import org.wsh.common.service.api.MenuService;
import org.wsh.common.service.api.flow.FileService;
import org.wsh.common.service.api.message.MessageService;
import org.wsh.common.service.api.mysql.lock.OptimisticLockService;
import org.wsh.common.service.api.mysql.lock.PessimisticLockService;
import org.wsh.common.support.beans.OptionsResponseDO;
import org.wsh.common.support.exception.BusinessException;
import org.wsh.common.support.response.ResponseDO;
import org.wsh.common.util.concurrent.ConcurrentUtil;
import org.wsh.common.util.concurrent.Task;
import org.wsh.common.util.logger.LoggerService;

import javax.annotation.Resource;
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
public class ServiceTest extends LoggerService{

    @Resource
    private MenuService menuService;

    @Resource
    private MessageService messageService;

//    @Resource
//    private UserBasicService userBasicService;

    @Autowired
    private CacheManager cacheManager;

    @Resource
    private PessimisticLockService pessimisticLockService;

    @Resource
    private OptimisticLockService optimisticLockService;

    @Resource
    private FileService fileService;

    @Test
    public void test() throws BusinessException {
//        List<MenuDO> menuDOList = menuService.getAllRootMenu();
//        for (MenuDO menuDO : menuDOList) {
//            logger.info("menuName==>" + menuDO.getName());
//        }
//
        FileDO fileParamDO = new FileDO();
        fileParamDO.setType(FileType.IMG);
        fileParamDO.setUserId(2L);
          fileService.queryFileDOListForPage(fileParamDO,new Pagination(100));
//        Cache comMessageCache = cacheManager.getCache("common_messageDO");
//        Cache fundsMessageCache = cacheManager.getCache("funds_messageDO");
//        System.out.println(comMessageCache.get("message_id:12").get());
//        System.out.println(fundsMessageCache.get("message_id:12").get());

//        ResponseDO<UserBasicDO> responseDO = userBasicService.getUserBasicDOById(1L);
//        userBasicService.getUserBasicDOByUserName("管理员");
//
//        UserBasicDO user = new UserBasicDO();
//        user.setId(1L);
//        userBasicService.modifyUserBasicDO(user);
//        userBasicService.modifyUserBasicDO(responseDO.getData());

//        optimisticLockUpdate();
//        pessimisticLockUpdate();

    }

    private void optimisticLockUpdate() {
        try {
            UserBasicDO user = new UserBasicDO();
            user.setId(6L);
            user.setFaceUrl("321");
            int count = 3;
            ConcurrentUtil.start(new Task(count,optimisticLockService,"updateForLock",user), count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void pessimisticLockUpdate() {
        try {
            UserBasicDO user = new UserBasicDO();
            user.setId(6L);
            user.setFaceUrl("123");
            int count = 3;
            ConcurrentUtil.start(new Task(count,pessimisticLockService,"updateForLock",user), count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void doAddMessageDO() {
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
    }

    @Test
    public void getListMessageDO() {
        try {
            OptionsResponseDO<List<MessageDO>> responseDO = messageService.queryMessageDOListForPage(null,new Pagination());
            List<MessageDO> messageDOList = responseDO.getData();
            for (MessageDO messageDO : messageDOList) {
                logger.info("id=>[" + messageDO.getId() + "]");
            }
        } catch (BusinessException e) {
            logger.error("获取消息异常!",e);
        }
    }

    @Test
    public void getById() {
        try {
            messageService.getMessageDOById(1L);
//            ResponseDO<MessageDO> responseDO = messageService.getMessageDOById(12L);
//            logger.info("id=>[" + responseDO.getData() + "]");
//            logger.info("topic=>" + responseDO.getData().getTopic());
        } catch (BusinessException e) {
            logger.error("获取消息异常!",e);
        }
    }

    @Test
    public void update() {
        try {
            ResponseDO<MessageDO> oldresponseDO = messageService.getMessageDOById(12L);
            MessageDO messageDO = new MessageDO();
            messageDO.setId(oldresponseDO.getData().getId());
            messageDO.setTopic("Test Update");
            messageService.modifyMessageDO(messageDO);
//            ResponseDO responseDO = messageService.modifyMessageDO(messageDO);
//            logger.info("id=>[" + responseDO.getData() + "]");
        } catch (BusinessException e) {
            logger.error("修改消息异常!",e);
        }
    }

    @Test
    public void delete() {
        try {
            ResponseDO responseDO = messageService.delMessageDO(11L);
            logger.info("id=>[" + responseDO.getData() + "]");
        } catch (BusinessException e) {
            logger.error("添加消息异常!",e);
        }
    }

}
