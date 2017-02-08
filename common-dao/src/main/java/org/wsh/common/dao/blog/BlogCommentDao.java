package org.wsh.common.dao.blog;

import org.wsh.common.model.blog.BlogCommentDO;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  BlogComment持久层
* since Date： 2017-02-08 14:50:42
*/
@Repository
public interface BlogCommentDao {

	/**
	 * 根据ID查询表数据
	 * @param id Long
	 * @return BlogCommentDO
	 */
	BlogCommentDO selectById(Long id);

	/**
	* 多条件查询表信息
	* @param blogCommentDO BlogCommentDO
	* @param rowBounds RowBounds
	* @return List<BlogCommentDO>
	*/
	List<BlogCommentDO> selectListByParams(BlogCommentDO blogCommentDO, RowBounds rowBounds);
	int selectCountByParams(BlogCommentDO blogCommentDO);
	
	/**
	 * 插入信息
	 * @param blogCommentDO BlogCommentDO
	 * @return int
	 */
	int insert(BlogCommentDO blogCommentDO);

	/**
	* 根据ID更新信息
	* @param blogCommentDO BlogCommentDO
	* @return int
	*/
	int updateById(BlogCommentDO blogCommentDO);

	/**
	* 根据ID更新删除状态
	* @param blogCommentDO BlogCommentDO
	* @return int
	*/
	int updateIsDeleteById(BlogCommentDO blogCommentDO);
	
	/**
	 * 根据ID删除信息
	 * @param id Long
	 * @return int
	 */
	int deleteById(Long id);
	
	/**
	 * 批量添加信息
	 * @param blogCommentDOList List<BlogCommentDO>
	 */
	void insertList(List<BlogCommentDO> blogCommentDOList);
	
}
