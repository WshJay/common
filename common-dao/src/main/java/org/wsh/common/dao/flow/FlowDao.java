package org.wsh.common.dao.flow;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import org.wsh.common.model.flow.FlowDO;

import java.util.List;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  Flow持久层
* since Date： 2016-12-27 15:22:47
*/
@Repository
public interface FlowDao {

	/**
	 * 根据ID查询表数据
	 * @param id Long
	 * @return FlowDO
	 */
	FlowDO selectById(Long id);

	/**
	* 多条件查询表信息
	* @param flowDO FlowDO
	* @param rowBounds RowBounds
	* @return List<FlowDO>
	*/
	List<FlowDO> selectListByParams(FlowDO flowDO, RowBounds rowBounds);
	int selectCountByParams(FlowDO flowDO);
	
	/**
	 * 插入信息
	 * @param flowDO FlowDO
	 * @return int
	 */
	int insert(FlowDO flowDO);

	/**
	* 根据ID更新信息
	* @param flowDO FlowDO
	* @return int
	*/
	int updateById(FlowDO flowDO);

	/**
	* 根据ID更新删除状态
	* @param flowDO FlowDO
	* @return int
	*/
	int updateIsDeleteById(FlowDO flowDO);
	
	/**
	 * 根据ID删除信息
	 * @param id Long
	 * @return int
	 */
	int deleteById(Long id);
	
	/**
	 * 批量添加信息
	 * @param flowDOList List<FlowDO>
	 */
	void insertList(List<FlowDO> flowDOList);
	
}
