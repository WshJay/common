package org.wsh.common.dao.blog;

import org.wsh.common.model.blog.BlogFollowDO;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  BlogFollow持久层
* since Date： 2017-02-07 16:06:55
*/
@Repository
public interface BlogFollowDao {

	/**
	 * 根据ID查询表数据
	 * @param id Long
	 * @return BlogFollowDO
	 */
	BlogFollowDO selectById(Long id);

	/**
	* 多条件查询表信息
	* @param blogFollowDO BlogFollowDO
	* @param rowBounds RowBounds
	* @return List<BlogFollowDO>
	*/
	List<BlogFollowDO> selectListByParams(BlogFollowDO blogFollowDO, RowBounds rowBounds);
	int selectCountByParams(BlogFollowDO blogFollowDO);
	
	/**
	 * 插入信息
	 * @param blogFollowDO BlogFollowDO
	 * @return int
	 */
	int insert(BlogFollowDO blogFollowDO);

	/**
	* 根据ID更新信息
	* @param blogFollowDO BlogFollowDO
	* @return int
	*/
	int updateById(BlogFollowDO blogFollowDO);

	/**
	* 根据ID更新删除状态
	* @param blogFollowDO BlogFollowDO
	* @return int
	*/
	int updateIsDeleteById(BlogFollowDO blogFollowDO);
	
	/**
	 * 根据ID删除信息
	 * @param id Long
	 * @return int
	 */
	int deleteById(Long id);
	
	/**
	 * 批量添加信息
	 * @param blogFollowDOList List<BlogFollowDO>
	 */
	void insertList(List<BlogFollowDO> blogFollowDOList);
	
}
