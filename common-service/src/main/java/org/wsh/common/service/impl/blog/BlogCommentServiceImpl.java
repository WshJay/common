package org.wsh.common.service.impl.blog;

import org.wsh.common.dao.blog.BlogCounterDao;
import org.wsh.common.model.blog.BlogCommentDO;
import org.wsh.common.dao.blog.BlogCommentDao;
import org.wsh.common.model.blog.BlogCounterDO;
import org.wsh.common.service.api.blog.BlogCommentService;
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
* comments:  BlogComment服务实现层
* since Date： 2017-02-08 14:50:43
*/
@Service("blogCommentService")
public class BlogCommentServiceImpl extends LoggerService implements BlogCommentService{

    @Resource
    private BlogCommentDao blogCommentDao;

    @Resource
    private BlogCounterDao blogCounterDao;

	/**
	* 多条件查询(分页)
	* @param blogCommentDO BlogCommentDO
	* @param pagination  Pagination
	* @return List<BlogCommentDO>
    */
    @Override
    public OptionsResponseDO<List<BlogCommentDO>> queryBlogCommentDOListForPage(BlogCommentDO blogCommentDO, Pagination pagination) throws BusinessException{
        try {
            int totalCount = blogCommentDao.selectCountByParams(blogCommentDO);
            if (totalCount <= 0) {
                return newStaticOptionsResponseDO(new ArrayList<BlogCommentDO>());
            }
            pagination.setTotalCount(totalCount);
            List<BlogCommentDO> blogCommentDOList = blogCommentDao.selectListByParams(blogCommentDO, pagination.getRowBounds());
            return newOptionsResponseDO(totalCount, blogCommentDOList, pagination.getPageSize(),pagination.getPP());
        } catch (Exception e) {
            throw new BusinessException("多条件查询BlogCommentDO信息异常",e);
        }
    }

    /**
    * 根据用户ID查询
    * @param id Long
    * @return ResponseDO<BlogCommentDO>
    */
    @Override
    @Cacheable(value = "common:blogCommentDO",key = "'common:blogCommentDO:id:' + #id")
    public ResponseDO<BlogCommentDO> getBlogCommentDOById(Long id) throws BusinessException{
        try {
                        Assert.isTrue(id != null,"查询Id不能为空!");
                        BlogCommentDO blogCommentDO = blogCommentDao.selectById(id);
            return newStaticResponseDO(blogCommentDO);
        } catch (Exception e) {
            throw new BusinessException("根据ID=>[" + id + "]查询BlogCommentDO信息异常",e);
        }
    }

    /**
    * 添加
    * @param blogCommentDO BlogCommentDO
    * @return ResponseDO<BlogCommentDO>
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "common:blogCommentDO",key = "'common:blogCommentDO:id:' + #blogCommentDO.id")
    public ResponseDO<BlogCommentDO> addBlogCommentDO(BlogCommentDO blogCommentDO) throws BusinessException{
        try {
            // Validate
            validateForAdd(blogCommentDO);

            // Insert
            int result = blogCommentDao.insert(blogCommentDO);
            if (result < 1) {
                throw new Exception("sql插入数据为0,请检查各项参数!");
            }

            // Update
            updateBlogCommentNum(blogCommentDO, 1);

            logger.info("新增ID=>[" + blogCommentDO.getId() + "]的BlogCommentDO成功");
            return newStaticResponseDO(blogCommentDO);
        } catch (Exception e) {
            throw new BusinessException("新增ID=>[" + blogCommentDO.getId() + "]的BlogCommentDO信息异常",e);
        }
    }

    private void updateBlogCommentNum(BlogCommentDO blogCommentDO, int commentNum) throws Exception {
        BlogCounterDO oldBlogCounterDO = blogCounterDao.selectByBlogId(blogCommentDO.getBlogId());
        Assert.notNull(blogCommentDO,"查询不到信息!");
        BlogCounterDO blogCounterDO = new BlogCounterDO();
        blogCounterDO.setId(oldBlogCounterDO.getId());
        blogCounterDO.setCommentNum(commentNum);
        blogCounterDO.setVersion(oldBlogCounterDO.getVersion());
        int result = blogCounterDao.updateCommentNumById(blogCounterDO);
        if (result < 1) {
            throw new Exception("sql插入数据为0,请检查各项参数!");
        }
    }

    /**
    * Validate Add
    * @param blogCommentDO BlogCommentDO
    */
    private void validateForAdd(BlogCommentDO blogCommentDO) {
        Assert.isTrue(blogCommentDO != null,"blogCommentDO不能为空!");
        // TODO Validate
    }

    /**
    * 修改
    * @param blogCommentDO BlogCommentDO
	* @return ResponseDO<BlogCommentDO>
    */
    @Override
    @CacheEvict(value = "common:blogCommentDO",key = "'common:blogCommentDO:id:' + #blogCommentDO.id",beforeInvocation = true)
    public ResponseDO<BlogCommentDO> modifyBlogCommentDO(BlogCommentDO blogCommentDO) throws BusinessException{
        try {

            // validate
            BlogCommentDO oldBlogCommentDO = validateForUpdate(blogCommentDO);

            // Update
            blogCommentDO.setVersion(oldBlogCommentDO.getVersion());
            int result = blogCommentDao.updateById(blogCommentDO);
            if (result < 1) {
                throw new Exception("sql修改数据为0,请检查各项参数!");
            }
            logger.info("修改ID=>[" + blogCommentDO.getId() + "]的blogCommentDO成功!");
            return newStaticResponseDO(blogCommentDO);
        }catch (Exception e){
            logger.error("修改ID=>[" + blogCommentDO.getId() + "]的blogCommentDO异常!");
            throw new BusinessException("修改ID=>[" + blogCommentDO.getId() +"]的BlogCommentDO信息异常",e);
        }
    }

    /**
    * Validate Update
    * @param blogCommentDO BlogCommentDO
    * @return BlogCommentDO
    */
    private BlogCommentDO validateForUpdate(BlogCommentDO blogCommentDO) {

        Assert.isTrue(blogCommentDO != null,"blogCommentDO不能为空!");
                Assert.isTrue(blogCommentDO.getId() != null,"查询Id不能为空!");
                // TODO Validate
        BlogCommentDO oldBlogCommentDO = blogCommentDao.selectById(blogCommentDO.getId());
        Assert.notNull(oldBlogCommentDO,"查询不到ID=>[" + blogCommentDO.getId() + "]的信息!");
        return oldBlogCommentDO;
    }

    /**
    * 删除(逻辑删除)
    * @param id Long
    * @return ResponseDO<BlogCommentDO>
    * @throws BusinessException
    */
    @Override
    @CacheEvict(value = "common:blogCommentDO",key = "'common:blogCommentDO:id:' + #id",beforeInvocation = true)
    public ResponseDO<BlogCommentDO> delBlogCommentDO(Long id) throws BusinessException{
        try {
            // Delete Comment
            BlogCommentDO blogCommentDO = delBlogComment(id);

            // Update CommentNum
            updateBlogCommentNum(blogCommentDO, -1);

            logger.info("删除ID=>[" + id + "]的blogCommentDO成功!");
            return newStaticResponseDO(blogCommentDO);
        }catch (Exception e){
            logger.error("删除ID=>[" + id + "]的blogCommentDO异常!");
            throw new BusinessException("删除ID=>[" + id +"]的BlogCommentDO异常",e);
        }
    }

    private BlogCommentDO delBlogComment(Long id) throws Exception {

        // validate
        Assert.notNull(id ,"Id不能为空!");
        BlogCommentDO oldblogCommentDO = blogCommentDao.selectById(id);
        Assert.notNull(oldblogCommentDO,"查询不到ID=>" + id + "的信息!");
        BlogCommentDO blogCommentDO = new BlogCommentDO();
        blogCommentDO.setId(id);
        blogCommentDO.setVersion(oldblogCommentDO.getVersion());
        // update
        int result = blogCommentDao.updateIsDeleteById(blogCommentDO);
        if (result < 1) {
            throw new Exception("数据已删除,请勿重复操作!");
        }
        return blogCommentDO;
    }
}
