package org.wsh.common.service.impl.blog;

import org.wsh.common.model.blog.BlogPraiseDO;
import org.wsh.common.dao.blog.BlogPraiseDao;
import org.wsh.common.service.api.blog.BlogPraiseService;
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
* comments:  BlogPraise服务实现层
* since Date： 2017-02-08 14:50:43
*/
@Service("blogPraiseService")
public class BlogPraiseServiceImpl extends LoggerService implements BlogPraiseService{

    @Resource
    private BlogPraiseDao blogPraiseDao;

	/**
	* 多条件查询(分页)
	* @param blogPraiseDO BlogPraiseDO
	* @param pagination  Pagination
	* @return List<BlogPraiseDO>
    */
    @Override
    public OptionsResponseDO<List<BlogPraiseDO>> queryBlogPraiseDOListForPage(BlogPraiseDO blogPraiseDO, Pagination pagination) throws BusinessException{
        try {
            int totalCount = blogPraiseDao.selectCountByParams(blogPraiseDO);
            if (totalCount <= 0) {
                return newStaticOptionsResponseDO(new ArrayList<BlogPraiseDO>());
            }
            pagination.setTotalCount(totalCount);
            List<BlogPraiseDO> blogPraiseDOList = blogPraiseDao.selectListByParams(blogPraiseDO, pagination.getRowBounds());
            return newOptionsResponseDO(totalCount, blogPraiseDOList, pagination.getPageSize(),pagination.getPP());
        } catch (Exception e) {
            throw new BusinessException("多条件查询BlogPraiseDO信息异常",e);
        }
    }

    /**
    * 根据用户ID查询
    * @param id Long
    * @return ResponseDO<BlogPraiseDO>
    */
    @Override
    @Cacheable(value = "common:blogPraiseDO",key = "'common:blogPraiseDO:id:' + #id")
    public ResponseDO<BlogPraiseDO> getBlogPraiseDOById(Long id) throws BusinessException{
        try {
                        Assert.isTrue(id != null,"查询Id不能为空!");
                        BlogPraiseDO blogPraiseDO = blogPraiseDao.selectById(id);
            return newStaticResponseDO(blogPraiseDO);
        } catch (Exception e) {
            throw new BusinessException("根据ID=>[" + id + "]查询BlogPraiseDO信息异常",e);
        }
    }

    /**
    * 添加
    * @param blogPraiseDO BlogPraiseDO
    * @return ResponseDO<BlogPraiseDO>
    */
    @Override
    @CachePut(value = "common:blogPraiseDO",key = "'common:blogPraiseDO:id:' + #blogPraiseDO.id")
    public ResponseDO<BlogPraiseDO> addBlogPraiseDO(BlogPraiseDO blogPraiseDO) throws BusinessException{
        try {
            // Validate
            validateForAdd(blogPraiseDO);

            // Insert
            int result = blogPraiseDao.insert(blogPraiseDO);
            if (result < 1) {
                throw new Exception("sql插入数据为0,请检查各项参数!");
            }
            logger.info("新增ID=>[" + blogPraiseDO.getId() + "]的BlogPraiseDO成功");
            return newStaticResponseDO(blogPraiseDO);
        } catch (Exception e) {
            throw new BusinessException("新增ID=>[" + blogPraiseDO.getId() + "]的BlogPraiseDO信息异常",e);
        }
    }

    /**
    * Validate Add
    * @param blogPraiseDO BlogPraiseDO
    */
    private void validateForAdd(BlogPraiseDO blogPraiseDO) {
        Assert.isTrue(blogPraiseDO != null,"blogPraiseDO不能为空!");
        // TODO Validate
    }

    /**
    * 修改
    * @param blogPraiseDO BlogPraiseDO
	* @return ResponseDO<BlogPraiseDO>
    */
    @Override
    @CacheEvict(value = "common:blogPraiseDO",key = "'common:blogPraiseDO:id:' + #blogPraiseDO.id",beforeInvocation = true)
    public ResponseDO<BlogPraiseDO> modifyBlogPraiseDO(BlogPraiseDO blogPraiseDO) throws BusinessException{
        try {

            // validate
            BlogPraiseDO oldBlogPraiseDO = validateForUpdate(blogPraiseDO);

            // Update
            int result = blogPraiseDao.updateById(blogPraiseDO);
            if (result < 1) {
                throw new Exception("sql修改数据为0,请检查各项参数!");
            }
            logger.info("修改ID=>[" + blogPraiseDO.getId() + "]的blogPraiseDO成功!");
            return newStaticResponseDO(blogPraiseDO);
        }catch (Exception e){
            logger.error("修改ID=>[" + blogPraiseDO.getId() + "]的blogPraiseDO异常!");
            throw new BusinessException("修改ID=>[" + blogPraiseDO.getId() +"]的BlogPraiseDO信息异常",e);
        }
    }

    /**
    * Validate Update
    * @param blogPraiseDO BlogPraiseDO
    * @return BlogPraiseDO
    */
    private BlogPraiseDO validateForUpdate(BlogPraiseDO blogPraiseDO) {

        Assert.isTrue(blogPraiseDO != null,"blogPraiseDO不能为空!");
                Assert.isTrue(blogPraiseDO.getId() != null,"查询Id不能为空!");
                // TODO Validate
        BlogPraiseDO oldBlogPraiseDO = blogPraiseDao.selectById(blogPraiseDO.getId());
        Assert.notNull(oldBlogPraiseDO,"查询不到ID=>[" + blogPraiseDO.getId() + "]的信息!");
        return oldBlogPraiseDO;
    }

    /**
    * 删除(逻辑删除)
    * @param id Long
    * @return ResponseDO<BlogPraiseDO>
    * @throws BusinessException
    */
    @Override
    @CacheEvict(value = "common:blogPraiseDO",key = "'common:blogPraiseDO:id:' + #id",beforeInvocation = true)
    public ResponseDO<BlogPraiseDO> delBlogPraiseDO(Long id) throws BusinessException{
        try {
            // validate
                        Assert.isTrue(id != null,"查询Id不能为空!");
            
            BlogPraiseDO oldblogPraiseDO = blogPraiseDao.selectById(id);
            Assert.isTrue(oldblogPraiseDO != null,"查询不到ID=>" + id + "的信息!");
            BlogPraiseDO blogPraiseDO = new BlogPraiseDO();
            blogPraiseDO.setId(id);
            // update
            int result = blogPraiseDao.updateIsDeleteById(blogPraiseDO);
            if (result < 1) {
                throw new Exception("数据已删除,请勿重复操作!");
            }
            logger.info("删除ID=>[" + id + "]的blogPraiseDO成功!");
            return newStaticResponseDO(blogPraiseDO);
        }catch (Exception e){
            logger.error("删除ID=>[" + id + "]的blogPraiseDO异常!");
            throw new BusinessException("删除ID=>[" + id +"]的BlogPraiseDO异常",e);
        }
    }
}
