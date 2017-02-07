package org.wsh.common.dao.blog;

import org.wsh.common.model.blog.BlogTagsDO;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  BlogTags持久层
* since Date： 2017-02-07 16:06:55
*/
@Repository
public interface BlogTagsDao {

	/**
	 * 根据ID查询表数据
	 * @param id Long
	 * @return BlogTagsDO
	 */
	BlogTagsDO selectById(Long id);

	/**
	* 多条件查询表信息
	* @param blogTagsDO BlogTagsDO
	* @param rowBounds RowBounds
	* @return List<BlogTagsDO>
	*/
	List<BlogTagsDO> selectListByParams(BlogTagsDO blogTagsDO, RowBounds rowBounds);
	int selectCountByParams(BlogTagsDO blogTagsDO);
	
	/**
	 * 插入信息
	 * @param blogTagsDO BlogTagsDO
	 * @return int
	 */
	int insert(BlogTagsDO blogTagsDO);

	/**
	* 根据ID更新信息
	* @param blogTagsDO BlogTagsDO
	* @return int
	*/
	int updateById(BlogTagsDO blogTagsDO);

	/**
	* 根据ID更新删除状态
	* @param blogTagsDO BlogTagsDO
	* @return int
	*/
	int updateIsDeleteById(BlogTagsDO blogTagsDO);
	
	/**
	 * 根据ID删除信息
	 * @param id Long
	 * @return int
	 */
	int deleteById(Long id);
	
	/**
	 * 批量添加信息
	 * @param blogTagsDOList List<BlogTagsDO>
	 */
	void insertList(List<BlogTagsDO> blogTagsDOList);
	
}
