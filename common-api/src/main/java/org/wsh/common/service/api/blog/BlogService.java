package org.wsh.common.service.api.blog;

import org.wsh.common.model.blog.BlogDO;
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
* comments:  Blog服务层
* since Date： 2017-02-07 16:06:54
*/
@Service
public interface BlogService {

	/**
	* 多条件查询(分页)
	* @param blogDO BlogDO
	* @param pagination  Pagination
	* @return OptionsResponseDO<List<BlogDO>>
    */
    public OptionsResponseDO<List<BlogDO>> queryBlogDOListForPage(BlogDO blogDO, Pagination pagination) throws BusinessException;

    /**
    * 根据用户ID查询
    * @param id Long
    * @return ResponseDO<BlogDO>
    */
    public ResponseDO<BlogDO> getBlogDOById(Long id) throws BusinessException;

    /**
    * 添加
    * @param blogDO BlogDO
    * @return ResponseDO<BlogDO>
    */
    public ResponseDO<BlogDO> addBlogDO(BlogDO blogDO) throws BusinessException;

    /**
    * 修改
    * @param blogDO BlogDO
	* @return ResponseDO<BlogDO>
    */
    public ResponseDO<BlogDO> modifyBlogDO(BlogDO blogDO) throws BusinessException;

    /**
    * 删除(逻辑删除)
    * @param id Long
    * @return ResponseDO<BlogDO>
    * @throws BusinessException
    */
    public ResponseDO<BlogDO> delBlogDO(Long id) throws BusinessException;
}
