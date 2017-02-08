package org.wsh.common.dao.blog;

import org.wsh.common.model.blog.BlogCatalogDO;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  BlogCatalog持久层
* since Date： 2017-02-08 14:50:42
*/
@Repository
public interface BlogCatalogDao {

	/**
	 * 根据ID查询表数据
	 * @param id Long
	 * @return BlogCatalogDO
	 */
	BlogCatalogDO selectById(Long id);

	/**
	* 多条件查询表信息
	* @param blogCatalogDO BlogCatalogDO
	* @param rowBounds RowBounds
	* @return List<BlogCatalogDO>
	*/
	List<BlogCatalogDO> selectListByParams(BlogCatalogDO blogCatalogDO, RowBounds rowBounds);
	int selectCountByParams(BlogCatalogDO blogCatalogDO);
	
	/**
	 * 插入信息
	 * @param blogCatalogDO BlogCatalogDO
	 * @return int
	 */
	int insert(BlogCatalogDO blogCatalogDO);

	/**
	* 根据ID更新信息
	* @param blogCatalogDO BlogCatalogDO
	* @return int
	*/
	int updateById(BlogCatalogDO blogCatalogDO);

	/**
	* 根据ID更新删除状态
	* @param blogCatalogDO BlogCatalogDO
	* @return int
	*/
	int updateIsDeleteById(BlogCatalogDO blogCatalogDO);
	
	/**
	 * 根据ID删除信息
	 * @param id Long
	 * @return int
	 */
	int deleteById(Long id);
	
	/**
	 * 批量添加信息
	 * @param blogCatalogDOList List<BlogCatalogDO>
	 */
	void insertList(List<BlogCatalogDO> blogCatalogDOList);
	
}
