package org.wsh.common.service.test.blog;

import org.springframework.transaction.annotation.Transactional;
import org.wsh.common.model.blog.BlogCatalogDO;
import org.wsh.common.pager.pagination.Pagination;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wsh.common.service.api.blog.BlogCatalogService;
import org.wsh.common.support.beans.OptionsResponseDO;
import org.wsh.common.support.response.ResponseDO;
import org.wsh.common.util.logger.LoggerService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;
import java.util.List;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  服务接口单元测试
* since Date： 2017-02-08 14:50:42
*/
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:common-bean.xml")
public class BlogCatalogServiceTest extends LoggerService{

    @Resource
    private BlogCatalogService blogCatalogService;

    private Long id = 1L;

    @Test
    public void add() {
        try {
            BlogCatalogDO blogCatalogDO = insert();
            id = blogCatalogDO.getId();
            logger.info(blogCatalogDO.toString());
        } catch (Exception e) {
            logger.error("添加数据异常!", e);
        }
    }

    private BlogCatalogDO insert() throws Exception {
        BlogCatalogDO blogCatalogDO = new BlogCatalogDO();

        ResponseDO<BlogCatalogDO> responseDO = blogCatalogService.addBlogCatalogDO(blogCatalogDO);
        logger.info("Result:" + responseDO.isSuccess() + "ErrorCode:" + responseDO.getErrorCode() + "ErrorMsg:" + responseDO.getErrorMsg());
        return blogCatalogDO;
    }

    @Test
    public void selectListForPage() {
        try {
            BlogCatalogDO blogCatalogDO = new BlogCatalogDO();
            OptionsResponseDO<List<BlogCatalogDO>> responseDO = blogCatalogService.queryBlogCatalogDOListForPage(blogCatalogDO, new Pagination());
            if (responseDO.isSuccess()) {
                for (BlogCatalogDO blogCatalogDO1 : responseDO.getData()) {
                    logger.info(blogCatalogDO1.toString());
                }
            } else {
                logger.info("分页查询列表为空!");
            }
        } catch (Exception e) {
            logger.error("分页查询列表数据异常!", e);
        }
    }

    @Test
    public void selectById() {
        try {
            ResponseDO<BlogCatalogDO> responseDO = blogCatalogService.getBlogCatalogDOById(id);
            if (responseDO.isSuccess()) {
                logger.info(responseDO.getData().toString());
            }
        } catch (Exception e) {
            logger.error("根据ID查询表数据异常!", e);
        }
    }

    @Test
    public void updateById() {
        try {
            ResponseDO<BlogCatalogDO> oldResponseDO = blogCatalogService.getBlogCatalogDOById(id);
            BlogCatalogDO blogCatalogDO = oldResponseDO.getData();
            ResponseDO<BlogCatalogDO> responseDO = blogCatalogService.modifyBlogCatalogDO(blogCatalogDO);
            logger.info("Result:" + responseDO.isSuccess());
        } catch (Exception e) {
            logger.error("根据ID更新表数据异常!", e);
        }
    }

    @Test
    public void delete() {
        try {
            BlogCatalogDO blogCatalogDO = new BlogCatalogDO();
            blogCatalogDO.setId(id);
            ResponseDO<BlogCatalogDO> responseDO = blogCatalogService.delBlogCatalogDO(id);
            logger.info("Result:" + responseDO.isSuccess());
        } catch (Exception e) {
            logger.error("根据ID逻辑删除表数据异常!", e);
        }
    }
}