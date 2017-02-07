package org.wsh.common.service.test.blog;

import org.springframework.transaction.annotation.Transactional;
import org.wsh.common.model.blog.BlogInnerTagsDO;
import org.wsh.common.pager.pagination.Pagination;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wsh.common.service.api.blog.BlogInnerTagsService;
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
* since Date： 2017-02-07 16:06:55
*/
//@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:common-bean.xml")
public class BlogInnerTagsServiceTest extends LoggerService{

    @Resource
    private BlogInnerTagsService blogInnerTagsService;

    private Long id = 1L;

    @Test
    public void add() {
        try {
            BlogInnerTagsDO blogInnerTagsDO = insert();
            id = blogInnerTagsDO.getId();
            logger.info(blogInnerTagsDO.toString());
        } catch (Exception e) {
            logger.error("添加数据异常!", e);
        }
    }

    private BlogInnerTagsDO insert() throws Exception {
        BlogInnerTagsDO blogInnerTagsDO = new BlogInnerTagsDO();

        ResponseDO<BlogInnerTagsDO> responseDO = blogInnerTagsService.addBlogInnerTagsDO(blogInnerTagsDO);
        logger.info("Result:" + responseDO.isSuccess() + "ErrorCode:" + responseDO.getErrorCode() + "ErrorMsg:" + responseDO.getErrorMsg());
        return blogInnerTagsDO;
    }

    @Test
    public void selectListForPage() {
        try {
            BlogInnerTagsDO blogInnerTagsDO = new BlogInnerTagsDO();
            OptionsResponseDO<List<BlogInnerTagsDO>> responseDO = blogInnerTagsService.queryBlogInnerTagsDOListForPage(blogInnerTagsDO, new Pagination());
            if (responseDO.isSuccess()) {
                for (BlogInnerTagsDO blogInnerTagsDO1 : responseDO.getData()) {
                    logger.info(blogInnerTagsDO1.toString());
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
            ResponseDO<BlogInnerTagsDO> responseDO = blogInnerTagsService.getBlogInnerTagsDOById(id);
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
            ResponseDO<BlogInnerTagsDO> oldResponseDO = blogInnerTagsService.getBlogInnerTagsDOById(id);
            BlogInnerTagsDO blogInnerTagsDO = oldResponseDO.getData();
            ResponseDO<BlogInnerTagsDO> responseDO = blogInnerTagsService.modifyBlogInnerTagsDO(blogInnerTagsDO);
            logger.info("Result:" + responseDO.isSuccess());
        } catch (Exception e) {
            logger.error("根据ID更新表数据异常!", e);
        }
    }

    @Test
    public void delete() {
        try {
            BlogInnerTagsDO blogInnerTagsDO = new BlogInnerTagsDO();
            blogInnerTagsDO.setId(id);
            ResponseDO<BlogInnerTagsDO> responseDO = blogInnerTagsService.delBlogInnerTagsDO(id);
            logger.info("Result:" + responseDO.isSuccess());
        } catch (Exception e) {
            logger.error("根据ID逻辑删除表数据异常!", e);
        }
    }
}