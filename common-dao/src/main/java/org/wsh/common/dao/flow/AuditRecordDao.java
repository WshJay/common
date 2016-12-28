package org.wsh.common.dao.flow;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import org.wsh.common.model.flow.AuditRecordDO;

import java.util.List;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  AuditRecord持久层
* since Date： 2016-12-27 15:22:47
*/
@Repository
public interface AuditRecordDao {

	/**
	 * 根据ID查询表数据
	 * @param id Long
	 * @return AuditRecordDO
	 */
	AuditRecordDO selectById(Long id);

	/**
	* 多条件查询表信息
	* @param auditRecordDO AuditRecordDO
	* @param rowBounds RowBounds
	* @return List<AuditRecordDO>
	*/
	List<AuditRecordDO> selectListByParams(AuditRecordDO auditRecordDO, RowBounds rowBounds);
	int selectCountByParams(AuditRecordDO auditRecordDO);
	
	/**
	 * 插入信息
	 * @param auditRecordDO AuditRecordDO
	 * @return int
	 */
	int insert(AuditRecordDO auditRecordDO);

	/**
	* 根据ID更新信息
	* @param auditRecordDO AuditRecordDO
	* @return int
	*/
	int updateById(AuditRecordDO auditRecordDO);

	/**
	* 根据ID更新删除状态
	* @param auditRecordDO AuditRecordDO
	* @return int
	*/
	int updateIsDeleteById(AuditRecordDO auditRecordDO);
	
	/**
	 * 根据ID删除信息
	 * @param id Long
	 * @return int
	 */
	int deleteById(Long id);
	
	/**
	 * 批量添加信息
	 * @param auditRecordDOList List<AuditRecordDO>
	 */
	void insertList(List<AuditRecordDO> auditRecordDOList);
	
}
