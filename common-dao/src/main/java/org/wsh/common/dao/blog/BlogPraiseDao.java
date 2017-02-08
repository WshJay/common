package org.wsh.common.dao.blog;

import org.wsh.common.model.blog.BlogPraiseDO;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  BlogPraise持久层
* since Date： 2017-02-08 14:50:43
*/
@Repository
public interface BlogPraiseDao {

	/**
	 * 根据ID查询表数据
	 * @param id Long
	 * @return BlogPraiseDO
	 */
	BlogPraiseDO selectById(Long id);

	/**
	* 多条件查询表信息
	* @param blogPraiseDO BlogPraiseDO
	* @param rowBounds RowBounds
	* @return List<BlogPraiseDO>
	*/
	List<BlogPraiseDO> selectListByParams(BlogPraiseDO blogPraiseDO, RowBounds rowBounds);
	int selectCountByParams(BlogPraiseDO blogPraiseDO);
	
	/**
	 * 插入信息
	 * @param blogPraiseDO BlogPraiseDO
	 * @return int
	 */
	int insert(BlogPraiseDO blogPraiseDO);

	/**
	* 根据ID更新信息
	* @param blogPraiseDO BlogPraiseDO
	* @return int
	*/
	int updateById(BlogPraiseDO blogPraiseDO);

	/**
	* 根据ID更新删除状态
	* @param blogPraiseDO BlogPraiseDO
	* @return int
	*/
	int updateIsDeleteById(BlogPraiseDO blogPraiseDO);
	
	/**
	 * 根据ID删除信息
	 * @param id Long
	 * @return int
	 */
	int deleteById(Long id);
	
	/**
	 * 批量添加信息
	 * @param blogPraiseDOList List<BlogPraiseDO>
	 */
	void insertList(List<BlogPraiseDO> blogPraiseDOList);
	
}
