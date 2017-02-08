package org.wsh.common.service.test.blog;

import org.springframework.transaction.annotation.Transactional;
import org.wsh.common.model.blog.BlogCounterDO;
import org.wsh.common.pager.pagination.Pagination;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wsh.common.service.api.blog.BlogCounterService;
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
* since Date： 2017-02-08 16:57:38
*/
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:common-bean.xml")
public class BlogCounterServiceTest extends LoggerService{

    @Resource
    private BlogCounterService blogCounterService;

    private Long id = 1L;

    @Test
    public void add() {
        try {
            BlogCounterDO blogCounterDO = insert();
            id = blogCounterDO.getId();
            logger.info(blogCounterDO.toString());
        } catch (Exception e) {
            logger.error("添加数据异常!", e);
        }
    }

    private BlogCounterDO insert() throws Exception {
        BlogCounterDO blogCounterDO = new BlogCounterDO();

        ResponseDO<BlogCounterDO> responseDO = blogCounterService.addBlogCounterDO(blogCounterDO);
        logger.info("Result:" + responseDO.isSuccess() + "ErrorCode:" + responseDO.getErrorCode() + "ErrorMsg:" + responseDO.getErrorMsg());
        return blogCounterDO;
    }

    @Test
    public void selectListForPage() {
        try {
            BlogCounterDO blogCounterDO = new BlogCounterDO();
            OptionsResponseDO<List<BlogCounterDO>> responseDO = blogCounterService.queryBlogCounterDOListForPage(blogCounterDO, new Pagination());
            if (responseDO.isSuccess()) {
                for (BlogCounterDO blogCounterDO1 : responseDO.getData()) {
                    logger.info(blogCounterDO1.toString());
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
            ResponseDO<BlogCounterDO> responseDO = blogCounterService.getBlogCounterDOById(id);
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
            ResponseDO<BlogCounterDO> oldResponseDO = blogCounterService.getBlogCounterDOById(id);
            BlogCounterDO blogCounterDO = oldResponseDO.getData();
            ResponseDO<BlogCounterDO> responseDO = blogCounterService.modifyBlogCounterDO(blogCounterDO);
            logger.info("Result:" + responseDO.isSuccess());
        } catch (Exception e) {
            logger.error("根据ID更新表数据异常!", e);
        }
    }

    @Test
    public void delete() {
        try {
            BlogCounterDO blogCounterDO = new BlogCounterDO();
            blogCounterDO.setId(id);
            ResponseDO<BlogCounterDO> responseDO = blogCounterService.delBlogCounterDO(id);
            logger.info("Result:" + responseDO.isSuccess());
        } catch (Exception e) {
            logger.error("根据ID逻辑删除表数据异常!", e);
        }
    }
}