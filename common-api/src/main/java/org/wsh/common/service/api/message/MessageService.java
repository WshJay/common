package org.wsh.common.service.api.message;

import org.wsh.common.model.msg.MessageDO;
import org.wsh.common.pager.pagination.Pagination;
import org.wsh.common.support.beans.OptionsResponseDO;
import org.wsh.common.support.exception.BusinessException;
import org.wsh.common.support.response.ResponseDO;

import javax.annotation.Resource;
import java.util.List;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  消息服务
 * since Date： 2016-11-07 14:06
 */
public interface MessageService {

    /**
     * 多条件查询消息信息
     * @param messageDO MessageDO
     * @param pagination Pagination
     * @return OptionsResponseDO
     */
    public OptionsResponseDO<List<MessageDO>> getMessageListByParams(MessageDO messageDO, Pagination pagination) throws BusinessException;

    /**
     * 新增消息
     * @param messageDO 消息bean
     * @return ResponseDO
     * @throws BusinessException
     */
    public ResponseDO doAddMessage(MessageDO messageDO) throws BusinessException;

    /**
     * 根据ID获取消息内容
     * @param id 消息ID
     * @return ResponseDO
     * @throws BusinessException
     */
    public ResponseDO<MessageDO> getMessageById(Long id) throws BusinessException;

    /**
     * 根据ID删除消息
     * @param id 消息ID
     * @return ResponseDO
     * @throws BusinessException
     */
    public ResponseDO doDelMessageById(Long id) throws BusinessException;
}
