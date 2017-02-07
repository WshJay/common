package org.wsh.common.service.test.blog;

import org.springframework.transaction.annotation.Transactional;
import org.wsh.common.model.blog.BlogContentDO;
import org.wsh.common.pager.pagination.Pagination;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wsh.common.service.api.blog.BlogContentService;
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
public class BlogContentServiceTest extends LoggerService{

    @Resource
    private BlogContentService blogContentService;

    private Long id = 1L;

    @Test
    public void add() {
        try {
            BlogContentDO blogContentDO = insert();
            id = blogContentDO.getId();
            logger.info(blogContentDO.toString());
        } catch (Exception e) {
            logger.error("添加数据异常!", e);
        }
    }

    private BlogContentDO insert() throws Exception {
        BlogContentDO blogContentDO = new BlogContentDO("测试...");

        ResponseDO<BlogContentDO> responseDO = blogContentService.addBlogContentDO(blogContentDO);
        logger.info("Result:" + responseDO.isSuccess() + "ErrorCode:" + responseDO.getErrorCode() + "ErrorMsg:" + responseDO.getErrorMsg());
        return blogContentDO;
    }

    @Test
    public void selectListForPage() {
        try {
            BlogContentDO blogContentDO = new BlogContentDO();
            OptionsResponseDO<List<BlogContentDO>> responseDO = blogContentService.queryBlogContentDOListForPage(blogContentDO, new Pagination());
            if (responseDO.isSuccess()) {
                for (BlogContentDO blogContentDO1 : responseDO.getData()) {
                    logger.info(blogContentDO1.toString());
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
            ResponseDO<BlogContentDO> responseDO = blogContentService.getBlogContentDOById(id);
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
            ResponseDO<BlogContentDO> oldResponseDO = blogContentService.getBlogContentDOById(id);
            BlogContentDO blogContentDO = oldResponseDO.getData();
            ResponseDO<BlogContentDO> responseDO = blogContentService.modifyBlogContentDO(blogContentDO);
            logger.info("Result:" + responseDO.isSuccess());
        } catch (Exception e) {
            logger.error("根据ID更新表数据异常!", e);
        }
    }

    @Test
    public void delete() {
        try {
            BlogContentDO blogContentDO = new BlogContentDO();
            blogContentDO.setId(id);
            ResponseDO<BlogContentDO> responseDO = blogContentService.delBlogContentDO(id);
            logger.info("Result:" + responseDO.isSuccess());
        } catch (Exception e) {
            logger.error("根据ID逻辑删除表数据异常!", e);
        }
    }
}