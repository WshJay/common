package org.wsh.common.service.impl.blog;

import org.wsh.common.dao.blog.*;
import org.wsh.common.model.blog.*;
import org.wsh.common.service.api.blog.BlogContentService;
import org.wsh.common.service.api.blog.BlogCounterService;
import org.wsh.common.service.api.blog.BlogService;
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
* comments:  Blog服务实现层
* since Date： 2017-02-07 16:06:54
*/
@Service("blogService")
public class BlogServiceImpl extends LoggerService implements BlogService{

    @Resource
    private BlogDao blogDao;

    @Resource
    private BlogContentDao blogContentDao;

    @Resource
    private BlogTagsDao blogTagsDao;

    @Resource
    private BlogInnerTagsDao blogInnerTagsDao;

    @Resource
    private BlogCounterDao blogCounterDao;

	/**
	* 多条件查询(分页)
	* @param blogDO BlogDO
	* @param pagination  Pagination
	* @return List<BlogDO>
    */
    @Override
    public OptionsResponseDO<List<BlogDO>> queryBlogDOListForPage(BlogDO blogDO, Pagination pagination) throws BusinessException{
        try {
            int totalCount = blogDao.selectCountByParams(blogDO);
            if (totalCount <= 0) {
                return newStaticOptionsResponseDO(new ArrayList<BlogDO>());
            }
            pagination.setTotalCount(totalCount);
            List<BlogDO> blogDOList = blogDao.selectListByParams(blogDO, pagination.getRowBounds());
            return newOptionsResponseDO(totalCount, blogDOList, pagination.getPageSize(),pagination.getPP());
        } catch (Exception e) {
            throw new BusinessException("多条件查询BlogDO信息异常",e);
        }
    }

    /**
    * 根据用户ID查询
    * @param id Long
    * @return ResponseDO<BlogDO>
    */
    @Override
    @Cacheable(value = "common:blogDO",key = "'common:blogDO:id:' + #id")
    public ResponseDO<BlogDO> getBlogDOById(Long id) throws BusinessException{
        try {
            Assert.isTrue(id != null,"查询Id不能为空!");
            BlogDO blogDO = blogDao.selectById(id);
            return newStaticResponseDO(blogDO);
        } catch (Exception e) {
            throw new BusinessException("根据ID=>[" + id + "]查询BlogDO信息异常",e);
        }
    }

    /**
    * 添加
    * @param blogDO BlogDO
    * @return ResponseDO<BlogDO>
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "common:blogDO",key = "'common:blogDO:id:' + #blogDO.id")
    public ResponseDO<BlogDO> addBlogDO(BlogDO blogDO) throws BusinessException{
        try {

            // Insert BlogContent
            BlogContentDO blogContentDO = insertBlogContentDO(blogDO);
            blogDO.setContentId(blogContentDO.getId());

            // Insert Blog
            blogDO = insertBlogDO(blogDO);

            // Insert BlogCounter
            insertBlogCounter(blogDO);

            // Insert BlogTags
            insertBlogTags(blogDO);

            logger.info("新增ID=>[" + blogDO.getId() + "]的BlogDO成功");
            return newStaticResponseDO(blogDO);
        } catch (Exception e) {
            throw new BusinessException("新增ID=>[" + blogDO.getId() + "]的BlogDO信息异常",e);
        }
    }

    private void insertBlogTags(BlogDO blogDO) throws Exception {
        String[] tagNames = blogDO.getTags().split(",");
        for (String tagName : tagNames) {
            BlogTagsDO blogTag = blogTagsDao.selectByTagName(tagName);
            if (blogTag == null){
                blogTag = new BlogTagsDO(tagName);
                int result = blogTagsDao.insert(blogTag);
                if (result < 1) {
                    throw new Exception("sql插入数据为0,请检查各项参数!");
                }
            }
            int result = blogInnerTagsDao.insert(new BlogInnerTagsDO(blogDO.getId(),blogTag.getId()));
            if (result < 1) {
                throw new Exception("sql插入数据为0,请检查各项参数!");
            }
        }
    }

    private void insertBlogCounter(BlogDO blogDO) throws Exception {
        int result = blogCounterDao.insert(new BlogCounterDO(blogDO.getId()));
        if (result < 1) {
            throw new Exception("sql插入数据为0,请检查各项参数!");
        }
    }

    private BlogContentDO insertBlogContentDO(BlogDO blogDO) throws Exception {
        Assert.notNull(blogDO.getContent(),"博客内容不能为空");
        BlogContentDO blogContentDO = new BlogContentDO(blogDO.getContent());
        int result = blogContentDao.insert(blogContentDO);
        if (result < 1) {
            throw new Exception("sql插入数据为0,请检查各项参数!");
        }
        return blogContentDO;
    }

    private BlogDO insertBlogDO(BlogDO blogDO) throws Exception {
        // Validate
        validateForAdd(blogDO);

        // Insert
        int result = blogDao.insert(blogDO);
        if (result < 1) {
            throw new Exception("sql插入数据为0,请检查各项参数!");
        }
        return blogDO;
    }

    /**
    * Validate Add
    * @param blogDO BlogDO
    */
    private void validateForAdd(BlogDO blogDO) {
        Assert.notNull(blogDO,"blogDO不能为空!");
        // TODO Validate
    }

    /**
    * 修改
    * @param blogDO BlogDO
	* @return ResponseDO<BlogDO>
    */
    @Override
    @CacheEvict(value = "common:blogDO",key = "'common:blogDO:id:' + #blogDO.id",beforeInvocation = true)
    public ResponseDO<BlogDO> modifyBlogDO(BlogDO blogDO) throws BusinessException{
        try {

            // Update Blog
            updateBlogDO(blogDO);

            if (blogDO.getContent() != null){
                // Update BlogContent
                updateBlogContentDO(blogDO);
            }

            logger.info("修改ID=>[" + blogDO.getId() + "]的blogDO成功!");
            return newStaticResponseDO(blogDO);
        }catch (Exception e){
            logger.error("修改ID=>[" + blogDO.getId() + "]的blogDO异常!");
            throw new BusinessException("修改ID=>[" + blogDO.getId() +"]的BlogDO信息异常",e);
        }
    }

    private void updateBlogContentDO(BlogDO blogDO) throws Exception {

        Assert.notNull(blogDO.getContentId(),"内容ID不能为空");
        BlogContentDO blogContentDO = new BlogContentDO(blogDO.getContent());
        blogContentDO.setId(blogDO.getContentId());
        int result = blogContentDao.updateById(blogContentDO);
        if (result < 1) {
            throw new Exception("sql修改数据为0,请检查各项参数!");
        }
    }

    private void updateBlogDO(BlogDO blogDO) throws Exception {
        // validate
        BlogDO oldBlogDO = validateForUpdate(blogDO);

        // Update
        blogDO.setVersion(oldBlogDO.getVersion());
        int result = blogDao.updateById(blogDO);
        if (result < 1) {
            throw new Exception("sql修改数据为0,请检查各项参数!");
        }
    }

    /**
    * Validate Update
    * @param blogDO BlogDO
    * @return BlogDO
    */
    private BlogDO validateForUpdate(BlogDO blogDO) {

        Assert.notNull(blogDO,"blogDO不能为空!");
        Assert.isTrue(blogDO.getId() != null,"查询Id不能为空!");
        // TODO Validate
        BlogDO oldBlogDO = blogDao.selectById(blogDO.getId());
        Assert.notNull(oldBlogDO,"查询不到ID=>[" + blogDO.getId() + "]的信息!");
        return oldBlogDO;
    }

    /**
    * 删除(逻辑删除)
    * @param id Long
    * @return ResponseDO<BlogDO>
    * @throws BusinessException
    */
    @Override
    @CacheEvict(value = "common:blogDO",key = "'common:blogDO:id:' + #id",beforeInvocation = true)
    public ResponseDO<BlogDO> delBlogDO(Long id) throws BusinessException{
        try {
            // validate
                        Assert.isTrue(id != null,"查询Id不能为空!");
            
            BlogDO oldblogDO = blogDao.selectById(id);
            Assert.isTrue(oldblogDO != null,"查询不到ID=>" + id + "的信息!");
            BlogDO blogDO = new BlogDO();
            blogDO.setId(id);
            blogDO.setIsDeleted(1);
            blogDO.setVersion(oldblogDO.getVersion());
            // update
            int result = blogDao.updateIsDeleteById(blogDO);
            if (result < 1) {
                throw new Exception("数据已删除,请勿重复操作!");
            }
            logger.info("删除ID=>[" + id + "]的blogDO成功!");
            return newStaticResponseDO(blogDO);
        }catch (Exception e){
            logger.error("删除ID=>[" + id + "]的blogDO异常!");
            throw new BusinessException("删除ID=>[" + id +"]的BlogDO异常",e);
        }
    }
}
