package org.wsh.common.service.impl.blog;

import org.wsh.common.model.blog.BlogCatalogDO;
import org.wsh.common.dao.blog.BlogCatalogDao;
import org.wsh.common.service.api.blog.BlogCatalogService;
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
* comments:  BlogCatalog服务实现层
* since Date： 2017-02-08 14:50:42
*/
@Service("blogCatalogService")
public class BlogCatalogServiceImpl extends LoggerService implements BlogCatalogService{

    @Resource
    private BlogCatalogDao blogCatalogDao;

	/**
	* 多条件查询(分页)
	* @param blogCatalogDO BlogCatalogDO
	* @param pagination  Pagination
	* @return List<BlogCatalogDO>
    */
    @Override
    public OptionsResponseDO<List<BlogCatalogDO>> queryBlogCatalogDOListForPage(BlogCatalogDO blogCatalogDO, Pagination pagination) throws BusinessException{
        try {
            int totalCount = blogCatalogDao.selectCountByParams(blogCatalogDO);
            if (totalCount <= 0) {
                return newStaticOptionsResponseDO(new ArrayList<BlogCatalogDO>());
            }
            pagination.setTotalCount(totalCount);
            List<BlogCatalogDO> blogCatalogDOList = blogCatalogDao.selectListByParams(blogCatalogDO, pagination.getRowBounds());
            return newOptionsResponseDO(totalCount, blogCatalogDOList, pagination.getPageSize(),pagination.getPP());
        } catch (Exception e) {
            throw new BusinessException("多条件查询BlogCatalogDO信息异常",e);
        }
    }

    /**
    * 根据用户ID查询
    * @param id Long
    * @return ResponseDO<BlogCatalogDO>
    */
    @Override
    @Cacheable(value = "common:blogCatalogDO",key = "'common:blogCatalogDO:id:' + #id")
    public ResponseDO<BlogCatalogDO> getBlogCatalogDOById(Long id) throws BusinessException{
        try {
                        Assert.isTrue(id != null,"查询Id不能为空!");
                        BlogCatalogDO blogCatalogDO = blogCatalogDao.selectById(id);
            return newStaticResponseDO(blogCatalogDO);
        } catch (Exception e) {
            throw new BusinessException("根据ID=>[" + id + "]查询BlogCatalogDO信息异常",e);
        }
    }

    /**
    * 添加
    * @param blogCatalogDO BlogCatalogDO
    * @return ResponseDO<BlogCatalogDO>
    */
    @Override
    @CachePut(value = "common:blogCatalogDO",key = "'common:blogCatalogDO:id:' + #blogCatalogDO.id")
    public ResponseDO<BlogCatalogDO> addBlogCatalogDO(BlogCatalogDO blogCatalogDO) throws BusinessException{
        try {
            // Validate
            validateForAdd(blogCatalogDO);

            // Insert
            int result = blogCatalogDao.insert(blogCatalogDO);
            if (result < 1) {
                throw new Exception("sql插入数据为0,请检查各项参数!");
            }
            logger.info("新增ID=>[" + blogCatalogDO.getId() + "]的BlogCatalogDO成功");
            return newStaticResponseDO(blogCatalogDO);
        } catch (Exception e) {
            throw new BusinessException("新增ID=>[" + blogCatalogDO.getId() + "]的BlogCatalogDO信息异常",e);
        }
    }

    /**
    * Validate Add
    * @param blogCatalogDO BlogCatalogDO
    */
    private void validateForAdd(BlogCatalogDO blogCatalogDO) {
        Assert.isTrue(blogCatalogDO != null,"blogCatalogDO不能为空!");
        // TODO Validate
    }

    /**
    * 修改
    * @param blogCatalogDO BlogCatalogDO
	* @return ResponseDO<BlogCatalogDO>
    */
    @Override
    @CacheEvict(value = "common:blogCatalogDO",key = "'common:blogCatalogDO:id:' + #blogCatalogDO.id",beforeInvocation = true)
    public ResponseDO<BlogCatalogDO> modifyBlogCatalogDO(BlogCatalogDO blogCatalogDO) throws BusinessException{
        try {

            // validate
            BlogCatalogDO oldBlogCatalogDO = validateForUpdate(blogCatalogDO);

            // Update
            blogCatalogDO.setVersion(oldBlogCatalogDO.getVersion());
            int result = blogCatalogDao.updateById(blogCatalogDO);
            if (result < 1) {
                throw new Exception("sql修改数据为0,请检查各项参数!");
            }
            logger.info("修改ID=>[" + blogCatalogDO.getId() + "]的blogCatalogDO成功!");
            return newStaticResponseDO(blogCatalogDO);
        }catch (Exception e){
            logger.error("修改ID=>[" + blogCatalogDO.getId() + "]的blogCatalogDO异常!");
            throw new BusinessException("修改ID=>[" + blogCatalogDO.getId() +"]的BlogCatalogDO信息异常",e);
        }
    }

    /**
    * Validate Update
    * @param blogCatalogDO BlogCatalogDO
    * @return BlogCatalogDO
    */
    private BlogCatalogDO validateForUpdate(BlogCatalogDO blogCatalogDO) {

        Assert.isTrue(blogCatalogDO != null,"blogCatalogDO不能为空!");
                Assert.isTrue(blogCatalogDO.getId() != null,"查询Id不能为空!");
                // TODO Validate
        BlogCatalogDO oldBlogCatalogDO = blogCatalogDao.selectById(blogCatalogDO.getId());
        Assert.notNull(oldBlogCatalogDO,"查询不到ID=>[" + blogCatalogDO.getId() + "]的信息!");
        return oldBlogCatalogDO;
    }

    /**
    * 删除(逻辑删除)
    * @param id Long
    * @return ResponseDO<BlogCatalogDO>
    * @throws BusinessException
    */
    @Override
    @CacheEvict(value = "common:blogCatalogDO",key = "'common:blogCatalogDO:id:' + #id",beforeInvocation = true)
    public ResponseDO<BlogCatalogDO> delBlogCatalogDO(Long id) throws BusinessException{
        try {
            // validate
                        Assert.isTrue(id != null,"查询Id不能为空!");
            
            BlogCatalogDO oldblogCatalogDO = blogCatalogDao.selectById(id);
            Assert.isTrue(oldblogCatalogDO != null,"查询不到ID=>" + id + "的信息!");
            BlogCatalogDO blogCatalogDO = new BlogCatalogDO();
            blogCatalogDO.setId(id);
            blogCatalogDO.setIsDeleted(1);
            blogCatalogDO.setVersion(oldblogCatalogDO.getVersion());
            // update
            int result = blogCatalogDao.updateIsDeleteById(blogCatalogDO);
            if (result < 1) {
                throw new Exception("数据已删除,请勿重复操作!");
            }
            logger.info("删除ID=>[" + id + "]的blogCatalogDO成功!");
            return newStaticResponseDO(blogCatalogDO);
        }catch (Exception e){
            logger.error("删除ID=>[" + id + "]的blogCatalogDO异常!");
            throw new BusinessException("删除ID=>[" + id +"]的BlogCatalogDO异常",e);
        }
    }
}
