package org.wsh.common.service.impl.message;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import org.wsh.common.dao.msg.MessageDao;
import org.wsh.common.model.msg.MessageDO;
import org.wsh.common.pager.pagination.Pagination;
import org.wsh.common.service.api.message.MessageService;
import org.wsh.common.support.beans.OptionsResponseDO;
import org.wsh.common.support.exception.BusinessException;
import org.wsh.common.support.response.ResponseDO;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  消息服务层
 * since Date： 2016-11-07 15:55
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {
    @Resource
    private MessageDao messageDao;

    @Override
    @CachePut(value = "messageCache",key = "'messageCache' + #messageDO.id")
    public ResponseDO doAddMessage(MessageDO messageDO) throws BusinessException {
        try {
            // TODO validate
            messageDao.insert(messageDO);
        } catch (Exception e) {
            throw new BusinessException("添加消息失败:", e);
        }

        return new ResponseDO();
    }

    @Override
    @Cacheable(value = "messageCache",key = "'messageCache' + #id")
    public ResponseDO<MessageDO> getMessageById(Long id) throws BusinessException {
        ResponseDO<MessageDO> responseDO = new ResponseDO<MessageDO>();
        try {
            Assert.isTrue(id != null, "消息ID不能为空!");
            MessageDO messageDO = messageDao.loadById(id);
            responseDO.setData(messageDO);
        } catch (Exception e) {
            throw new BusinessException("根据消息ID查询消息失败:", e);
        }
        return responseDO;
    }

    @Override
    @CacheEvict(value = "messageCache",key = "'messageCache' + #id")
    public ResponseDO doDelMessageById(Long id) throws BusinessException {
        try {
            Assert.isTrue(id != null, "消息ID不能为空!");
            messageDao.deleteById(id);
        } catch (Exception e) {
            throw new BusinessException("删除消息ID=>[" + id + "]的消息失败:", e);
        }
        return new ResponseDO();
    }

    @Override
    public OptionsResponseDO<List<MessageDO>> getMessageListByParams(MessageDO messageDO, Pagination pagination)
            throws BusinessException {
        return null;
    }
}