package org.wsh.common.service.impl.blog;

import org.wsh.common.model.blog.BlogFavorsDO;
import org.wsh.common.dao.blog.BlogFavorsDao;
import org.wsh.common.service.api.blog.BlogFavorsService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.Assert;
import org.springframework.stereotype.Service;
import org.wsh.common.util.logger.LoggerService;
import org.springframework.transaction.annotation.Transactional;
import org.wsh.common.pager.pagination.Pagination;
import org.wsh.common.support.beans.OptionsResponseDO;
import org.wsh.common.support.exception.BusinessException;
import org.wsh.common.support.response.ResponseDO;
import javax.annotation.Resource;
import static org.wsh.common.support.beans.BeansUtil.newOptionsResponseDO;
import static org.wsh.common.support.beans.BeansUtil.newStaticOptionsResponseDO;
import static org.wsh.common.support.beans.BeansUtil.newStaticResponseDO;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  BlogFavors服务实现层
* since Date： 2017-02-07 16:06:55
*/
@Service("blogFavorsService")
public class BlogFavorsServiceImpl extends LoggerService implements BlogFavorsService{

    @Resource
    private BlogFavorsDao blogFavorsDao;

	/**
	* 多条件查询(分页)
	* @param blogFavorsDO BlogFavorsDO
	* @param pagination  Pagination
	* @return List<BlogFavorsDO>
    */
    @Override
    public OptionsResponseDO<List<BlogFavorsDO>> queryBlogFavorsDOListForPage(BlogFavorsDO blogFavorsDO, Pagination pagination) throws BusinessException{
        try {
            int totalCount = blogFavorsDao.selectCountByParams(blogFavorsDO);
            if (totalCount <= 0) {
                return newStaticOptionsResponseDO(new ArrayList<BlogFavorsDO>());
            }
            pagination.setTotalCount(totalCount);
            List<BlogFavorsDO> blogFavorsDOList = blogFavorsDao.selectListByParams(blogFavorsDO, pagination.getRowBounds());
            return newOptionsResponseDO(totalCount, blogFavorsDOList, pagination.getPageSize(),pagination.getPP());
        } catch (Exception e) {
            throw new BusinessException("多条件查询BlogFavorsDO信息异常",e);
        }
    }

    /**
    * 根据用户ID查询
    * @param id Long
    * @return ResponseDO<BlogFavorsDO>
    */
    @Override
    @Cacheable(value = "common:blogFavorsDO",key = "'common:blogFavorsDO:id:' + #id")
    public ResponseDO<BlogFavorsDO> getBlogFavorsDOById(Long id) throws BusinessException{
        try {
                        Assert.isTrue(id != null,"查询Id不能为空!");
                        BlogFavorsDO blogFavorsDO = blogFavorsDao.selectById(id);
            return newStaticResponseDO(blogFavorsDO);
        } catch (Exception e) {
            throw new BusinessException("根据ID=>[" + id + "]查询BlogFavorsDO信息异常",e);
        }
    }

    /**
    * 添加
    * @param blogFavorsDO BlogFavorsDO
    * @return ResponseDO<BlogFavorsDO>
    */
    @Override
    @CachePut(value = "common:blogFavorsDO",key = "'common:blogFavorsDO:id:' + #blogFavorsDO.id")
    public ResponseDO<BlogFavorsDO> addBlogFavorsDO(BlogFavorsDO blogFavorsDO) throws BusinessException{
        try {
            // Validate
            validateForAdd(blogFavorsDO);

            // Insert
            int result = blogFavorsDao.insert(blogFavorsDO);
            if (result < 1) {
                throw new Exception("sql插入数据为0,请检查各项参数!");
            }
            logger.info("新增ID=>[" + blogFavorsDO.getId() + "]的BlogFavorsDO成功");
            return newStaticResponseDO(blogFavorsDO);
        } catch (Exception e) {
            throw new BusinessException("新增ID=>[" + blogFavorsDO.getId() + "]的BlogFavorsDO信息异常",e);
        }
    }

    /**
    * Validate Add
    * @param blogFavorsDO BlogFavorsDO
    */
    private void validateForAdd(BlogFavorsDO blogFavorsDO) {
        Assert.isTrue(blogFavorsDO != null,"blogFavorsDO不能为空!");
        // TODO Validate
    }

    /**
    * 修改
    * @param blogFavorsDO BlogFavorsDO
	* @return ResponseDO<BlogFavorsDO>
    */
    @Override
    @CacheEvict(value = "common:blogFavorsDO",key = "'common:blogFavorsDO:id:' + #blogFavorsDO.id",beforeInvocation = true)
    public ResponseDO<BlogFavorsDO> modifyBlogFavorsDO(BlogFavorsDO blogFavorsDO) throws BusinessException{
        try {

            // validate
            BlogFavorsDO oldBlogFavorsDO = validateForUpdate(blogFavorsDO);

            // Update
            blogFavorsDO.setVersion(oldBlogFavorsDO.getVersion());
            int result = blogFavorsDao.updateById(blogFavorsDO);
            if (result < 1) {
                throw new Exception("sql修改数据为0,请检查各项参数!");
            }
            logger.info("修改ID=>[" + blogFavorsDO.getId() + "]的blogFavorsDO成功!");
            return newStaticResponseDO(blogFavorsDO);
        }catch (Exception e){
            logger.error("修改ID=>[" + blogFavorsDO.getId() + "]的blogFavorsDO异常!");
            throw new BusinessException("修改ID=>[" + blogFavorsDO.getId() +"]的BlogFavorsDO信息异常",e);
        }
    }

    /**
    * Validate Update
    * @param blogFavorsDO BlogFavorsDO
    * @return BlogFavorsDO
    */
    private BlogFavorsDO validateForUpdate(BlogFavorsDO blogFavorsDO) {

        Assert.isTrue(blogFavorsDO != null,"blogFavorsDO不能为空!");
                Assert.isTrue(blogFavorsDO.getId() != null,"查询Id不能为空!");
                // TODO Validate
        BlogFavorsDO oldBlogFavorsDO = blogFavorsDao.selectById(blogFavorsDO.getId());
        Assert.notNull(oldBlogFavorsDO,"查询不到ID=>[" + blogFavorsDO.getId() + "]的信息!");
        return oldBlogFavorsDO;
    }

    /**
    * 删除(逻辑删除)
    * @param id Long
    * @return ResponseDO<BlogFavorsDO>
    * @throws BusinessException
    */
    @Override
    @CacheEvict(value = "common:blogFavorsDO",key = "'common:blogFavorsDO:id:' + #id",beforeInvocation = true)
    public ResponseDO<BlogFavorsDO> delBlogFavorsDO(Long id) throws BusinessException{
        try {
            // validate
                        Assert.isTrue(id != null,"查询Id不能为空!");
            
            BlogFavorsDO oldblogFavorsDO = blogFavorsDao.selectById(id);
            Assert.isTrue(oldblogFavorsDO != null,"查询不到ID=>" + id + "的信息!");
            BlogFavorsDO blogFavorsDO = new BlogFavorsDO();
            blogFavorsDO.setId(id);
            blogFavorsDO.setVersion(oldblogFavorsDO.getVersion());
            // update
            int result = blogFavorsDao.updateIsDeleteById(blogFavorsDO);
            if (result < 1) {
                throw new Exception("数据已删除,请勿重复操作!");
            }
            logger.info("删除ID=>[" + id + "]的blogFavorsDO成功!");
            return newStaticResponseDO(blogFavorsDO);
        }catch (Exception e){
            logger.error("删除ID=>[" + id + "]的blogFavorsDO异常!");
            throw new BusinessException("删除ID=>[" + id +"]的BlogFavorsDO异常",e);
        }
    }
}
