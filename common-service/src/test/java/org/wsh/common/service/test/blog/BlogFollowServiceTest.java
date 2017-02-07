package org.wsh.common.service.test.blog;

import org.springframework.transaction.annotation.Transactional;
import org.wsh.common.model.blog.BlogFollowDO;
import org.wsh.common.pager.pagination.Pagination;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wsh.common.service.api.blog.BlogFollowService;
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
public class BlogFollowServiceTest extends LoggerService{

    @Resource
    private BlogFollowService blogFollowService;

    private Long id = 1L;

    @Test
    public void add() {
        try {
            BlogFollowDO blogFollowDO = insert();
            id = blogFollowDO.getId();
            logger.info(blogFollowDO.toString());
        } catch (Exception e) {
            logger.error("添加数据异常!", e);
        }
    }

    private BlogFollowDO insert() throws Exception {
        BlogFollowDO blogFollowDO = new BlogFollowDO();

        ResponseDO<BlogFollowDO> responseDO = blogFollowService.addBlogFollowDO(blogFollowDO);
        logger.info("Result:" + responseDO.isSuccess() + "ErrorCode:" + responseDO.getErrorCode() + "ErrorMsg:" + responseDO.getErrorMsg());
        return blogFollowDO;
    }

    @Test
    public void selectListForPage() {
        try {
            BlogFollowDO blogFollowDO = new BlogFollowDO();
            OptionsResponseDO<List<BlogFollowDO>> responseDO = blogFollowService.queryBlogFollowDOListForPage(blogFollowDO, new Pagination());
            if (responseDO.isSuccess()) {
                for (BlogFollowDO blogFollowDO1 : responseDO.getData()) {
                    logger.info(blogFollowDO1.toString());
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
            ResponseDO<BlogFollowDO> responseDO = blogFollowService.getBlogFollowDOById(id);
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
            ResponseDO<BlogFollowDO> oldResponseDO = blogFollowService.getBlogFollowDOById(id);
            BlogFollowDO blogFollowDO = oldResponseDO.getData();
            ResponseDO<BlogFollowDO> responseDO = blogFollowService.modifyBlogFollowDO(blogFollowDO);
            logger.info("Result:" + responseDO.isSuccess());
        } catch (Exception e) {
            logger.error("根据ID更新表数据异常!", e);
        }
    }

    @Test
    public void delete() {
        try {
            BlogFollowDO blogFollowDO = new BlogFollowDO();
            blogFollowDO.setId(id);
            ResponseDO<BlogFollowDO> responseDO = blogFollowService.delBlogFollowDO(id);
            logger.info("Result:" + responseDO.isSuccess());
        } catch (Exception e) {
            logger.error("根据ID逻辑删除表数据异常!", e);
        }
    }
}