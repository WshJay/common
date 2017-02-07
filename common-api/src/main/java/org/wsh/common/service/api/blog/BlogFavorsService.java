package org.wsh.common.service.api.blog;

import org.wsh.common.model.blog.BlogFavorsDO;
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
* comments:  BlogFavors服务层
* since Date： 2017-02-07 16:06:55
*/
@Service
public interface BlogFavorsService {

	/**
	* 多条件查询(分页)
	* @param blogFavorsDO BlogFavorsDO
	* @param pagination  Pagination
	* @return OptionsResponseDO<List<BlogFavorsDO>>
    */
    public OptionsResponseDO<List<BlogFavorsDO>> queryBlogFavorsDOListForPage(BlogFavorsDO blogFavorsDO, Pagination pagination) throws BusinessException;

    /**
    * 根据用户ID查询
    * @param id Long
    * @return ResponseDO<BlogFavorsDO>
    */
    public ResponseDO<BlogFavorsDO> getBlogFavorsDOById(Long id) throws BusinessException;

    /**
    * 添加
    * @param blogFavorsDO BlogFavorsDO
    * @return ResponseDO<BlogFavorsDO>
    */
    public ResponseDO<BlogFavorsDO> addBlogFavorsDO(BlogFavorsDO blogFavorsDO) throws BusinessException;

    /**
    * 修改
    * @param blogFavorsDO BlogFavorsDO
	* @return ResponseDO<BlogFavorsDO>
    */
    public ResponseDO<BlogFavorsDO> modifyBlogFavorsDO(BlogFavorsDO blogFavorsDO) throws BusinessException;

    /**
    * 删除(逻辑删除)
    * @param id Long
    * @return ResponseDO<BlogFavorsDO>
    * @throws BusinessException
    */
    public ResponseDO<BlogFavorsDO> delBlogFavorsDO(Long id) throws BusinessException;
}
