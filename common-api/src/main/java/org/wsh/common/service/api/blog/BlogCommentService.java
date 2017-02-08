package org.wsh.common.service.api.blog;

import org.wsh.common.model.blog.BlogCommentDO;
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
* comments:  BlogComment服务层
* since Date： 2017-02-08 14:50:43
*/
@Service
public interface BlogCommentService {

	/**
	* 多条件查询(分页)
	* @param blogCommentDO BlogCommentDO
	* @param pagination  Pagination
	* @return OptionsResponseDO<List<BlogCommentDO>>
    */
    public OptionsResponseDO<List<BlogCommentDO>> queryBlogCommentDOListForPage(BlogCommentDO blogCommentDO, Pagination pagination) throws BusinessException;

    /**
    * 根据用户ID查询
    * @param id Long
    * @return ResponseDO<BlogCommentDO>
    */
    public ResponseDO<BlogCommentDO> getBlogCommentDOById(Long id) throws BusinessException;

    /**
    * 添加
    * @param blogCommentDO BlogCommentDO
    * @return ResponseDO<BlogCommentDO>
    */
    public ResponseDO<BlogCommentDO> addBlogCommentDO(BlogCommentDO blogCommentDO) throws BusinessException;

    /**
    * 修改
    * @param blogCommentDO BlogCommentDO
	* @return ResponseDO<BlogCommentDO>
    */
    public ResponseDO<BlogCommentDO> modifyBlogCommentDO(BlogCommentDO blogCommentDO) throws BusinessException;

    /**
    * 删除(逻辑删除)
    * @param id Long
    * @return ResponseDO<BlogCommentDO>
    * @throws BusinessException
    */
    public ResponseDO<BlogCommentDO> delBlogCommentDO(Long id) throws BusinessException;
}
