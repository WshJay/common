package org.wsh.common.service.api.blog;

import org.wsh.common.model.blog.BlogTagsDO;
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
* comments:  BlogTags服务层
* since Date： 2017-02-07 16:06:55
*/
@Service
public interface BlogTagsService {

	/**
	* 多条件查询(分页)
	* @param blogTagsDO BlogTagsDO
	* @param pagination  Pagination
	* @return OptionsResponseDO<List<BlogTagsDO>>
    */
    public OptionsResponseDO<List<BlogTagsDO>> queryBlogTagsDOListForPage(BlogTagsDO blogTagsDO, Pagination pagination) throws BusinessException;

    /**
    * 根据用户ID查询
    * @param id Long
    * @return ResponseDO<BlogTagsDO>
    */
    public ResponseDO<BlogTagsDO> getBlogTagsDOById(Long id) throws BusinessException;

    /**
    * 添加
    * @param blogTagsDO BlogTagsDO
    * @return ResponseDO<BlogTagsDO>
    */
    public ResponseDO<BlogTagsDO> addBlogTagsDO(BlogTagsDO blogTagsDO) throws BusinessException;

    /**
    * 修改
    * @param blogTagsDO BlogTagsDO
	* @return ResponseDO<BlogTagsDO>
    */
    public ResponseDO<BlogTagsDO> modifyBlogTagsDO(BlogTagsDO blogTagsDO) throws BusinessException;

    /**
    * 删除(逻辑删除)
    * @param id Long
    * @return ResponseDO<BlogTagsDO>
    * @throws BusinessException
    */
    public ResponseDO<BlogTagsDO> delBlogTagsDO(Long id) throws BusinessException;
}
