package org.wsh.common.service.impl.blog;

import org.wsh.common.model.blog.BlogTagsDO;
import org.wsh.common.dao.blog.BlogTagsDao;
import org.wsh.common.service.api.blog.BlogTagsService;
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
* comments:  BlogTags服务实现层
* since Date： 2017-02-07 16:06:55
*/
@Service("blogTagsService")
public class BlogTagsServiceImpl extends LoggerService implements BlogTagsService{

    @Resource
    private BlogTagsDao blogTagsDao;

	/**
	* 多条件查询(分页)
	* @param blogTagsDO BlogTagsDO
	* @param pagination  Pagination
	* @return List<BlogTagsDO>
    */
    @Override
    public OptionsResponseDO<List<BlogTagsDO>> queryBlogTagsDOListForPage(BlogTagsDO blogTagsDO, Pagination pagination) throws BusinessException{
        try {
            int totalCount = blogTagsDao.selectCountByParams(blogTagsDO);
            if (totalCount <= 0) {
                return newStaticOptionsResponseDO(new ArrayList<BlogTagsDO>());
            }
            pagination.setTotalCount(totalCount);
            List<BlogTagsDO> blogTagsDOList = blogTagsDao.selectListByParams(blogTagsDO, pagination.getRowBounds());
            return newOptionsResponseDO(totalCount, blogTagsDOList, pagination.getPageSize(),pagination.getPP());
        } catch (Exception e) {
            throw new BusinessException("多条件查询BlogTagsDO信息异常",e);
        }
    }

    /**
    * 根据用户ID查询
    * @param id Long
    * @return ResponseDO<BlogTagsDO>
    */
    @Override
    @Cacheable(value = "common:blogTagsDO",key = "'common:blogTagsDO:id:' + #id")
    public ResponseDO<BlogTagsDO> getBlogTagsDOById(Long id) throws BusinessException{
        try {
                        Assert.isTrue(id != null,"查询Id不能为空!");
                        BlogTagsDO blogTagsDO = blogTagsDao.selectById(id);
            return newStaticResponseDO(blogTagsDO);
        } catch (Exception e) {
            throw new BusinessException("根据ID=>[" + id + "]查询BlogTagsDO信息异常",e);
        }
    }

    /**
    * 添加
    * @param blogTagsDO BlogTagsDO
    * @return ResponseDO<BlogTagsDO>
    */
    @Override
    @CachePut(value = "common:blogTagsDO",key = "'common:blogTagsDO:id:' + #blogTagsDO.id")
    public ResponseDO<BlogTagsDO> addBlogTagsDO(BlogTagsDO blogTagsDO) throws BusinessException{
        try {
            // Validate
            validateForAdd(blogTagsDO);

            // Insert
            int result = blogTagsDao.insert(blogTagsDO);
            if (result < 1) {
                throw new Exception("sql插入数据为0,请检查各项参数!");
            }
            logger.info("新增ID=>[" + blogTagsDO.getId() + "]的BlogTagsDO成功");
            return newStaticResponseDO(blogTagsDO);
        } catch (Exception e) {
            throw new BusinessException("新增ID=>[" + blogTagsDO.getId() + "]的BlogTagsDO信息异常",e);
        }
    }

    /**
    * Validate Add
    * @param blogTagsDO BlogTagsDO
    */
    private void validateForAdd(BlogTagsDO blogTagsDO) {
        Assert.isTrue(blogTagsDO != null,"blogTagsDO不能为空!");
        // TODO Validate
    }

    /**
    * 修改
    * @param blogTagsDO BlogTagsDO
	* @return ResponseDO<BlogTagsDO>
    */
    @Override
    @CacheEvict(value = "common:blogTagsDO",key = "'common:blogTagsDO:id:' + #blogTagsDO.id",beforeInvocation = true)
    public ResponseDO<BlogTagsDO> modifyBlogTagsDO(BlogTagsDO blogTagsDO) throws BusinessException{
        try {

            // validate
            BlogTagsDO oldBlogTagsDO = validateForUpdate(blogTagsDO);

            // Update
            blogTagsDO.setVersion(oldBlogTagsDO.getVersion());
            int result = blogTagsDao.updateById(blogTagsDO);
            if (result < 1) {
                throw new Exception("sql修改数据为0,请检查各项参数!");
            }
            logger.info("修改ID=>[" + blogTagsDO.getId() + "]的blogTagsDO成功!");
            return newStaticResponseDO(blogTagsDO);
        }catch (Exception e){
            logger.error("修改ID=>[" + blogTagsDO.getId() + "]的blogTagsDO异常!");
            throw new BusinessException("修改ID=>[" + blogTagsDO.getId() +"]的BlogTagsDO信息异常",e);
        }
    }

    /**
    * Validate Update
    * @param blogTagsDO BlogTagsDO
    * @return BlogTagsDO
    */
    private BlogTagsDO validateForUpdate(BlogTagsDO blogTagsDO) {

        Assert.isTrue(blogTagsDO != null,"blogTagsDO不能为空!");
                Assert.isTrue(blogTagsDO.getId() != null,"查询Id不能为空!");
                // TODO Validate
        BlogTagsDO oldBlogTagsDO = blogTagsDao.selectById(blogTagsDO.getId());
        Assert.notNull(oldBlogTagsDO,"查询不到ID=>[" + blogTagsDO.getId() + "]的信息!");
        return oldBlogTagsDO;
    }

    /**
    * 删除(逻辑删除)
    * @param id Long
    * @return ResponseDO<BlogTagsDO>
    * @throws BusinessException
    */
    @Override
    @CacheEvict(value = "common:blogTagsDO",key = "'common:blogTagsDO:id:' + #id",beforeInvocation = true)
    public ResponseDO<BlogTagsDO> delBlogTagsDO(Long id) throws BusinessException{
        try {
            // validate
                        Assert.isTrue(id != null,"查询Id不能为空!");
            
            BlogTagsDO oldblogTagsDO = blogTagsDao.selectById(id);
            Assert.isTrue(oldblogTagsDO != null,"查询不到ID=>" + id + "的信息!");
            BlogTagsDO blogTagsDO = new BlogTagsDO();
            blogTagsDO.setId(id);
            blogTagsDO.setIsDeleted(1);
            blogTagsDO.setVersion(oldblogTagsDO.getVersion());
            // update
            int result = blogTagsDao.updateIsDeleteById(blogTagsDO);
            if (result < 1) {
                throw new Exception("数据已删除,请勿重复操作!");
            }
            logger.info("删除ID=>[" + id + "]的blogTagsDO成功!");
            return newStaticResponseDO(blogTagsDO);
        }catch (Exception e){
            logger.error("删除ID=>[" + id + "]的blogTagsDO异常!");
            throw new BusinessException("删除ID=>[" + id +"]的BlogTagsDO异常",e);
        }
    }
}
