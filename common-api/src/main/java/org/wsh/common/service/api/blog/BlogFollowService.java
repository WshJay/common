package org.wsh.common.service.api.blog;

import org.wsh.common.model.blog.BlogFollowDO;
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
* comments:  BlogFollow服务层
* since Date： 2017-02-07 16:06:55
*/
@Service
public interface BlogFollowService {

	/**
	* 多条件查询(分页)
	* @param blogFollowDO BlogFollowDO
	* @param pagination  Pagination
	* @return OptionsResponseDO<List<BlogFollowDO>>
    */
    public OptionsResponseDO<List<BlogFollowDO>> queryBlogFollowDOListForPage(BlogFollowDO blogFollowDO, Pagination pagination) throws BusinessException;

    /**
    * 根据用户ID查询
    * @param id Long
    * @return ResponseDO<BlogFollowDO>
    */
    public ResponseDO<BlogFollowDO> getBlogFollowDOById(Long id) throws BusinessException;

    /**
    * 添加
    * @param blogFollowDO BlogFollowDO
    * @return ResponseDO<BlogFollowDO>
    */
    public ResponseDO<BlogFollowDO> addBlogFollowDO(BlogFollowDO blogFollowDO) throws BusinessException;

    /**
    * 修改
    * @param blogFollowDO BlogFollowDO
	* @return ResponseDO<BlogFollowDO>
    */
    public ResponseDO<BlogFollowDO> modifyBlogFollowDO(BlogFollowDO blogFollowDO) throws BusinessException;

    /**
    * 删除(逻辑删除)
    * @param id Long
    * @return ResponseDO<BlogFollowDO>
    * @throws BusinessException
    */
    public ResponseDO<BlogFollowDO> delBlogFollowDO(Long id) throws BusinessException;
}
