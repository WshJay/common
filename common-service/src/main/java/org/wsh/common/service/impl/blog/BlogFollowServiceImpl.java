package org.wsh.common.service.impl.blog;

import org.wsh.common.model.blog.BlogFollowDO;
import org.wsh.common.dao.blog.BlogFollowDao;
import org.wsh.common.service.api.blog.BlogFollowService;
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
* comments:  BlogFollow服务实现层
* since Date： 2017-02-07 16:06:55
*/
@Service("blogFollowService")
public class BlogFollowServiceImpl extends LoggerService implements BlogFollowService{

    @Resource
    private BlogFollowDao blogFollowDao;

	/**
	* 多条件查询(分页)
	* @param blogFollowDO BlogFollowDO
	* @param pagination  Pagination
	* @return List<BlogFollowDO>
    */
    @Override
    public OptionsResponseDO<List<BlogFollowDO>> queryBlogFollowDOListForPage(BlogFollowDO blogFollowDO, Pagination pagination) throws BusinessException{
        try {
            int totalCount = blogFollowDao.selectCountByParams(blogFollowDO);
            if (totalCount <= 0) {
                return newStaticOptionsResponseDO(new ArrayList<BlogFollowDO>());
            }
            pagination.setTotalCount(totalCount);
            List<BlogFollowDO> blogFollowDOList = blogFollowDao.selectListByParams(blogFollowDO, pagination.getRowBounds());
            return newOptionsResponseDO(totalCount, blogFollowDOList, pagination.getPageSize(),pagination.getPP());
        } catch (Exception e) {
            throw new BusinessException("多条件查询BlogFollowDO信息异常",e);
        }
    }

    /**
    * 根据用户ID查询
    * @param id Long
    * @return ResponseDO<BlogFollowDO>
    */
    @Override
    @Cacheable(value = "common:blogFollowDO",key = "'common:blogFollowDO:id:' + #id")
    public ResponseDO<BlogFollowDO> getBlogFollowDOById(Long id) throws BusinessException{
        try {
                        Assert.isTrue(id != null,"查询Id不能为空!");
                        BlogFollowDO blogFollowDO = blogFollowDao.selectById(id);
            return newStaticResponseDO(blogFollowDO);
        } catch (Exception e) {
            throw new BusinessException("根据ID=>[" + id + "]查询BlogFollowDO信息异常",e);
        }
    }

    /**
    * 添加
    * @param blogFollowDO BlogFollowDO
    * @return ResponseDO<BlogFollowDO>
    */
    @Override
    @CachePut(value = "common:blogFollowDO",key = "'common:blogFollowDO:id:' + #blogFollowDO.id")
    public ResponseDO<BlogFollowDO> addBlogFollowDO(BlogFollowDO blogFollowDO) throws BusinessException{
        try {
            // Validate
            validateForAdd(blogFollowDO);

            // Insert
            int result = blogFollowDao.insert(blogFollowDO);
            if (result < 1) {
                throw new Exception("sql插入数据为0,请检查各项参数!");
            }
            logger.info("新增ID=>[" + blogFollowDO.getId() + "]的BlogFollowDO成功");
            return newStaticResponseDO(blogFollowDO);
        } catch (Exception e) {
            throw new BusinessException("新增ID=>[" + blogFollowDO.getId() + "]的BlogFollowDO信息异常",e);
        }
    }

    /**
    * Validate Add
    * @param blogFollowDO BlogFollowDO
    */
    private void validateForAdd(BlogFollowDO blogFollowDO) {
        Assert.isTrue(blogFollowDO != null,"blogFollowDO不能为空!");
        // TODO Validate
    }

    /**
    * 修改
    * @param blogFollowDO BlogFollowDO
	* @return ResponseDO<BlogFollowDO>
    */
    @Override
    @CacheEvict(value = "common:blogFollowDO",key = "'common:blogFollowDO:id:' + #blogFollowDO.id",beforeInvocation = true)
    public ResponseDO<BlogFollowDO> modifyBlogFollowDO(BlogFollowDO blogFollowDO) throws BusinessException{
        try {

            // validate
            BlogFollowDO oldBlogFollowDO = validateForUpdate(blogFollowDO);

            // Update
            blogFollowDO.setVersion(oldBlogFollowDO.getVersion());
            int result = blogFollowDao.updateById(blogFollowDO);
            if (result < 1) {
                throw new Exception("sql修改数据为0,请检查各项参数!");
            }
            logger.info("修改ID=>[" + blogFollowDO.getId() + "]的blogFollowDO成功!");
            return newStaticResponseDO(blogFollowDO);
        }catch (Exception e){
            logger.error("修改ID=>[" + blogFollowDO.getId() + "]的blogFollowDO异常!");
            throw new BusinessException("修改ID=>[" + blogFollowDO.getId() +"]的BlogFollowDO信息异常",e);
        }
    }

    /**
    * Validate Update
    * @param blogFollowDO BlogFollowDO
    * @return BlogFollowDO
    */
    private BlogFollowDO validateForUpdate(BlogFollowDO blogFollowDO) {

        Assert.isTrue(blogFollowDO != null,"blogFollowDO不能为空!");
                Assert.isTrue(blogFollowDO.getId() != null,"查询Id不能为空!");
                // TODO Validate
        BlogFollowDO oldBlogFollowDO = blogFollowDao.selectById(blogFollowDO.getId());
        Assert.notNull(oldBlogFollowDO,"查询不到ID=>[" + blogFollowDO.getId() + "]的信息!");
        return oldBlogFollowDO;
    }

    /**
    * 删除(逻辑删除)
    * @param id Long
    * @return ResponseDO<BlogFollowDO>
    * @throws BusinessException
    */
    @Override
    @CacheEvict(value = "common:blogFollowDO",key = "'common:blogFollowDO:id:' + #id",beforeInvocation = true)
    public ResponseDO<BlogFollowDO> delBlogFollowDO(Long id) throws BusinessException{
        try {
            // validate
                        Assert.isTrue(id != null,"查询Id不能为空!");
            
            BlogFollowDO oldblogFollowDO = blogFollowDao.selectById(id);
            Assert.isTrue(oldblogFollowDO != null,"查询不到ID=>" + id + "的信息!");
            BlogFollowDO blogFollowDO = new BlogFollowDO();
            blogFollowDO.setId(id);
            blogFollowDO.setVersion(oldblogFollowDO.getVersion());
            // update
            int result = blogFollowDao.updateIsDeleteById(blogFollowDO);
            if (result < 1) {
                throw new Exception("数据已删除,请勿重复操作!");
            }
            logger.info("删除ID=>[" + id + "]的blogFollowDO成功!");
            return newStaticResponseDO(blogFollowDO);
        }catch (Exception e){
            logger.error("删除ID=>[" + id + "]的blogFollowDO异常!");
            throw new BusinessException("删除ID=>[" + id +"]的BlogFollowDO异常",e);
        }
    }
}
