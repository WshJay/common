package org.wsh.common.service.test.blog;

import org.springframework.transaction.annotation.Transactional;
import org.wsh.common.model.blog.BlogCommentDO;
import org.wsh.common.pager.pagination.Pagination;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wsh.common.service.api.blog.BlogCommentService;
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
* since Date： 2017-02-08 14:50:43
*/
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:common-bean.xml")
public class BlogCommentServiceTest extends LoggerService{

    @Resource
    private BlogCommentService blogCommentService;

    private Long id = 1L;

    @Test
    public void add() {
        try {
            BlogCommentDO blogCommentDO = insert();
            id = blogCommentDO.getId();
            logger.info(blogCommentDO.toString());
        } catch (Exception e) {
            logger.error("添加数据异常!", e);
        }
    }

    private BlogCommentDO insert() throws Exception {
        BlogCommentDO blogCommentDO = new BlogCommentDO();

        ResponseDO<BlogCommentDO> responseDO = blogCommentService.addBlogCommentDO(blogCommentDO);
        logger.info("Result:" + responseDO.isSuccess() + "ErrorCode:" + responseDO.getErrorCode() + "ErrorMsg:" + responseDO.getErrorMsg());
        return blogCommentDO;
    }

    @Test
    public void selectListForPage() {
        try {
            BlogCommentDO blogCommentDO = new BlogCommentDO();
            OptionsResponseDO<List<BlogCommentDO>> responseDO = blogCommentService.queryBlogCommentDOListForPage(blogCommentDO, new Pagination());
            if (responseDO.isSuccess()) {
                for (BlogCommentDO blogCommentDO1 : responseDO.getData()) {
                    logger.info(blogCommentDO1.toString());
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
            ResponseDO<BlogCommentDO> responseDO = blogCommentService.getBlogCommentDOById(id);
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
            ResponseDO<BlogCommentDO> oldResponseDO = blogCommentService.getBlogCommentDOById(id);
            BlogCommentDO blogCommentDO = oldResponseDO.getData();
            ResponseDO<BlogCommentDO> responseDO = blogCommentService.modifyBlogCommentDO(blogCommentDO);
            logger.info("Result:" + responseDO.isSuccess());
        } catch (Exception e) {
            logger.error("根据ID更新表数据异常!", e);
        }
    }

    @Test
    public void delete() {
        try {
            BlogCommentDO blogCommentDO = new BlogCommentDO();
            blogCommentDO.setId(id);
            ResponseDO<BlogCommentDO> responseDO = blogCommentService.delBlogCommentDO(id);
            logger.info("Result:" + responseDO.isSuccess());
        } catch (Exception e) {
            logger.error("根据ID逻辑删除表数据异常!", e);
        }
    }
}