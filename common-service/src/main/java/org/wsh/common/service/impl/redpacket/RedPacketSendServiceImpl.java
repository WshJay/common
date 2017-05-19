package org.wsh.common.service.impl.redpacket;

import org.wsh.common.enums.expection.Errors;
import org.wsh.common.model.redpacket.RedPacketSendDO;
import org.wsh.common.dao.redpacket.RedPacketSendDao;
import org.wsh.common.service.api.redpacket.RedPacketSendService;
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
* comments:  RedPacketSend服务实现层
* since Date： 2017-05-10 10:18:17
*/
@Service("redPacketSendService")
public class RedPacketSendServiceImpl extends LoggerService implements RedPacketSendService{

    @Resource
    private RedPacketSendDao redPacketSendDao;

	/**
	* 多条件查询(分页)
	* @param redPacketSendDO RedPacketSendDO
	* @param pagination  Pagination
	* @return List<RedPacketSendDO>
    */
    @Override
    public OptionsResponseDO<List<RedPacketSendDO>> queryRedPacketSendDOListForPage(RedPacketSendDO redPacketSendDO, Pagination pagination){
        try {
            int totalCount = redPacketSendDao.selectCountByParams(redPacketSendDO);
            if (totalCount <= 0) {
                return newStaticOptionsResponseDO(new ArrayList<RedPacketSendDO>());
            }
            pagination.setTotalCount(totalCount);
            List<RedPacketSendDO> redPacketSendDOList = redPacketSendDao.selectListByParams(redPacketSendDO, pagination.getRowBounds());
            return newOptionsResponseDO(totalCount, redPacketSendDOList, pagination.getPageSize(),pagination.getPP());
        } catch (Exception e) {
            logger.error("多条件分页查询列表异常!",e);
            return new OptionsResponseDO<>();
        }
    }

    /**
    * 根据用户ID查询
    * @param id Long
    * @return ResponseDO<RedPacketSendDO>
    */
    @Override
    @Cacheable(value = "common:redPacketSendDO",key = "'common:redPacketSendDO:id:' + #id")
    public ResponseDO<RedPacketSendDO> getRedPacketSendDOById(Long id){
        try {
            Assert.notNull(id,"查询Id不能为空!");
            RedPacketSendDO redPacketSendDO = redPacketSendDao.selectById(id);
            return newStaticResponseDO(redPacketSendDO);
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
    * @param redPacketSendDO RedPacketSendDO
    * @return ResponseDO<RedPacketSendDO>
    */
    @Override
    public ResponseDO<RedPacketSendDO> getRedPacketSendDOByParams(RedPacketSendDO redPacketSendDO){
        try {
            return newStaticResponseDO(redPacketSendDao.selectByParams(redPacketSendDO));
        } catch (Exception e) {
            logger.error("多条件查询信息异常!",e);
            return new ResponseDO<>(Errors.DEFAULT_ERROR.getErrorCode(),Errors.DEFAULT_ERROR.getErrorMsg());
        }
    }

    /**
    * 新增
    * @param redPacketSendDO RedPacketSendDO
    * @return ResponseDO<RedPacketSendDO>
    */
    @Override
    @CachePut(value = "common:redPacketSendDO",key = "'common:redPacketSendDO:id:' + #redPacketSendDO.id")
    public ResponseDO<RedPacketSendDO> addRedPacketSendDO(RedPacketSendDO redPacketSendDO){
        try {
            // Validate
            validateForAdd(redPacketSendDO);

            // Insert
            int result = redPacketSendDao.insert(redPacketSendDO);
            if (result < 1) {
                throw new Exception("sql插入数据为0,请检查各项参数!");
            }
            logger.info("新增ID=>[" + redPacketSendDO.getId() + "]的RedPacketSendDO成功");
            return newStaticResponseDO(redPacketSendDO);
        } catch (IllegalArgumentException e) {
            logger.warn("新增RedPacketSendDO校验参数异常!",e);
            return new ResponseDO<>(Errors.PARAMETER_ERROR.getErrorCode(),e.getMessage());
        } catch (DuplicateKeyException e) {
            logger.error("新增ID=>[" + redPacketSendDO.getId() + "]RedPacketSendDO已存在!",e);
            return new ResponseDO<>(Errors.REPEAT_SUBMIT.getErrorCode(),Errors.REPEAT_SUBMIT.getErrorMsg());
        } catch (Exception e) {
            logger.error("新增ID=>[" + redPacketSendDO.getId() + "]RedPacketSendDO异常!",e);
            return new ResponseDO<>(Errors.DEFAULT_ERROR.getErrorCode(),Errors.DEFAULT_ERROR.getErrorMsg());
        }
    }

    /**
    * Validate Add
    * @param redPacketSendDO RedPacketSendDO
    */
    private void validateForAdd(RedPacketSendDO redPacketSendDO) {
        Assert.isTrue(redPacketSendDO != null,"redPacketSendDO不能为空!");
        // TODO Validate
    }

    /**
    * 修改
    * @param redPacketSendDO RedPacketSendDO
	* @return ResponseDO<RedPacketSendDO>
    */
    @Override
    @CacheEvict(value = "common:redPacketSendDO",key = "'common:redPacketSendDO:id:' + #redPacketSendDO.id",beforeInvocation = true)
    public ResponseDO<RedPacketSendDO> modifyRedPacketSendDO(RedPacketSendDO redPacketSendDO){
        try {

            // validate
            RedPacketSendDO oldRedPacketSendDO = validateForUpdate(redPacketSendDO);

            // Update
            redPacketSendDO.setVersion(oldRedPacketSendDO.getVersion());
            int result = redPacketSendDao.updateById(redPacketSendDO);
            if (result < 1) {
                throw new Exception("sql修改数据为0,请检查各项参数!");
            }
            logger.info("修改ID=>[" + redPacketSendDO.getId() + "]的redPacketSendDO成功!");
            return newStaticResponseDO(redPacketSendDO);
        } catch (IllegalArgumentException e) {
            logger.warn("修改RedPacketSendDO校验参数异常!",e);
            return new ResponseDO<>(Errors.PARAMETER_ERROR.getErrorCode(),e.getMessage());
        } catch (Exception e) {
            logger.error("修改ID=>[" + redPacketSendDO.getId() + "]RedPacketSendDO异常!",e);
            return new ResponseDO<>(Errors.DEFAULT_ERROR.getErrorCode(),Errors.DEFAULT_ERROR.getErrorMsg());
        }
    }

    /**
    * Validate Update
    * @param redPacketSendDO RedPacketSendDO
    * @return RedPacketSendDO
    */
    private RedPacketSendDO validateForUpdate(RedPacketSendDO redPacketSendDO) {

        Assert.notNull(redPacketSendDO,"redPacketSendDO不能为空!");
        Assert.isTrue(redPacketSendDO.getId() != null,"查询Id不能为空!");
        // TODO Validate
        RedPacketSendDO oldRedPacketSendDO = redPacketSendDao.selectById(redPacketSendDO.getId());
        Assert.notNull(oldRedPacketSendDO,"查询不到ID=>[" + redPacketSendDO.getId() + "]的信息!");
        return oldRedPacketSendDO;
    }

    /**
    * 删除(逻辑删除)
    * @param id Long
    * @return ResponseDO<RedPacketSendDO>
    */
    @Override
    @CacheEvict(value = "common:redPacketSendDO",key = "'common:redPacketSendDO:id:' + #id",beforeInvocation = true)
    public ResponseDO<RedPacketSendDO> delRedPacketSendDO(Long id){
        try {

            // validate
            Assert.notNull(id,"缺少必要参数!");
            RedPacketSendDO oldredPacketSendDO = redPacketSendDao.selectById(id);
            Assert.notNull(oldredPacketSendDO,"传入参数有误!");
            RedPacketSendDO redPacketSendDO = new RedPacketSendDO();
            redPacketSendDO.setId(id);
            redPacketSendDO.setDeleted(true);
            redPacketSendDO.setVersion(oldredPacketSendDO.getVersion());
            // update
            int result = redPacketSendDao.updateIsDeleteById(redPacketSendDO);
            if (result < 1) {
                throw new Exception("数据已删除,请勿重复操作!");
            }
            logger.info("删除ID=>[" + id + "]的redPacketSendDO成功!");
            return newStaticResponseDO(redPacketSendDO);
        } catch (IllegalArgumentException e) {
            logger.warn("删除RedPacketSendDO校验参数异常!",e);
            return new ResponseDO<>(Errors.PARAMETER_ERROR.getErrorCode(),e.getMessage());
        } catch (Exception e) {
            logger.error("删除ID=>[" + id + "]RedPacketSendDO异常!",e);
            return new ResponseDO<>(Errors.DEFAULT_ERROR.getErrorCode(),Errors.DEFAULT_ERROR.getErrorMsg());
        }
    }
}
