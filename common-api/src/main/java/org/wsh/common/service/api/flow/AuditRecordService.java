package org.wsh.common.service.api.flow;

import org.springframework.stereotype.Service;
import org.wsh.common.model.flow.AuditRecordDO;
import org.wsh.common.pager.pagination.Pagination;
import org.wsh.common.support.beans.OptionsResponseDO;
import org.wsh.common.support.exception.BusinessException;
import org.wsh.common.support.response.ResponseDO;
import java.util.List;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  AuditRecord服务层
* since Date： 2016-12-27 15:31:18
*/
@Service
public interface AuditRecordService {

	/**
	* 多条件查询(分页)
	* @param auditRecordDO AuditRecordDO
	* @param pagination  Pagination
	* @return OptionsResponseDO<List<AuditRecordDO>>
    */
    public OptionsResponseDO<List<AuditRecordDO>> queryAuditRecordDOListForPage(AuditRecordDO auditRecordDO, Pagination pagination) throws BusinessException;

    /**
    * 根据用户ID查询
    * @param id Long
    * @return ResponseDO<AuditRecordDO>
    */
    public ResponseDO<AuditRecordDO> getAuditRecordDOById(Long id) throws BusinessException;

    /**
    * 添加
    * @param auditRecordDO AuditRecordDO
    * @return ResponseDO<AuditRecordDO>
    */
    public ResponseDO<AuditRecordDO> addAuditRecordDO(AuditRecordDO auditRecordDO) throws BusinessException;

    /**
    * 修改
    * @param auditRecordDO AuditRecordDO
	* @return ResponseDO<AuditRecordDO>
    */
    public ResponseDO<AuditRecordDO> modifyAuditRecordDO(AuditRecordDO auditRecordDO) throws BusinessException;

    /**
    * 删除(逻辑删除)
    * @param id Long
    * @return ResponseDO<AuditRecordDO>
    * @throws BusinessException
    */
    public ResponseDO<AuditRecordDO> delAuditRecordDO(Long id) throws BusinessException;
}
