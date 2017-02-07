package org.wsh.common.dao.blog;

import org.wsh.common.model.blog.BlogDO;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  Blog持久层
* since Date： 2017-02-07 16:06:54
*/
@Repository
public interface BlogDao {

	/**
	 * 根据ID查询表数据
	 * @param id Long
	 * @return BlogDO
	 */
	BlogDO selectById(Long id);

	/**
	* 多条件查询表信息
	* @param blogDO BlogDO
	* @param rowBounds RowBounds
	* @return List<BlogDO>
	*/
	List<BlogDO> selectListByParams(BlogDO blogDO, RowBounds rowBounds);
	int selectCountByParams(BlogDO blogDO);
	
	/**
	 * 插入信息
	 * @param blogDO BlogDO
	 * @return int
	 */
	int insert(BlogDO blogDO);

	/**
	* 根据ID更新信息
	* @param blogDO BlogDO
	* @return int
	*/
	int updateById(BlogDO blogDO);

	/**
	* 根据ID更新删除状态
	* @param blogDO BlogDO
	* @return int
	*/
	int updateIsDeleteById(BlogDO blogDO);
	
	/**
	 * 根据ID删除信息
	 * @param id Long
	 * @return int
	 */
	int deleteById(Long id);
	
	/**
	 * 批量添加信息
	 * @param blogDOList List<BlogDO>
	 */
	void insertList(List<BlogDO> blogDOList);
	
}
