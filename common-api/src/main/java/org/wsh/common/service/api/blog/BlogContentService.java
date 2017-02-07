package org.wsh.common.service.api.blog;

import org.wsh.common.model.blog.BlogContentDO;
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
* comments:  BlogContent服务层
* since Date： 2017-02-07 16:06:55
*/
@Service
public interface BlogContentService {

	/**
	* 多条件查询(分页)
	* @param blogContentDO BlogContentDO
	* @param pagination  Pagination
	* @return OptionsResponseDO<List<BlogContentDO>>
    */
    public OptionsResponseDO<List<BlogContentDO>> queryBlogContentDOListForPage(BlogContentDO blogContentDO, Pagination pagination) throws BusinessException;

    /**
    * 根据用户ID查询
    * @param id Long
    * @return ResponseDO<BlogContentDO>
    */
    public ResponseDO<BlogContentDO> getBlogContentDOById(Long id) throws BusinessException;

    /**
    * 添加
    * @param blogContentDO BlogContentDO
    * @return ResponseDO<BlogContentDO>
    */
    public ResponseDO<BlogContentDO> addBlogContentDO(BlogContentDO blogContentDO) throws BusinessException;

    /**
    * 修改
    * @param blogContentDO BlogContentDO
	* @return ResponseDO<BlogContentDO>
    */
    public ResponseDO<BlogContentDO> modifyBlogContentDO(BlogContentDO blogContentDO) throws BusinessException;

    /**
    * 删除(逻辑删除)
    * @param id Long
    * @return ResponseDO<BlogContentDO>
    * @throws BusinessException
    */
    public ResponseDO<BlogContentDO> delBlogContentDO(Long id) throws BusinessException;
}
