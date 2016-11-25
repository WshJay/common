package org.wsh.common.service.impl.message ;

import org.wsh.common.model.message.MessageDO;
import org.wsh.common.dao.message.MessageDao;
import org.wsh.common.service.api.message.MessageService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.Assert;
import org.springframework.stereotype.Service;
import org.wsh.common.util.logger.LoggerService;
import org.springframework.transaction.annotation.Transactional;
import org.wsh.common.pager.pagination.Pagination;
import org.wsh.common.support.beans.OptionsResponseDO;
import org.wsh.common.support.exception.BusinessException;
import org.wsh.common.support.response.ResponseDO;
import javax.annotation.Resource;
import static org.wsh.common.support.beans.BeansUtil.newOptionsResponseDO;
import static org.wsh.common.support.beans.BeansUtil.newStaticOptionsResponseDO;
import static org.wsh.common.support.beans.BeansUtil.newStaticResponseDO;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  Message服务实现层
* since Date： 2016-11-23 14:56:02
*/
@Service("messageService")
public class MessageServiceImpl extends LoggerService implements MessageService{

    @Resource
    private MessageDao messageDao;

	/**
	* 多条件查询(分页)
	* @param messageDO MessageDO
	* @param pagination  Pagination
	* @return List<MessageDO>
    */
    @Override
    public OptionsResponseDO<List<MessageDO>> queryMessageDOListForPage(MessageDO messageDO, Pagination pagination) throws BusinessException{
        try {
            int totalCount = messageDao.selectCountByParams(messageDO);
            if (totalCount <= 0) {
                return newStaticOptionsResponseDO(new ArrayList<MessageDO>());
            }
            pagination.setTotalCount(totalCount);
            List<MessageDO> messageDOList = messageDao.selectListByParams(messageDO, pagination.getRowBounds());
            return newOptionsResponseDO(totalCount, messageDOList, pagination.getPageSize(),pagination.getPP());
        } catch (Exception e) {
            throw new BusinessException("多条件查询MessageDO信息异常",e);
        }
    }

    /**
    * 根据用户ID查询
    * @param id Long
    * @return ResponseDO<MessageDO>
    */
    @Override
    @Cacheable(value = "common:messageDO",key = "'common:messageDO:id:' + #id")
    public ResponseDO<MessageDO> getMessageDOById(Long id) throws BusinessException{
        try {
            Assert.isTrue(id != null,"查询Id不能为空!");
            MessageDO messageDO = messageDao.selectById(id);
            return newStaticResponseDO(messageDO);
        } catch (Exception e) {
            throw new BusinessException("根据ID=>[" + id + "]查询MessageDO信息异常",e);
        }
    }

    /**
    * 添加
    * @param messageDO MessageDO
    * @return ResponseDO<MessageDO>
    */
    @Override
    @CachePut(value = "common:messageDO",key = "'common:messageDO:id:' + #messageDO.id")
    public ResponseDO<MessageDO> addMessageDO(MessageDO messageDO) throws BusinessException{
        try {
            // Validate
            validateForAdd(messageDO);

            // Insert
            int result = messageDao.insert(messageDO);
            if (result < 1) {
                throw new Exception("sql插入数据为0,请检查各项参数!");
            }
            logger.info("新增ID=>[" + messageDO.getId() + "]的MessageDO成功");
            return newStaticResponseDO(messageDO);
        } catch (Exception e) {
            throw new BusinessException("新增ID=>[" + messageDO.getId() + "]的MessageDO信息异常",e);
        }
    }

    /**
    * Validate Add
    * @param messageDO MessageDO
    */
    private void validateForAdd(MessageDO messageDO) {
        Assert.isTrue(messageDO != null,"messageDO不能为空!");
        // TODO Validate
    }

    /**
    * 修改
    * @param messageDO MessageDO
	* @return ResponseDO<MessageDO>
    */
    @Override
    @CacheEvict(value = "common:messageDO",key = "'common:messageDO:id:' + #messageDO.id",beforeInvocation = true)
    public ResponseDO<MessageDO> modifyMessageDO(MessageDO messageDO) throws BusinessException{
        try {

            // validate
            MessageDO oldMessageDO = validateForUpdate(messageDO);

            // Update
            messageDO.setVersion(oldMessageDO.getVersion());
            int result = messageDao.updateById(messageDO);
            if (result < 1) {
                throw new Exception("sql修改数据为0,请检查各项参数!");
            }
            logger.info("修改ID=>[" + messageDO.getId() + "]的messageDO成功!");
            return newStaticResponseDO(messageDO);
        }catch (Exception e){
            logger.error("修改ID=>[" + messageDO.getId() + "]的messageDO异常!");
            throw new BusinessException("修改ID=>[" + messageDO.getId() +"]的MessageDO信息异常",e);
        }
    }

    /**
    * Validate Update
    * @param messageDO MessageDO
    * @return MessageDO
    */
    private MessageDO validateForUpdate(MessageDO messageDO) {

        Assert.isTrue(messageDO != null,"messageDO不能为空!");
        Assert.isTrue(messageDO.getId() != null,"ID不能为空!");
        // TODO Validate
        MessageDO oldMessageDO = messageDao.selectById(messageDO.getId());
        Assert.isTrue(oldMessageDO != null,"查询不到ID=>[" + messageDO.getId() + "]的信息!");
        return oldMessageDO;
    }

    /**
    * 删除(逻辑删除)
    * @param id Long
    * @return ResponseDO<MessageDO>
    * @throws BusinessException
    */
    @Override
    @CacheEvict(value = "common:messageDO",key = "'common:messageDO:id:' + #id",beforeInvocation = true)
    public ResponseDO<MessageDO> delMessageDO(Long id) throws BusinessException{
        try {
            // validate
            Assert.isTrue(id != null,"ID不能为空!");

            MessageDO oldmessageDO = messageDao.selectById(id);
            Assert.isTrue(oldmessageDO != null,"查询不到ID=>" + id + "的信息!");
            MessageDO messageDO = new MessageDO();
            messageDO.setId(id);
            messageDO.setIsDeleted(1);
            messageDO.setVersion(oldmessageDO.getVersion());
            // update
            int result = messageDao.updateIsDeleteById(messageDO);
            if (result < 1) {
                throw new Exception("数据已删除,请勿重复操作!");
            }
            logger.info("删除ID=>[" + id + "]的messageDO成功!");
            return newStaticResponseDO(messageDO);
        }catch (Exception e){
            logger.error("删除ID=>[" + id + "]的messageDO异常!");
            throw new BusinessException("删除ID=>[" + id +"]的MessageDO异常",e);
        }
    }
}
