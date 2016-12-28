package org.wsh.common.service.impl.flow;

import org.wsh.common.model.flow.AuditRecordDO;
import org.wsh.common.dao.flow.AuditRecordDao;
import org.wsh.common.service.api.flow.AuditRecordService;
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
* comments:  AuditRecord服务实现层
* since Date： 2016-12-27 15:31:18
*/
@Service("auditRecordService")
public class AuditRecordServiceImpl extends LoggerService implements AuditRecordService{

    @Resource
    private AuditRecordDao auditRecordDao;

	/**
	* 多条件查询(分页)
	* @param auditRecordDO AuditRecordDO
	* @param pagination  Pagination
	* @return List<AuditRecordDO>
    */
    @Override
    public OptionsResponseDO<List<AuditRecordDO>> queryAuditRecordDOListForPage(AuditRecordDO auditRecordDO, Pagination pagination) throws BusinessException{
        try {
            int totalCount = auditRecordDao.selectCountByParams(auditRecordDO);
            if (totalCount <= 0) {
                return newStaticOptionsResponseDO(new ArrayList<AuditRecordDO>());
            }
            pagination.setTotalCount(totalCount);
            List<AuditRecordDO> auditRecordDOList = auditRecordDao.selectListByParams(auditRecordDO, pagination.getRowBounds());
            return newOptionsResponseDO(totalCount, auditRecordDOList, pagination.getPageSize(),pagination.getPP());
        } catch (Exception e) {
            throw new BusinessException("多条件查询AuditRecordDO信息异常",e);
        }
    }

    /**
    * 根据用户ID查询
    * @param id Long
    * @return ResponseDO<AuditRecordDO>
    */
    @Override
    @Cacheable(value = "common:auditRecordDO",key = "'common:auditRecordDO:id:' + #id")
    public ResponseDO<AuditRecordDO> getAuditRecordDOById(Long id) throws BusinessException{
        try {
                        Assert.isTrue(id != null,"查询Id不能为空!");
                        AuditRecordDO auditRecordDO = auditRecordDao.selectById(id);
            return newStaticResponseDO(auditRecordDO);
        } catch (Exception e) {
            throw new BusinessException("根据ID=>[" + id + "]查询AuditRecordDO信息异常",e);
        }
    }

    /**
    * 添加
    * @param auditRecordDO AuditRecordDO
    * @return ResponseDO<AuditRecordDO>
    */
    @Override
    @CachePut(value = "common:auditRecordDO",key = "'common:auditRecordDO:id:' + #auditRecordDO.id")
    public ResponseDO<AuditRecordDO> addAuditRecordDO(AuditRecordDO auditRecordDO) throws BusinessException{
        try {
            // Validate
            validateForAdd(auditRecordDO);

            // Insert
            int result = auditRecordDao.insert(auditRecordDO);
            if (result < 1) {
                throw new Exception("sql插入数据为0,请检查各项参数!");
            }
            logger.info("新增ID=>[" + auditRecordDO.getId() + "]的AuditRecordDO成功");
            return newStaticResponseDO(auditRecordDO);
        } catch (Exception e) {
            throw new BusinessException("新增ID=>[" + auditRecordDO.getId() + "]的AuditRecordDO信息异常",e);
        }
    }

    /**
    * Validate Add
    * @param auditRecordDO AuditRecordDO
    */
    private void validateForAdd(AuditRecordDO auditRecordDO) {
        Assert.isTrue(auditRecordDO != null,"auditRecordDO不能为空!");
        // TODO Validate
    }

    /**
    * 修改
    * @param auditRecordDO AuditRecordDO
	* @return ResponseDO<AuditRecordDO>
    */
    @Override
    @CacheEvict(value = "common:auditRecordDO",key = "'common:auditRecordDO:id:' + #auditRecordDO.id",beforeInvocation = true)
    public ResponseDO<AuditRecordDO> modifyAuditRecordDO(AuditRecordDO auditRecordDO) throws BusinessException{
        try {

            // validate
            AuditRecordDO oldAuditRecordDO = validateForUpdate(auditRecordDO);

            // Update
            auditRecordDO.setVersion(oldAuditRecordDO.getVersion());
            int result = auditRecordDao.updateById(auditRecordDO);
            if (result < 1) {
                throw new Exception("sql修改数据为0,请检查各项参数!");
            }
            logger.info("修改ID=>[" + auditRecordDO.getId() + "]的auditRecordDO成功!");
            return newStaticResponseDO(auditRecordDO);
        }catch (Exception e){
            logger.error("修改ID=>[" + auditRecordDO.getId() + "]的auditRecordDO异常!");
            throw new BusinessException("修改ID=>[" + auditRecordDO.getId() +"]的AuditRecordDO信息异常",e);
        }
    }

    /**
    * Validate Update
    * @param auditRecordDO AuditRecordDO
    * @return AuditRecordDO
    */
    private AuditRecordDO validateForUpdate(AuditRecordDO auditRecordDO) {

        Assert.isTrue(auditRecordDO != null,"auditRecordDO不能为空!");
                Assert.isTrue(auditRecordDO.getId() != null,"查询Id不能为空!");
                // TODO Validate
        AuditRecordDO oldAuditRecordDO = auditRecordDao.selectById(auditRecordDO.getId());
        Assert.isTrue(oldAuditRecordDO != null,"查询不到ID=>[" + auditRecordDO.getId() + "]的信息!");
        return oldAuditRecordDO;
    }

    /**
    * 删除(逻辑删除)
    * @param id Long
    * @return ResponseDO<AuditRecordDO>
    * @throws BusinessException
    */
    @Override
    @CacheEvict(value = "common:auditRecordDO",key = "'common:auditRecordDO:id:' + #id",beforeInvocation = true)
    public ResponseDO<AuditRecordDO> delAuditRecordDO(Long id) throws BusinessException{
        try {
            // validate
                        Assert.isTrue(id != null,"查询Id不能为空!");
            
            AuditRecordDO oldauditRecordDO = auditRecordDao.selectById(id);
            Assert.isTrue(oldauditRecordDO != null,"查询不到ID=>" + id + "的信息!");
            AuditRecordDO auditRecordDO = new AuditRecordDO();
            auditRecordDO.setId(id);
            auditRecordDO.setIsDeleted(1);
            auditRecordDO.setVersion(oldauditRecordDO.getVersion());
            // update
            int result = auditRecordDao.updateIsDeleteById(auditRecordDO);
            if (result < 1) {
                throw new Exception("数据已删除,请勿重复操作!");
            }
            logger.info("删除ID=>[" + id + "]的auditRecordDO成功!");
            return newStaticResponseDO(auditRecordDO);
        }catch (Exception e){
            logger.error("删除ID=>[" + id + "]的auditRecordDO异常!");
            throw new BusinessException("删除ID=>[" + id +"]的AuditRecordDO异常",e);
        }
    }
}
