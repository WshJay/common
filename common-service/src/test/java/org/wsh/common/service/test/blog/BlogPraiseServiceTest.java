package org.wsh.common.service.test.blog;

import org.springframework.transaction.annotation.Transactional;
import org.wsh.common.model.blog.BlogPraiseDO;
import org.wsh.common.pager.pagination.Pagination;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wsh.common.service.api.blog.BlogPraiseService;
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
public class BlogPraiseServiceTest extends LoggerService{

    @Resource
    private BlogPraiseService blogPraiseService;

    private Long id = 1L;

    @Test
    public void add() {
        try {
            BlogPraiseDO blogPraiseDO = insert();
            id = blogPraiseDO.getId();
            logger.info(blogPraiseDO.toString());
        } catch (Exception e) {
            logger.error("添加数据异常!", e);
        }
    }

    private BlogPraiseDO insert() throws Exception {
        BlogPraiseDO blogPraiseDO = new BlogPraiseDO();

        ResponseDO<BlogPraiseDO> responseDO = blogPraiseService.addBlogPraiseDO(blogPraiseDO);
        logger.info("Result:" + responseDO.isSuccess() + "ErrorCode:" + responseDO.getErrorCode() + "ErrorMsg:" + responseDO.getErrorMsg());
        return blogPraiseDO;
    }

    @Test
    public void selectListForPage() {
        try {
            BlogPraiseDO blogPraiseDO = new BlogPraiseDO();
            OptionsResponseDO<List<BlogPraiseDO>> responseDO = blogPraiseService.queryBlogPraiseDOListForPage(blogPraiseDO, new Pagination());
            if (responseDO.isSuccess()) {
                for (BlogPraiseDO blogPraiseDO1 : responseDO.getData()) {
                    logger.info(blogPraiseDO1.toString());
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
            ResponseDO<BlogPraiseDO> responseDO = blogPraiseService.getBlogPraiseDOById(id);
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
            ResponseDO<BlogPraiseDO> oldResponseDO = blogPraiseService.getBlogPraiseDOById(id);
            BlogPraiseDO blogPraiseDO = oldResponseDO.getData();
            ResponseDO<BlogPraiseDO> responseDO = blogPraiseService.modifyBlogPraiseDO(blogPraiseDO);
            logger.info("Result:" + responseDO.isSuccess());
        } catch (Exception e) {
            logger.error("根据ID更新表数据异常!", e);
        }
    }

    @Test
    public void delete() {
        try {
            BlogPraiseDO blogPraiseDO = new BlogPraiseDO();
            blogPraiseDO.setId(id);
            ResponseDO<BlogPraiseDO> responseDO = blogPraiseService.delBlogPraiseDO(id);
            logger.info("Result:" + responseDO.isSuccess());
        } catch (Exception e) {
            logger.error("根据ID逻辑删除表数据异常!", e);
        }
    }
}