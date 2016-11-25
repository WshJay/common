package org.wsh.common.service.api.message;

import org.wsh.common.model.message.MessageDO;
import org.springframework.stereotype.Service;
import org.wsh.common.pager.pagination.Pagination;
import org.wsh.common.support.beans.OptionsResponseDO;
import org.wsh.common.support.exception.BusinessException;
import org.wsh.common.support.response.ResponseDO;
import static org.wsh.common.support.beans.BeansUtil.newOptionsResponseDO;
import java.util.List;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  Message服务层
* since Date： 2016-11-23 14:56:02
*/
@Service
public interface MessageService {

	/**
	* 多条件查询(分页)
	* @param messageDO MessageDO
	* @param pagination  Pagination
	* @return OptionsResponseDO<List<MessageDO>>
    */
    public OptionsResponseDO<List<MessageDO>> queryMessageDOListForPage(MessageDO messageDO, Pagination pagination) throws BusinessException;

    /**
    * 根据用户ID查询
    * @param id Long
    * @return ResponseDO<MessageDO>
    */
    public ResponseDO<MessageDO> getMessageDOById(Long id) throws BusinessException;

    /**
    * 添加
    * @param messageDO MessageDO
    * @return ResponseDO<MessageDO>
    */
    public ResponseDO<MessageDO> addMessageDO(MessageDO messageDO) throws BusinessException;

    /**
    * 修改
    * @param messageDO MessageDO
	* @return ResponseDO<MessageDO>
    */
    public ResponseDO<MessageDO> modifyMessageDO(MessageDO messageDO) throws BusinessException;

    /**
    * 删除(逻辑删除)
    * @param id Long
    * @return ResponseDO<MessageDO>
    * @throws BusinessException
    */
    public ResponseDO<MessageDO> delMessageDO(Long id) throws BusinessException;
}
