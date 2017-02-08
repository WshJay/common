package org.wsh.common.service.api.blog;

import org.wsh.common.model.blog.BlogPraiseDO;
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
* comments:  BlogPraise服务层
* since Date： 2017-02-08 14:50:43
*/
@Service
public interface BlogPraiseService {

	/**
	* 多条件查询(分页)
	* @param blogPraiseDO BlogPraiseDO
	* @param pagination  Pagination
	* @return OptionsResponseDO<List<BlogPraiseDO>>
    */
    public OptionsResponseDO<List<BlogPraiseDO>> queryBlogPraiseDOListForPage(BlogPraiseDO blogPraiseDO, Pagination pagination) throws BusinessException;

    /**
    * 根据用户ID查询
    * @param id Long
    * @return ResponseDO<BlogPraiseDO>
    */
    public ResponseDO<BlogPraiseDO> getBlogPraiseDOById(Long id) throws BusinessException;

    /**
    * 添加
    * @param blogPraiseDO BlogPraiseDO
    * @return ResponseDO<BlogPraiseDO>
    */
    public ResponseDO<BlogPraiseDO> addBlogPraiseDO(BlogPraiseDO blogPraiseDO) throws BusinessException;

    /**
    * 修改
    * @param blogPraiseDO BlogPraiseDO
	* @return ResponseDO<BlogPraiseDO>
    */
    public ResponseDO<BlogPraiseDO> modifyBlogPraiseDO(BlogPraiseDO blogPraiseDO) throws BusinessException;

    /**
    * 删除(逻辑删除)
    * @param id Long
    * @return ResponseDO<BlogPraiseDO>
    * @throws BusinessException
    */
    public ResponseDO<BlogPraiseDO> delBlogPraiseDO(Long id) throws BusinessException;
}
