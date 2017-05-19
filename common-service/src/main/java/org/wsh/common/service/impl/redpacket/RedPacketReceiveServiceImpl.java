package org.wsh.common.service.impl.redpacket;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.wsh.common.dao.redpacket.RedPacketSendDao;
import org.wsh.common.enums.expection.Errors;
import org.wsh.common.enums.redpacket.RedPacketType;
import org.wsh.common.model.redpacket.RedPacketReceiveDO;
import org.wsh.common.dao.redpacket.RedPacketReceiveDao;
import org.wsh.common.model.redpacket.RedPacketSendDO;
import org.wsh.common.service.api.redpacket.RedPacketReceiveService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.Assert;
import org.springframework.stereotype.Service;
import org.wsh.common.util.logger.LoggerService;
import org.wsh.common.pager.pagination.Pagination;
import org.wsh.common.support.beans.OptionsResponseDO;
import org.wsh.common.support.response.ResponseDO;
import org.wsh.common.util.redpacket.RedPacketUtil;

import javax.annotation.Resource;
import static org.wsh.common.support.beans.BeansUtil.newOptionsResponseDO;
import static org.wsh.common.support.beans.BeansUtil.newStaticOptionsResponseDO;
import static org.wsh.common.support.beans.BeansUtil.newStaticResponseDO;
import static org.wsh.common.util.redpacket.RedPacketUtil.randomAmount;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  RedPacketReceive服务实现层
* since Date： 2017-05-10 10:18:17
*/
@Service("redPacketReceiveService")
public class RedPacketReceiveServiceImpl extends LoggerService implements RedPacketReceiveService{

    @Resource
    private RedPacketReceiveDao redPacketReceiveDao;

    @Resource
    private RedPacketSendDao redPacketSendDao;

	/**
	* 多条件查询(分页)
	* @param redPacketReceiveDO RedPacketReceiveDO
	* @param pagination  Pagination
	* @return List<RedPacketReceiveDO>
    */
    @Override
    public OptionsResponseDO<List<RedPacketReceiveDO>> queryRedPacketReceiveDOListForPage(RedPacketReceiveDO redPacketReceiveDO, Pagination pagination){
        try {
            int totalCount = redPacketReceiveDao.selectCountByParams(redPacketReceiveDO);
            if (totalCount <= 0) {
                return newStaticOptionsResponseDO(new ArrayList<RedPacketReceiveDO>());
            }
            pagination.setTotalCount(totalCount);
            List<RedPacketReceiveDO> redPacketReceiveDOList = redPacketReceiveDao.selectListByParams(redPacketReceiveDO, pagination.getRowBounds());
            return newOptionsResponseDO(totalCount, redPacketReceiveDOList, pagination.getPageSize(),pagination.getPP());
        } catch (Exception e) {
            logger.error("多条件分页查询列表异常!",e);
            return new OptionsResponseDO<>();
        }
    }

    /**
    * 根据用户ID查询
    * @param id Long
    * @return ResponseDO<RedPacketReceiveDO>
    */
    @Override
    @Cacheable(value = "common:redPacketReceiveDO",key = "'common:redPacketReceiveDO:id:' + #id")
    public ResponseDO<RedPacketReceiveDO> getRedPacketReceiveDOById(Long id){
        try {
            Assert.notNull(id,"查询Id不能为空!");
            RedPacketReceiveDO redPacketReceiveDO = redPacketReceiveDao.selectById(id);
            return newStaticResponseDO(redPacketReceiveDO);
        } catch (IllegalArgumentException e) {
            logger.warn("根据ID查询校验参数异常!",e);
            return new ResponseDO<>(Errors.PARAMETER_ERROR.getErrorCode(),e.getMessage());
        } catch (Exception e) {
            logger.error("根据ID=>[" + id + "]查询信息异常!",e);
            return new ResponseDO<>(Errors.DEFAULT_ERROR.getErrorCode(),Errors.DEFAULT_ERROR.getErrorMsg());
        }
    }

    /**
    * 多条件查询信息
    * @param redPacketReceiveDO RedPacketReceiveDO
    * @return ResponseDO<RedPacketReceiveDO>
    */
    @Override
    public ResponseDO<RedPacketReceiveDO> getRedPacketReceiveDOByParams(RedPacketReceiveDO redPacketReceiveDO){
        try {
            return newStaticResponseDO(redPacketReceiveDao.selectByParams(redPacketReceiveDO));
        } catch (Exception e) {
            logger.error("多条件查询信息异常!",e);
            return new ResponseDO<>(Errors.DEFAULT_ERROR.getErrorCode(),Errors.DEFAULT_ERROR.getErrorMsg());
        }
    }

    /**
    * 新增
    * @param redPacketReceiveDO RedPacketReceiveDO
    * @return ResponseDO<RedPacketReceiveDO>
    */
    @Override
    @CachePut(value = "common:redPacketReceiveDO",key = "'common:redPacketReceiveDO:id:' + #redPacketReceiveDO.id")
    public ResponseDO<RedPacketReceiveDO> addRedPacketReceiveDO(RedPacketReceiveDO redPacketReceiveDO){
        try {
            // Validate
            validateForAdd(redPacketReceiveDO);

            // Insert
            int result = redPacketReceiveDao.insert(redPacketReceiveDO);
            if (result < 1) {
                throw new Exception("sql插入数据为0,请检查各项参数!");
            }
            logger.info("新增ID=>[" + redPacketReceiveDO.getId() + "]的RedPacketReceiveDO成功");
            return newStaticResponseDO(redPacketReceiveDO);
        } catch (IllegalArgumentException e) {
            logger.warn("新增RedPacketReceiveDO校验参数异常!",e);
            return new ResponseDO<>(Errors.PARAMETER_ERROR.getErrorCode(),e.getMessage());
        } catch (DuplicateKeyException e) {
            logger.error("新增ID=>[" + redPacketReceiveDO.getId() + "]RedPacketReceiveDO已存在!",e);
            return new ResponseDO<>(Errors.REPEAT_SUBMIT.getErrorCode(),Errors.REPEAT_SUBMIT.getErrorMsg());
        } catch (Exception e) {
            logger.error("新增ID=>[" + redPacketReceiveDO.getId() + "]RedPacketReceiveDO异常!",e);
            return new ResponseDO<>(Errors.DEFAULT_ERROR.getErrorCode(),Errors.DEFAULT_ERROR.getErrorMsg());
        }
    }

    /**
    * Validate Add
    * @param redPacketReceiveDO RedPacketReceiveDO
    */
    private void validateForAdd(RedPacketReceiveDO redPacketReceiveDO) {
        Assert.isTrue(redPacketReceiveDO != null,"redPacketReceiveDO不能为空!");
        // TODO Validate
    }

    /**
    * 修改
    * @param redPacketReceiveDO RedPacketReceiveDO
	* @return ResponseDO<RedPacketReceiveDO>
    */
    @Override
    @CacheEvict(value = "common:redPacketReceiveDO",key = "'common:redPacketReceiveDO:id:' + #redPacketReceiveDO.id",beforeInvocation = true)
    public ResponseDO<RedPacketReceiveDO> modifyRedPacketReceiveDO(RedPacketReceiveDO redPacketReceiveDO){
        try {

            // validate
            RedPacketReceiveDO oldRedPacketReceiveDO = validateForUpdate(redPacketReceiveDO);

            // Update
            redPacketReceiveDO.setVersion(oldRedPacketReceiveDO.getVersion());
            int result = redPacketReceiveDao.updateById(redPacketReceiveDO);
            if (result < 1) {
                throw new Exception("sql修改数据为0,请检查各项参数!");
            }
            logger.info("修改ID=>[" + redPacketReceiveDO.getId() + "]的redPacketReceiveDO成功!");
            return newStaticResponseDO(redPacketReceiveDO);
        } catch (IllegalArgumentException e) {
            logger.warn("修改RedPacketReceiveDO校验参数异常!",e);
            return new ResponseDO<>(Errors.PARAMETER_ERROR.getErrorCode(),e.getMessage());
        } catch (Exception e) {
            logger.error("修改ID=>[" + redPacketReceiveDO.getId() + "]RedPacketReceiveDO异常!",e);
            return new ResponseDO<>(Errors.DEFAULT_ERROR.getErrorCode(),Errors.DEFAULT_ERROR.getErrorMsg());
        }
    }

    /**
    * Validate Update
    * @param redPacketReceiveDO RedPacketReceiveDO
    * @return RedPacketReceiveDO
    */
    private RedPacketReceiveDO validateForUpdate(RedPacketReceiveDO redPacketReceiveDO) {

        Assert.notNull(redPacketReceiveDO,"redPacketReceiveDO不能为空!");
        Assert.isTrue(redPacketReceiveDO.getId() != null,"查询Id不能为空!");
        // TODO Validate
        RedPacketReceiveDO oldRedPacketReceiveDO = redPacketReceiveDao.selectById(redPacketReceiveDO.getId());
        Assert.notNull(oldRedPacketReceiveDO,"查询不到ID=>[" + redPacketReceiveDO.getId() + "]的信息!");
        return oldRedPacketReceiveDO;
    }

    /**
    * 删除(逻辑删除)
    * @param id Long
    * @return ResponseDO<RedPacketReceiveDO>
    */
    @Override
    @CacheEvict(value = "common:redPacketReceiveDO",key = "'common:redPacketReceiveDO:id:' + #id",beforeInvocation = true)
    public ResponseDO<RedPacketReceiveDO> delRedPacketReceiveDO(Long id){
        try {

            // validate
            Assert.notNull(id,"缺少必要参数!");
            RedPacketReceiveDO oldredPacketReceiveDO = redPacketReceiveDao.selectById(id);
            Assert.notNull(oldredPacketReceiveDO,"传入参数有误!");
            RedPacketReceiveDO redPacketReceiveDO = new RedPacketReceiveDO();
            redPacketReceiveDO.setId(id);
            redPacketReceiveDO.setDeleted(true);
            redPacketReceiveDO.setVersion(oldredPacketReceiveDO.getVersion());
            // update
            int result = redPacketReceiveDao.updateIsDeleteById(redPacketReceiveDO);
            if (result < 1) {
                throw new Exception("数据已删除,请勿重复操作!");
            }
            logger.info("删除ID=>[" + id + "]的redPacketReceiveDO成功!");
            return newStaticResponseDO(redPacketReceiveDO);
        } catch (IllegalArgumentException e) {
            logger.warn("删除RedPacketReceiveDO校验参数异常!",e);
            return new ResponseDO<>(Errors.PARAMETER_ERROR.getErrorCode(),e.getMessage());
        } catch (Exception e) {
            logger.error("删除ID=>[" + id + "]RedPacketReceiveDO异常!",e);
            return new ResponseDO<>(Errors.DEFAULT_ERROR.getErrorCode(),Errors.DEFAULT_ERROR.getErrorMsg());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDO<RedPacketReceiveDO> doReceiveRedPacket(RedPacketReceiveDO redPacketReceiveDO) {
        try {
            // Validate
            validateForReceive(redPacketReceiveDO);

            RedPacketSendDO redPacketSendDO = validateRemain(redPacketReceiveDO);

            addReceiveRedPacket(redPacketReceiveDO, redPacketSendDO);

            logger.info("订单ID=>[" + redPacketReceiveDO.getBusinessNumber() + "]领取红包成功!");
            return newStaticResponseDO(redPacketReceiveDO);
        } catch (IllegalArgumentException e) {
            logger.warn("领取红包校验参数异常!",e);
            // 手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResponseDO<>(Errors.PARAMETER_ERROR.getErrorCode(),e.getMessage());
        } catch (DuplicateKeyException e) {
            logger.error("订单ID=>[" + redPacketReceiveDO.getBusinessNumber() + "],账户ID=>[" + redPacketReceiveDO.getAccountId() + "]重复领取红包!",e);
            // 手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResponseDO<>(Errors.REPEAT_SUBMIT.getErrorCode(),Errors.REPEAT_SUBMIT.getErrorMsg());
        } catch (Exception e) {
            logger.error("订单ID=>[" + redPacketReceiveDO.getBusinessNumber() + "]领取红包异常!",e);
            // 手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResponseDO<>(Errors.DEFAULT_ERROR.getErrorCode(),Errors.DEFAULT_ERROR.getErrorMsg());
        }
    }

    private RedPacketSendDO validateRemain(RedPacketReceiveDO redPacketReceiveDO) throws Exception {

        // 悲观锁
        RedPacketSendDO redPacketSendDO = redPacketSendDao.selectForCalculate(new RedPacketSendDO(redPacketReceiveDO.getSendId()));
        // 乐观锁
//        RedPacketSendDO redPacketSendDO = redPacketSendDao.selectById(redPacketReceiveDO.getSendId());
        Assert.notNull(redPacketSendDO,"传入数据有误!");
        Assert.isTrue(redPacketSendDO.getRemainNum() > 0,"红包剩余数量不足!");
        return redPacketSendDO;
    }

    private void addReceiveRedPacket(RedPacketReceiveDO redPacketReceiveDO, RedPacketSendDO redPacketSendDO) throws Exception {

        BigDecimal amount = new BigDecimal(0);
        if (redPacketSendDO.getType() == RedPacketType.AVERAGE){
            amount = redPacketSendDO.getAverageAmount();
        }else if(redPacketSendDO.getType() == RedPacketType.REDOM){
            amount = new BigDecimal(RedPacketUtil.randomAmount(redPacketSendDO.getRemainNum(), redPacketSendDO.getRemainAmount().doubleValue()));
        }
        // Insert
        insertReceiveDO(redPacketReceiveDO, amount);

        // update
        cutSendRemain(redPacketSendDO, amount);
    }

    private void insertReceiveDO(RedPacketReceiveDO redPacketReceiveDO, BigDecimal amount) throws Exception {
        redPacketReceiveDO.setAmount(amount);
        int result = redPacketReceiveDao.insert(redPacketReceiveDO);
        if (result < 1) {
            throw new Exception("sql插入数据为0,请检查各项参数!");
        }
    }

    private void cutSendRemain(RedPacketSendDO redPacketSendDO, BigDecimal amount) throws Exception {
        redPacketSendDO.setRemainAmount(amount);
        int result = redPacketSendDao.updateRemainById(redPacketSendDO);
        if (result < 1) {
            throw new Exception("sql插入数据为0,请检查各项参数!");
        }
    }

    private void validateForReceive(RedPacketReceiveDO redPacketReceiveDO) {
        Assert.notNull(redPacketReceiveDO,"缺少必要参数!");
        Assert.notNull(redPacketReceiveDO.getSendId(),"红包ID不能为空!");
        Assert.hasText(redPacketReceiveDO.getUserId(),"领取用户ID不能为空!");
        Assert.hasText(redPacketReceiveDO.getAccountId(),"领取账户ID不能为空!");
        Assert.hasText(redPacketReceiveDO.getBillNumber(),"账单编号不能为空!");
        Assert.hasText(redPacketReceiveDO.getBillNumber(),"业务编号不能为空!");
    }
}
