package org.wsh.common.service.impl.flow;

import org.wsh.common.model.flow.FlowDO;
import org.wsh.common.dao.flow.FlowDao;
import org.wsh.common.service.api.flow.FlowService;
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
* comments:  Flow服务实现层
* since Date： 2016-12-27 15:31:18
*/
@Service("flowService")
public class FlowServiceImpl extends LoggerService implements FlowService{

    @Resource
    private FlowDao flowDao;

	/**
	* 多条件查询(分页)
	* @param flowDO FlowDO
	* @param pagination  Pagination
	* @return List<FlowDO>
    */
    @Override
    public OptionsResponseDO<List<FlowDO>> queryFlowDOListForPage(FlowDO flowDO, Pagination pagination) throws BusinessException{
        try {
            int totalCount = flowDao.selectCountByParams(flowDO);
            if (totalCount <= 0) {
                return newStaticOptionsResponseDO(new ArrayList<FlowDO>());
            }
            pagination.setTotalCount(totalCount);
            List<FlowDO> flowDOList = flowDao.selectListByParams(flowDO, pagination.getRowBounds());
            return newOptionsResponseDO(totalCount, flowDOList, pagination.getPageSize(),pagination.getPP());
        } catch (Exception e) {
            throw new BusinessException("多条件查询FlowDO信息异常",e);
        }
    }

    /**
    * 根据用户ID查询
    * @param id Long
    * @return ResponseDO<FlowDO>
    */
    @Override
    @Cacheable(value = "common:flowDO",key = "'common:flowDO:id:' + #id")
    public ResponseDO<FlowDO> getFlowDOById(Long id) throws BusinessException{
        try {
                        Assert.isTrue(id != null,"查询Id不能为空!");
                        FlowDO flowDO = flowDao.selectById(id);
            return newStaticResponseDO(flowDO);
        } catch (Exception e) {
            throw new BusinessException("根据ID=>[" + id + "]查询FlowDO信息异常",e);
        }
    }

    /**
    * 添加
    * @param flowDO FlowDO
    * @return ResponseDO<FlowDO>
    */
    @Override
    @CachePut(value = "common:flowDO",key = "'common:flowDO:id:' + #flowDO.id")
    public ResponseDO<FlowDO> addFlowDO(FlowDO flowDO) throws BusinessException{
        try {
            // Validate
            validateForAdd(flowDO);

            // Insert
            int result = flowDao.insert(flowDO);
            if (result < 1) {
                throw new Exception("sql插入数据为0,请检查各项参数!");
            }
            logger.info("新增ID=>[" + flowDO.getId() + "]的FlowDO成功");
            return newStaticResponseDO(flowDO);
        } catch (Exception e) {
            throw new BusinessException("新增ID=>[" + flowDO.getId() + "]的FlowDO信息异常",e);
        }
    }

    /**
    * Validate Add
    * @param flowDO FlowDO
    */
    private void validateForAdd(FlowDO flowDO) {
        Assert.isTrue(flowDO != null,"flowDO不能为空!");
        // TODO Validate
    }

    /**
    * 修改
    * @param flowDO FlowDO
	* @return ResponseDO<FlowDO>
    */
    @Override
    @CacheEvict(value = "common:flowDO",key = "'common:flowDO:id:' + #flowDO.id",beforeInvocation = true)
    public ResponseDO<FlowDO> modifyFlowDO(FlowDO flowDO) throws BusinessException{
        try {

            // validate
            FlowDO oldFlowDO = validateForUpdate(flowDO);
            flowDO.setVersion(oldFlowDO.getVersion());
//            Thread.sleep(10000);
//            int i = 1/0;
            // Update
            int result = flowDao.updateById(flowDO);
            if (result < 1) {
                throw new Exception("sql修改数据为0,请检查各项参数!");
            }
            logger.info("修改ID=>[" + flowDO.getId() + "]的flowDO成功!");
            return newStaticResponseDO(flowDO);
        }catch (Exception e){
            logger.error("修改ID=>[" + flowDO.getId() + "]的flowDO异常!");
            throw new BusinessException("修改ID=>[" + flowDO.getId() +"]的FlowDO信息异常",e);
        }
    }

    /**
    * Validate Update
    * @param flowDO FlowDO
    * @return FlowDO
    */
    private FlowDO validateForUpdate(FlowDO flowDO) {

        Assert.isTrue(flowDO != null,"flowDO不能为空!");
                Assert.isTrue(flowDO.getId() != null,"查询Id不能为空!");
                // TODO Validate
        FlowDO oldFlowDO = flowDao.selectById(flowDO.getId());
        Assert.notNull(oldFlowDO,"查询不到ID=>[" + flowDO.getId() + "]的信息!");
        return oldFlowDO;
    }

    /**
    * 删除(逻辑删除)
    * @param id Long
    * @return ResponseDO<FlowDO>
    * @throws BusinessException
    */
    @Override
    @CacheEvict(value = "common:flowDO",key = "'common:flowDO:id:' + #id",beforeInvocation = true)
    public ResponseDO<FlowDO> delFlowDO(Long id) throws BusinessException{
        try {
            // validate
                        Assert.isTrue(id != null,"查询Id不能为空!");
            
            FlowDO oldflowDO = flowDao.selectById(id);
            Assert.isTrue(oldflowDO != null,"查询不到ID=>" + id + "的信息!");
            FlowDO flowDO = new FlowDO();
            flowDO.setId(id);
            flowDO.setIsDeleted(1);
            // update
            int result = flowDao.updateIsDeleteById(flowDO);
            if (result < 1) {
                throw new Exception("数据已删除,请勿重复操作!");
            }
            logger.info("删除ID=>[" + id + "]的flowDO成功!");
            return newStaticResponseDO(flowDO);
        }catch (Exception e){
            logger.error("删除ID=>[" + id + "]的flowDO异常!");
            throw new BusinessException("删除ID=>[" + id +"]的FlowDO异常",e);
        }
    }
}
