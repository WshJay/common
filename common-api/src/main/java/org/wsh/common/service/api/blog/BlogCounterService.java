package org.wsh.common.service.api.blog;

import org.wsh.common.model.blog.BlogCounterDO;
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
* comments:  BlogCounter服务层
* since Date： 2017-02-08 16:57:38
*/
@Service
public interface BlogCounterService {

	/**
	* 多条件查询(分页)
	* @param blogCounterDO BlogCounterDO
	* @param pagination  Pagination
	* @return OptionsResponseDO<List<BlogCounterDO>>
    */
    public OptionsResponseDO<List<BlogCounterDO>> queryBlogCounterDOListForPage(BlogCounterDO blogCounterDO, Pagination pagination) throws BusinessException;

    /**
    * 根据用户ID查询
    * @param id Long
    * @return ResponseDO<BlogCounterDO>
    */
    public ResponseDO<BlogCounterDO> getBlogCounterDOById(Long id) throws BusinessException;

    /**
    * 添加
    * @param blogCounterDO BlogCounterDO
    * @return ResponseDO<BlogCounterDO>
    */
    public ResponseDO<BlogCounterDO> addBlogCounterDO(BlogCounterDO blogCounterDO) throws BusinessException;

    /**
    * 修改
    * @param blogCounterDO BlogCounterDO
	* @return ResponseDO<BlogCounterDO>
    */
    public ResponseDO<BlogCounterDO> modifyBlogCounterDO(BlogCounterDO blogCounterDO) throws BusinessException;

    /**
    * 删除(逻辑删除)
    * @param id Long
    * @return ResponseDO<BlogCounterDO>
    * @throws BusinessException
    */
    public ResponseDO<BlogCounterDO> delBlogCounterDO(Long id) throws BusinessException;

    /**
     * 根据BlogIds查询信息
     * @param idList List<Long>
     * @return ResponseDO<List<BlogCounterDO>>
     */
    public ResponseDO<List<BlogCounterDO>> queryBlogCounterDOListByBlogIds(List<Long> idList);

    /**
     * 增加浏览量
     * @param blogId Long
     * @return ResponseDO<BlogCounterDO>
     */
    public ResponseDO addViewNum(Long blogId);


    public ResponseDO<BlogCounterDO> queryBlogCounterDOByBlogId(Long blogId);
}
