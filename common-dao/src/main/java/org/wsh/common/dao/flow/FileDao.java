package org.wsh.common.dao.flow;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import org.wsh.common.model.flow.FileDO;

import java.util.List;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  File持久层
* since Date： 2016-12-27 15:22:47
*/
@Repository
public interface FileDao {

	/**
	 * 根据ID查询表数据
	 * @param id Long
	 * @return FileDO
	 */
	FileDO selectById(Long id);

	/**
	* 多条件查询表信息
	* @param fileDO FileDO
	* @param rowBounds RowBounds
	* @return List<FileDO>
	*/
	List<FileDO> selectListByParams(FileDO fileDO, RowBounds rowBounds);
	int selectCountByParams(FileDO fileDO);
	
	/**
	 * 插入信息
	 * @param fileDO FileDO
	 * @return int
	 */
	int insert(FileDO fileDO);

	/**
	* 根据ID更新信息
	* @param fileDO FileDO
	* @return int
	*/
	int updateById(FileDO fileDO);

	/**
	* 根据ID更新删除状态
	* @param fileDO FileDO
	* @return int
	*/
	int updateIsDeleteById(FileDO fileDO);
	
	/**
	 * 根据ID删除信息
	 * @param id Long
	 * @return int
	 */
	int deleteById(Long id);
	
	/**
	 * 批量添加信息
	 * @param fileDOList List<FileDO>
	 */
	void insertList(List<FileDO> fileDOList);
	
}
