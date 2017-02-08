package org.wsh.common.service.api.blog;

import org.wsh.common.model.blog.BlogCatalogDO;
import org.springframework.stereotype.Service;
import org.wsh.common.pager.pagination.Pagination;
import org.wsh.common.support.beans.OptionsResponseDO;
import org.wsh.common.support.exception.BusinessException;
import org.wsh.common.support.response.ResponseDO;
import static org.wsh.common.support.beans.BeansUtil.newOptionsResponseDO;
import java.util.List;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  BlogCatalog服务层
* since Date： 2017-02-08 14:50:42
*/
@Service
public interface BlogCatalogService {

	/**
	* 多条件查询(分页)
	* @param blogCatalogDO BlogCatalogDO
	* @param pagination  Pagination
	* @return OptionsResponseDO<List<BlogCatalogDO>>
    */
    public OptionsResponseDO<List<BlogCatalogDO>> queryBlogCatalogDOListForPage(BlogCatalogDO blogCatalogDO, Pagination pagination) throws BusinessException;

    /**
    * 根据用户ID查询
    * @param id Long
    * @return ResponseDO<BlogCatalogDO>
    */
    public ResponseDO<BlogCatalogDO> getBlogCatalogDOById(Long id) throws BusinessException;

    /**
    * 添加
    * @param blogCatalogDO BlogCatalogDO
    * @return ResponseDO<BlogCatalogDO>
    */
    public ResponseDO<BlogCatalogDO> addBlogCatalogDO(BlogCatalogDO blogCatalogDO) throws BusinessException;

    /**
    * 修改
    * @param blogCatalogDO BlogCatalogDO
	* @return ResponseDO<BlogCatalogDO>
    */
    public ResponseDO<BlogCatalogDO> modifyBlogCatalogDO(BlogCatalogDO blogCatalogDO) throws BusinessException;

    /**
    * 删除(逻辑删除)
    * @param id Long
    * @return ResponseDO<BlogCatalogDO>
    * @throws BusinessException
    */
    public ResponseDO<BlogCatalogDO> delBlogCatalogDO(Long id) throws BusinessException;
}
