package org.wsh.common.service.impl.blog;

import org.wsh.common.model.blog.BlogInnerTagsDO;
import org.wsh.common.dao.blog.BlogInnerTagsDao;
import org.wsh.common.service.api.blog.BlogInnerTagsService;
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
* comments:  BlogInnerTags服务实现层
* since Date： 2017-02-07 16:06:55
*/
@Service("blogInnerTagsService")
public class BlogInnerTagsServiceImpl extends LoggerService implements BlogInnerTagsService{

    @Resource
    private BlogInnerTagsDao blogInnerTagsDao;

	/**
	* 多条件查询(分页)
	* @param blogInnerTagsDO BlogInnerTagsDO
	* @param pagination  Pagination
	* @return List<BlogInnerTagsDO>
    */
    @Override
    public OptionsResponseDO<List<BlogInnerTagsDO>> queryBlogInnerTagsDOListForPage(BlogInnerTagsDO blogInnerTagsDO, Pagination pagination) throws BusinessException{
        try {
            int totalCount = blogInnerTagsDao.selectCountByParams(blogInnerTagsDO);
            if (totalCount <= 0) {
                return newStaticOptionsResponseDO(new ArrayList<BlogInnerTagsDO>());
            }
            pagination.setTotalCount(totalCount);
            List<BlogInnerTagsDO> blogInnerTagsDOList = blogInnerTagsDao.selectListByParams(blogInnerTagsDO, pagination.getRowBounds());
            return newOptionsResponseDO(totalCount, blogInnerTagsDOList, pagination.getPageSize(),pagination.getPP());
        } catch (Exception e) {
            throw new BusinessException("多条件查询BlogInnerTagsDO信息异常",e);
        }
    }

    /**
    * 根据用户ID查询
    * @param id Long
    * @return ResponseDO<BlogInnerTagsDO>
    */
    @Override
    @Cacheable(value = "common:blogInnerTagsDO",key = "'common:blogInnerTagsDO:id:' + #id")
    public ResponseDO<BlogInnerTagsDO> getBlogInnerTagsDOById(Long id) throws BusinessException{
        try {
                        Assert.isTrue(id != null,"查询Id不能为空!");
                        BlogInnerTagsDO blogInnerTagsDO = blogInnerTagsDao.selectById(id);
            return newStaticResponseDO(blogInnerTagsDO);
        } catch (Exception e) {
            throw new BusinessException("根据ID=>[" + id + "]查询BlogInnerTagsDO信息异常",e);
        }
    }

    /**
    * 添加
    * @param blogInnerTagsDO BlogInnerTagsDO
    * @return ResponseDO<BlogInnerTagsDO>
    */
    @Override
    @CachePut(value = "common:blogInnerTagsDO",key = "'common:blogInnerTagsDO:id:' + #blogInnerTagsDO.id")
    public ResponseDO<BlogInnerTagsDO> addBlogInnerTagsDO(BlogInnerTagsDO blogInnerTagsDO) throws BusinessException{
        try {
            // Validate
            validateForAdd(blogInnerTagsDO);

            // Insert
            int result = blogInnerTagsDao.insert(blogInnerTagsDO);
            if (result < 1) {
                throw new Exception("sql插入数据为0,请检查各项参数!");
            }
            logger.info("新增ID=>[" + blogInnerTagsDO.getId() + "]的BlogInnerTagsDO成功");
            return newStaticResponseDO(blogInnerTagsDO);
        } catch (Exception e) {
            throw new BusinessException("新增ID=>[" + blogInnerTagsDO.getId() + "]的BlogInnerTagsDO信息异常",e);
        }
    }

    /**
    * Validate Add
    * @param blogInnerTagsDO BlogInnerTagsDO
    */
    private void validateForAdd(BlogInnerTagsDO blogInnerTagsDO) {
        Assert.isTrue(blogInnerTagsDO != null,"blogInnerTagsDO不能为空!");
        // TODO Validate
    }

    /**
    * 修改
    * @param blogInnerTagsDO BlogInnerTagsDO
	* @return ResponseDO<BlogInnerTagsDO>
    */
    @Override
    @CacheEvict(value = "common:blogInnerTagsDO",key = "'common:blogInnerTagsDO:id:' + #blogInnerTagsDO.id",beforeInvocation = true)
    public ResponseDO<BlogInnerTagsDO> modifyBlogInnerTagsDO(BlogInnerTagsDO blogInnerTagsDO) throws BusinessException{
        try {

            // validate
            BlogInnerTagsDO oldBlogInnerTagsDO = validateForUpdate(blogInnerTagsDO);

            // Update
            int result = blogInnerTagsDao.updateById(blogInnerTagsDO);
            if (result < 1) {
                throw new Exception("sql修改数据为0,请检查各项参数!");
            }
            logger.info("修改ID=>[" + blogInnerTagsDO.getId() + "]的blogInnerTagsDO成功!");
            return newStaticResponseDO(blogInnerTagsDO);
        }catch (Exception e){
            logger.error("修改ID=>[" + blogInnerTagsDO.getId() + "]的blogInnerTagsDO异常!");
            throw new BusinessException("修改ID=>[" + blogInnerTagsDO.getId() +"]的BlogInnerTagsDO信息异常",e);
        }
    }

    /**
    * Validate Update
    * @param blogInnerTagsDO BlogInnerTagsDO
    * @return BlogInnerTagsDO
    */
    private BlogInnerTagsDO validateForUpdate(BlogInnerTagsDO blogInnerTagsDO) {

        Assert.isTrue(blogInnerTagsDO != null,"blogInnerTagsDO不能为空!");
                Assert.isTrue(blogInnerTagsDO.getId() != null,"查询Id不能为空!");
                // TODO Validate
        BlogInnerTagsDO oldBlogInnerTagsDO = blogInnerTagsDao.selectById(blogInnerTagsDO.getId());
        Assert.notNull(oldBlogInnerTagsDO,"查询不到ID=>[" + blogInnerTagsDO.getId() + "]的信息!");
        return oldBlogInnerTagsDO;
    }

    /**
    * 删除(逻辑删除)
    * @param id Long
    * @return ResponseDO<BlogInnerTagsDO>
    * @throws BusinessException
    */
    @Override
    @CacheEvict(value = "common:blogInnerTagsDO",key = "'common:blogInnerTagsDO:id:' + #id",beforeInvocation = true)
    public ResponseDO<BlogInnerTagsDO> delBlogInnerTagsDO(Long id) throws BusinessException{
        try {
            // validate
                        Assert.isTrue(id != null,"查询Id不能为空!");
            
            BlogInnerTagsDO oldblogInnerTagsDO = blogInnerTagsDao.selectById(id);
            Assert.isTrue(oldblogInnerTagsDO != null,"查询不到ID=>" + id + "的信息!");
            BlogInnerTagsDO blogInnerTagsDO = new BlogInnerTagsDO();
            blogInnerTagsDO.setId(id);
            // update
            int result = blogInnerTagsDao.updateIsDeleteById(blogInnerTagsDO);
            if (result < 1) {
                throw new Exception("数据已删除,请勿重复操作!");
            }
            logger.info("删除ID=>[" + id + "]的blogInnerTagsDO成功!");
            return newStaticResponseDO(blogInnerTagsDO);
        }catch (Exception e){
            logger.error("删除ID=>[" + id + "]的blogInnerTagsDO异常!");
            throw new BusinessException("删除ID=>[" + id +"]的BlogInnerTagsDO异常",e);
        }
    }
}
