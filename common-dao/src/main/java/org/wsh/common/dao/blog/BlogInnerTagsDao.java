package org.wsh.common.dao.blog;

import org.wsh.common.model.blog.BlogInnerTagsDO;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  BlogInnerTags持久层
* since Date： 2017-02-07 16:06:55
*/
@Repository
public interface BlogInnerTagsDao {

	/**
	 * 根据ID查询表数据
	 * @param id Long
	 * @return BlogInnerTagsDO
	 */
	BlogInnerTagsDO selectById(Long id);

	/**
	* 多条件查询表信息
	* @param blogInnerTagsDO BlogInnerTagsDO
	* @param rowBounds RowBounds
	* @return List<BlogInnerTagsDO>
	*/
	List<BlogInnerTagsDO> selectListByParams(BlogInnerTagsDO blogInnerTagsDO, RowBounds rowBounds);
	int selectCountByParams(BlogInnerTagsDO blogInnerTagsDO);
	
	/**
	 * 插入信息
	 * @param blogInnerTagsDO BlogInnerTagsDO
	 * @return int
	 */
	int insert(BlogInnerTagsDO blogInnerTagsDO);

	/**
	* 根据ID更新信息
	* @param blogInnerTagsDO BlogInnerTagsDO
	* @return int
	*/
	int updateById(BlogInnerTagsDO blogInnerTagsDO);

	/**
	* 根据ID更新删除状态
	* @param blogInnerTagsDO BlogInnerTagsDO
	* @return int
	*/
	int updateIsDeleteById(BlogInnerTagsDO blogInnerTagsDO);
	
	/**
	 * 根据ID删除信息
	 * @param id Long
	 * @return int
	 */
	int deleteById(Long id);
	
	/**
	 * 批量添加信息
	 * @param blogInnerTagsDOList List<BlogInnerTagsDO>
	 */
	void insertList(List<BlogInnerTagsDO> blogInnerTagsDOList);
	
}
