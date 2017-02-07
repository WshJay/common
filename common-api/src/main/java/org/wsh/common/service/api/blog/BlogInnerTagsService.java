package org.wsh.common.service.api.blog;

import org.wsh.common.model.blog.BlogInnerTagsDO;
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
* comments:  BlogInnerTags服务层
* since Date： 2017-02-07 16:06:55
*/
@Service
public interface BlogInnerTagsService {

	/**
	* 多条件查询(分页)
	* @param blogInnerTagsDO BlogInnerTagsDO
	* @param pagination  Pagination
	* @return OptionsResponseDO<List<BlogInnerTagsDO>>
    */
    public OptionsResponseDO<List<BlogInnerTagsDO>> queryBlogInnerTagsDOListForPage(BlogInnerTagsDO blogInnerTagsDO, Pagination pagination) throws BusinessException;

    /**
    * 根据用户ID查询
    * @param id Long
    * @return ResponseDO<BlogInnerTagsDO>
    */
    public ResponseDO<BlogInnerTagsDO> getBlogInnerTagsDOById(Long id) throws BusinessException;

    /**
    * 添加
    * @param blogInnerTagsDO BlogInnerTagsDO
    * @return ResponseDO<BlogInnerTagsDO>
    */
    public ResponseDO<BlogInnerTagsDO> addBlogInnerTagsDO(BlogInnerTagsDO blogInnerTagsDO) throws BusinessException;

    /**
    * 修改
    * @param blogInnerTagsDO BlogInnerTagsDO
	* @return ResponseDO<BlogInnerTagsDO>
    */
    public ResponseDO<BlogInnerTagsDO> modifyBlogInnerTagsDO(BlogInnerTagsDO blogInnerTagsDO) throws BusinessException;

    /**
    * 删除(逻辑删除)
    * @param id Long
    * @return ResponseDO<BlogInnerTagsDO>
    * @throws BusinessException
    */
    public ResponseDO<BlogInnerTagsDO> delBlogInnerTagsDO(Long id) throws BusinessException;
}
