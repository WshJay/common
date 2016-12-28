package org.wsh.common.service.api.flow;

import org.wsh.common.model.flow.FlowDO;
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
* comments:  Flow服务层
* since Date： 2016-12-27 15:31:18
*/
@Service
public interface FlowService {

	/**
	* 多条件查询(分页)
	* @param flowDO FlowDO
	* @param pagination  Pagination
	* @return OptionsResponseDO<List<FlowDO>>
    */
    public OptionsResponseDO<List<FlowDO>> queryFlowDOListForPage(FlowDO flowDO, Pagination pagination) throws BusinessException;

    /**
    * 根据用户ID查询
    * @param id Long
    * @return ResponseDO<FlowDO>
    */
    public ResponseDO<FlowDO> getFlowDOById(Long id) throws BusinessException;

    /**
    * 添加
    * @param flowDO FlowDO
    * @return ResponseDO<FlowDO>
    */
    public ResponseDO<FlowDO> addFlowDO(FlowDO flowDO) throws BusinessException;

    /**
    * 修改
    * @param flowDO FlowDO
	* @return ResponseDO<FlowDO>
    */
    public ResponseDO<FlowDO> modifyFlowDO(FlowDO flowDO) throws BusinessException;

    /**
    * 删除(逻辑删除)
    * @param id Long
    * @return ResponseDO<FlowDO>
    * @throws BusinessException
    */
    public ResponseDO<FlowDO> delFlowDO(Long id) throws BusinessException;
}
