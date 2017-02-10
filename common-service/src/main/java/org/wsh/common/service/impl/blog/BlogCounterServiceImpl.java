package org.wsh.common.service.impl.blog;

import org.wsh.common.enums.expection.Errors;
import org.wsh.common.model.blog.BlogContentDO;
import org.wsh.common.model.blog.BlogCounterDO;
import org.wsh.common.dao.blog.BlogCounterDao;
import org.wsh.common.service.api.blog.BlogCounterService;
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
* comments:  BlogCounter服务实现层
* since Date： 2017-02-08 16:57:38
*/
@Service("blogCounterService")
public class BlogCounterServiceImpl extends LoggerService implements BlogCounterService{

    @Resource
    private BlogCounterDao blogCounterDao;

	/**
	* 多条件查询(分页)
	* @param blogCounterDO BlogCounterDO
	* @param pagination  Pagination
	* @return List<BlogCounterDO>
    */
    @Override
    public OptionsResponseDO<List<BlogCounterDO>> queryBlogCounterDOListForPage(BlogCounterDO blogCounterDO, Pagination pagination) throws BusinessException{
        try {
            int totalCount = blogCounterDao.selectCountByParams(blogCounterDO);
            if (totalCount <= 0) {
                return newStaticOptionsResponseDO(new ArrayList<BlogCounterDO>());
            }
            pagination.setTotalCount(totalCount);
            List<BlogCounterDO> blogCounterDOList = blogCounterDao.selectListByParams(blogCounterDO, pagination.getRowBounds());
            return newOptionsResponseDO(totalCount, blogCounterDOList, pagination.getPageSize(),pagination.getPP());
        } catch (Exception e) {
            throw new BusinessException("多条件查询BlogCounterDO信息异常",e);
        }
    }

    /**
    * 根据用户ID查询
    * @param id Long
    * @return ResponseDO<BlogCounterDO>
    */
    @Override
    @Cacheable(value = "common:blogCounterDO",key = "'common:blogCounterDO:id:' + #id")
    public ResponseDO<BlogCounterDO> getBlogCounterDOById(Long id) throws BusinessException{
        try {
                        Assert.isTrue(id != null,"查询Id不能为空!");
                        BlogCounterDO blogCounterDO = blogCounterDao.selectById(id);
            return newStaticResponseDO(blogCounterDO);
        } catch (Exception e) {
            throw new BusinessException("根据ID=>[" + id + "]查询BlogCounterDO信息异常",e);
        }
    }

    /**
    * 添加
    * @param blogCounterDO BlogCounterDO
    * @return ResponseDO<BlogCounterDO>
    */
    @Override
    @CachePut(value = "common:blogCounterDO",key = "'common:blogCounterDO:id:' + #blogCounterDO.id")
    public ResponseDO<BlogCounterDO> addBlogCounterDO(BlogCounterDO blogCounterDO) throws BusinessException{
        try {
            // Validate
            validateForAdd(blogCounterDO);

            // Insert
            int result = blogCounterDao.insert(blogCounterDO);
            if (result < 1) {
                throw new Exception("sql插入数据为0,请检查各项参数!");
            }
            logger.info("新增ID=>[" + blogCounterDO.getId() + "]的BlogCounterDO成功");
            return newStaticResponseDO(blogCounterDO);
        } catch (Exception e) {
            throw new BusinessException("新增ID=>[" + blogCounterDO.getId() + "]的BlogCounterDO信息异常",e);
        }
    }

    /**
    * Validate Add
    * @param blogCounterDO BlogCounterDO
    */
    private void validateForAdd(BlogCounterDO blogCounterDO) {
        Assert.isTrue(blogCounterDO != null,"blogCounterDO不能为空!");
        // TODO Validate
    }

    /**
    * 修改
    * @param blogCounterDO BlogCounterDO
	* @return ResponseDO<BlogCounterDO>
    */
    @Override
    @CacheEvict(value = "common:blogCounterDO",key = "'common:blogCounterDO:id:' + #blogCounterDO.id",beforeInvocation = true)
    public ResponseDO<BlogCounterDO> modifyBlogCounterDO(BlogCounterDO blogCounterDO) throws BusinessException{
        try {

            // validate
            BlogCounterDO oldBlogCounterDO = validateForUpdate(blogCounterDO);

            // Update
            blogCounterDO.setVersion(oldBlogCounterDO.getVersion());
            int result = blogCounterDao.updateById(blogCounterDO);
            if (result < 1) {
                throw new Exception("sql修改数据为0,请检查各项参数!");
            }
            logger.info("修改ID=>[" + blogCounterDO.getId() + "]的blogCounterDO成功!");
            return newStaticResponseDO(blogCounterDO);
        }catch (Exception e){
            logger.error("修改ID=>[" + blogCounterDO.getId() + "]的blogCounterDO异常!");
            throw new BusinessException("修改ID=>[" + blogCounterDO.getId() +"]的BlogCounterDO信息异常",e);
        }
    }


    /**
    * Validate Update
    * @param blogCounterDO BlogCounterDO
    * @return BlogCounterDO
    */
    private BlogCounterDO validateForUpdate(BlogCounterDO blogCounterDO) {

        Assert.isTrue(blogCounterDO != null,"blogCounterDO不能为空!");
                Assert.isTrue(blogCounterDO.getId() != null,"查询Id不能为空!");
                // TODO Validate
        BlogCounterDO oldBlogCounterDO = blogCounterDao.selectById(blogCounterDO.getId());
        Assert.notNull(oldBlogCounterDO,"查询不到ID=>[" + blogCounterDO.getId() + "]的信息!");
        return oldBlogCounterDO;
    }

    /**
    * 删除(逻辑删除)
    * @param id Long
    * @return ResponseDO<BlogCounterDO>
    * @throws BusinessException
    */
    @Override
    @CacheEvict(value = "common:blogCounterDO",key = "'common:blogCounterDO:id:' + #id",beforeInvocation = true)
    public ResponseDO<BlogCounterDO> delBlogCounterDO(Long id) throws BusinessException{
        try {
            // validate
                        Assert.isTrue(id != null,"查询Id不能为空!");
            
            BlogCounterDO oldblogCounterDO = blogCounterDao.selectById(id);
            Assert.isTrue(oldblogCounterDO != null,"查询不到ID=>" + id + "的信息!");
            BlogCounterDO blogCounterDO = new BlogCounterDO();
            blogCounterDO.setId(id);
            blogCounterDO.setIsDeleted(1);
            blogCounterDO.setVersion(oldblogCounterDO.getVersion());
            // update
            int result = blogCounterDao.updateIsDeleteById(blogCounterDO);
            if (result < 1) {
                throw new Exception("数据已删除,请勿重复操作!");
            }
            logger.info("删除ID=>[" + id + "]的blogCounterDO成功!");
            return newStaticResponseDO(blogCounterDO);
        }catch (Exception e){
            logger.error("删除ID=>[" + id + "]的blogCounterDO异常!");
            throw new BusinessException("删除ID=>[" + id +"]的BlogCounterDO异常",e);
        }
    }

    @Override
    public ResponseDO<List<BlogCounterDO>> queryBlogCounterDOListByBlogIds(List<Long> idList) {
        try {
            // validate
            Assert.notEmpty(idList,"查询Ids不能为空!");

            List<BlogCounterDO> blogCounterDOList = blogCounterDao.selectListByIds(idList);
            logger.info("根据Ids=>[" + idList.toString() + "]查询blogCounterDOList成功!");
            return newStaticResponseDO(blogCounterDOList);
        }catch (Exception e){
            logger.error("根据Ids=>[" + idList.toString() + "]查询blogCounterDOList成功!",e);
            return new ResponseDO<>("-1", "查询异常!");
        }
    }

    @Override
    public ResponseDO addViewNum(Long blogId) {
        try {

            // validate
            Assert.notNull(blogId,"BlogId不能为空!");

            BlogCounterDO oldBlogCounterDO = blogCounterDao.selectByBlogId(blogId);
            Assert.notNull(oldBlogCounterDO,"查询不到BlogID=>[" + blogId +"]相关信息!");

            // Update
            BlogCounterDO blogCounterDO = new BlogCounterDO();
            blogCounterDO.setId(oldBlogCounterDO.getId());
            blogCounterDO.setVersion(oldBlogCounterDO.getVersion());
            int result = blogCounterDao.updateViewNumById(blogCounterDO);
            if (result < 1) {
                throw new Exception("sql修改数据为0,请检查各项参数!");
            }
            logger.info("修改ID=>[" + blogCounterDO.getId() + "]的blogCounterDO成功!");
            return new ResponseDO();
        }catch (Exception e){
            logger.error("修改ID=>[" + blogId + "]的blogCounterDO异常!",e);
            return new ResponseDO<>(Errors.DEFAULT_ERROR);
        }
    }
}
