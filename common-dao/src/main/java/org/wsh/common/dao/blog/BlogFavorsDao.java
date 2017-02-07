package org.wsh.common.dao.blog;

import org.wsh.common.model.blog.BlogFavorsDO;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  BlogFavors持久层
* since Date： 2017-02-07 16:06:55
*/
@Repository
public interface BlogFavorsDao {

	/**
	 * 根据ID查询表数据
	 * @param id Long
	 * @return BlogFavorsDO
	 */
	BlogFavorsDO selectById(Long id);

	/**
	* 多条件查询表信息
	* @param blogFavorsDO BlogFavorsDO
	* @param rowBounds RowBounds
	* @return List<BlogFavorsDO>
	*/
	List<BlogFavorsDO> selectListByParams(BlogFavorsDO blogFavorsDO, RowBounds rowBounds);
	int selectCountByParams(BlogFavorsDO blogFavorsDO);
	
	/**
	 * 插入信息
	 * @param blogFavorsDO BlogFavorsDO
	 * @return int
	 */
	int insert(BlogFavorsDO blogFavorsDO);

	/**
	* 根据ID更新信息
	* @param blogFavorsDO BlogFavorsDO
	* @return int
	*/
	int updateById(BlogFavorsDO blogFavorsDO);

	/**
	* 根据ID更新删除状态
	* @param blogFavorsDO BlogFavorsDO
	* @return int
	*/
	int updateIsDeleteById(BlogFavorsDO blogFavorsDO);
	
	/**
	 * 根据ID删除信息
	 * @param id Long
	 * @return int
	 */
	int deleteById(Long id);
	
	/**
	 * 批量添加信息
	 * @param blogFavorsDOList List<BlogFavorsDO>
	 */
	void insertList(List<BlogFavorsDO> blogFavorsDOList);
	
}
