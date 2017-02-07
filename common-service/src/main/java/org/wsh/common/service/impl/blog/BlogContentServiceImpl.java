package org.wsh.common.service.impl.blog;

import org.wsh.common.model.blog.BlogContentDO;
import org.wsh.common.dao.blog.BlogContentDao;
import org.wsh.common.service.api.blog.BlogContentService;
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
* comments:  BlogContent服务实现层
* since Date： 2017-02-07 16:06:55
*/
@Service("blogContentService")
public class BlogContentServiceImpl extends LoggerService implements BlogContentService{

    @Resource
    private BlogContentDao blogContentDao;

	/**
	* 多条件查询(分页)
	* @param blogContentDO BlogContentDO
	* @param pagination  Pagination
	* @return List<BlogContentDO>
    */
    @Override
    public OptionsResponseDO<List<BlogContentDO>> queryBlogContentDOListForPage(BlogContentDO blogContentDO, Pagination pagination) throws BusinessException{
        try {
            int totalCount = blogContentDao.selectCountByParams(blogContentDO);
            if (totalCount <= 0) {
                return newStaticOptionsResponseDO(new ArrayList<BlogContentDO>());
            }
            pagination.setTotalCount(totalCount);
            List<BlogContentDO> blogContentDOList = blogContentDao.selectListByParams(blogContentDO, pagination.getRowBounds());
            return newOptionsResponseDO(totalCount, blogContentDOList, pagination.getPageSize(),pagination.getPP());
        } catch (Exception e) {
            throw new BusinessException("多条件查询BlogContentDO信息异常",e);
        }
    }

    /**
    * 根据用户ID查询
    * @param id Long
    * @return ResponseDO<BlogContentDO>
    */
    @Override
    @Cacheable(value = "common:blogContentDO",key = "'common:blogContentDO:id:' + #id")
    public ResponseDO<BlogContentDO> getBlogContentDOById(Long id) throws BusinessException{
        try {
                        Assert.isTrue(id != null,"查询Id不能为空!");
                        BlogContentDO blogContentDO = blogContentDao.selectById(id);
            return newStaticResponseDO(blogContentDO);
        } catch (Exception e) {
            throw new BusinessException("根据ID=>[" + id + "]查询BlogContentDO信息异常",e);
        }
    }

    /**
    * 添加
    * @param blogContentDO BlogContentDO
    * @return ResponseDO<BlogContentDO>
    */
    @Override
    @CachePut(value = "common:blogContentDO",key = "'common:blogContentDO:id:' + #blogContentDO.id")
    public ResponseDO<BlogContentDO> addBlogContentDO(BlogContentDO blogContentDO) throws BusinessException{
        try {
            // Validate
            validateForAdd(blogContentDO);

            // Insert
            int result = blogContentDao.insert(blogContentDO);
            if (result < 1) {
                throw new Exception("sql插入数据为0,请检查各项参数!");
            }
            logger.info("新增ID=>[" + blogContentDO.getId() + "]的BlogContentDO成功");
            return newStaticResponseDO(blogContentDO);
        } catch (Exception e) {
            throw new BusinessException("新增ID=>[" + blogContentDO.getId() + "]的BlogContentDO信息异常",e);
        }
    }

    /**
    * Validate Add
    * @param blogContentDO BlogContentDO
    */
    private void validateForAdd(BlogContentDO blogContentDO) {
        Assert.isTrue(blogContentDO != null,"blogContentDO不能为空!");
        // TODO Validate
    }

    /**
    * 修改
    * @param blogContentDO BlogContentDO
	* @return ResponseDO<BlogContentDO>
    */
    @Override
    @CacheEvict(value = "common:blogContentDO",key = "'common:blogContentDO:id:' + #blogContentDO.id",beforeInvocation = true)
    public ResponseDO<BlogContentDO> modifyBlogContentDO(BlogContentDO blogContentDO) throws BusinessException{
        try {

            // validate
            BlogContentDO oldBlogContentDO = validateForUpdate(blogContentDO);

            // Update
            blogContentDO.setVersion(oldBlogContentDO.getVersion());
            int result = blogContentDao.updateById(blogContentDO);
            if (result < 1) {
                throw new Exception("sql修改数据为0,请检查各项参数!");
            }
            logger.info("修改ID=>[" + blogContentDO.getId() + "]的blogContentDO成功!");
            return newStaticResponseDO(blogContentDO);
        }catch (Exception e){
            logger.error("修改ID=>[" + blogContentDO.getId() + "]的blogContentDO异常!");
            throw new BusinessException("修改ID=>[" + blogContentDO.getId() +"]的BlogContentDO信息异常",e);
        }
    }

    /**
    * Validate Update
    * @param blogContentDO BlogContentDO
    * @return BlogContentDO
    */
    private BlogContentDO validateForUpdate(BlogContentDO blogContentDO) {

        Assert.isTrue(blogContentDO != null,"blogContentDO不能为空!");
                Assert.isTrue(blogContentDO.getId() != null,"查询Id不能为空!");
                // TODO Validate
        BlogContentDO oldBlogContentDO = blogContentDao.selectById(blogContentDO.getId());
        Assert.notNull(oldBlogContentDO,"查询不到ID=>[" + blogContentDO.getId() + "]的信息!");
        return oldBlogContentDO;
    }

    /**
    * 删除(逻辑删除)
    * @param id Long
    * @return ResponseDO<BlogContentDO>
    * @throws BusinessException
    */
    @Override
    @CacheEvict(value = "common:blogContentDO",key = "'common:blogContentDO:id:' + #id",beforeInvocation = true)
    public ResponseDO<BlogContentDO> delBlogContentDO(Long id) throws BusinessException{
        try {
            // validate
                        Assert.isTrue(id != null,"查询Id不能为空!");
            
            BlogContentDO oldblogContentDO = blogContentDao.selectById(id);
            Assert.isTrue(oldblogContentDO != null,"查询不到ID=>" + id + "的信息!");
            BlogContentDO blogContentDO = new BlogContentDO();
            blogContentDO.setId(id);
            blogContentDO.setIsDeleted(1);
            blogContentDO.setVersion(oldblogContentDO.getVersion());
            // update
            int result = blogContentDao.updateIsDeleteById(blogContentDO);
            if (result < 1) {
                throw new Exception("数据已删除,请勿重复操作!");
            }
            logger.info("删除ID=>[" + id + "]的blogContentDO成功!");
            return newStaticResponseDO(blogContentDO);
        }catch (Exception e){
            logger.error("删除ID=>[" + id + "]的blogContentDO异常!");
            throw new BusinessException("删除ID=>[" + id +"]的BlogContentDO异常",e);
        }
    }
}
