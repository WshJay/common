package org.wsh.common.dao.basic ;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import org.wsh.common.interceptor.MapParam;
import org.wsh.common.model.basic.UserBasicDO;

import java.util.List;
import java.util.Map;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  UserBasic持久层
* since Date： 2016-11-23 17:00:43
*/
@Repository
public interface UserBasicDao {

	/**
	 * 根据ID查询表数据
	 * @param id Long
	 * @return UserBasicDO
	 */
	UserBasicDO selectById(Long id);

	/**
	* 多条件查询表信息
	* @param userBasicDO UserBasicDO
	* @param rowBounds RowBounds
	* @return List<UserBasicDO>
	*/
	List<UserBasicDO> selectListByParams(UserBasicDO userBasicDO, RowBounds rowBounds);
	int selectCountByParams(UserBasicDO userBasicDO);
	
	/**
	 * 插入信息
	 * @param userBasicDO UserBasicDO
	 * @return int
	 */
	int insert(UserBasicDO userBasicDO);

	/**
	* 根据ID更新信息
	* @param userBasicDO UserBasicDO
	* @return int
	*/
	int updateById(UserBasicDO userBasicDO);

	/**
	* 根据ID更新删除状态
	* @param userBasicDO UserBasicDO
	* @return int
	*/
	int updateIsDeleteById(UserBasicDO userBasicDO);
	
	/**
	 * 根据ID删除信息
	 * @param id Long
	 * @return int
	 */
	int deleteById(Long id);
	
	/**
	 * 批量添加信息
	 * @param userBasicDOList List<UserBasicDO>
	 */
	void insertList(List<UserBasicDO> userBasicDOList);

	/**
	 * 根据ID查询用户名
	 * @param mapParam MapParam
	 * @return Map<Long,String>
	 */
	Map<Long,String> selectUserNameByIds(MapParam mapParam);
	
}
