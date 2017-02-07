package org.wsh.common.dao.blog;

import org.wsh.common.model.blog.BlogContentDO;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  BlogContent持久层
* since Date： 2017-02-07 16:06:54
*/
@Repository
public interface BlogContentDao {

	/**
	 * 根据ID查询表数据
	 * @param id Long
	 * @return BlogContentDO
	 */
	BlogContentDO selectById(Long id);

	/**
	* 多条件查询表信息
	* @param blogContentDO BlogContentDO
	* @param rowBounds RowBounds
	* @return List<BlogContentDO>
	*/
	List<BlogContentDO> selectListByParams(BlogContentDO blogContentDO, RowBounds rowBounds);
	int selectCountByParams(BlogContentDO blogContentDO);
	
	/**
	 * 插入信息
	 * @param blogContentDO BlogContentDO
	 * @return int
	 */
	int insert(BlogContentDO blogContentDO);

	/**
	* 根据ID更新信息
	* @param blogContentDO BlogContentDO
	* @return int
	*/
	int updateById(BlogContentDO blogContentDO);

	/**
	* 根据ID更新删除状态
	* @param blogContentDO BlogContentDO
	* @return int
	*/
	int updateIsDeleteById(BlogContentDO blogContentDO);
	
	/**
	 * 根据ID删除信息
	 * @param id Long
	 * @return int
	 */
	int deleteById(Long id);
	
	/**
	 * 批量添加信息
	 * @param blogContentDOList List<BlogContentDO>
	 */
	void insertList(List<BlogContentDO> blogContentDOList);
	
}
