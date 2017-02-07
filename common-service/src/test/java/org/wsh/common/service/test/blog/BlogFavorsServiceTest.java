package org.wsh.common.service.test.blog;

import org.springframework.transaction.annotation.Transactional;
import org.wsh.common.model.blog.BlogFavorsDO;
import org.wsh.common.pager.pagination.Pagination;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wsh.common.service.api.blog.BlogFavorsService;
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
public class BlogFavorsServiceTest extends LoggerService{

    @Resource
    private BlogFavorsService blogFavorsService;

    private Long id = 1L;

    @Test
    public void add() {
        try {
            BlogFavorsDO blogFavorsDO = insert();
            id = blogFavorsDO.getId();
            logger.info(blogFavorsDO.toString());
        } catch (Exception e) {
            logger.error("添加数据异常!", e);
        }
    }

    private BlogFavorsDO insert() throws Exception {
        BlogFavorsDO blogFavorsDO = new BlogFavorsDO();

        ResponseDO<BlogFavorsDO> responseDO = blogFavorsService.addBlogFavorsDO(blogFavorsDO);
        logger.info("Result:" + responseDO.isSuccess() + "ErrorCode:" + responseDO.getErrorCode() + "ErrorMsg:" + responseDO.getErrorMsg());
        return blogFavorsDO;
    }

    @Test
    public void selectListForPage() {
        try {
            BlogFavorsDO blogFavorsDO = new BlogFavorsDO();
            OptionsResponseDO<List<BlogFavorsDO>> responseDO = blogFavorsService.queryBlogFavorsDOListForPage(blogFavorsDO, new Pagination());
            if (responseDO.isSuccess()) {
                for (BlogFavorsDO blogFavorsDO1 : responseDO.getData()) {
                    logger.info(blogFavorsDO1.toString());
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
            ResponseDO<BlogFavorsDO> responseDO = blogFavorsService.getBlogFavorsDOById(id);
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
            ResponseDO<BlogFavorsDO> oldResponseDO = blogFavorsService.getBlogFavorsDOById(id);
            BlogFavorsDO blogFavorsDO = oldResponseDO.getData();
            ResponseDO<BlogFavorsDO> responseDO = blogFavorsService.modifyBlogFavorsDO(blogFavorsDO);
            logger.info("Result:" + responseDO.isSuccess());
        } catch (Exception e) {
            logger.error("根据ID更新表数据异常!", e);
        }
    }

    @Test
    public void delete() {
        try {
            BlogFavorsDO blogFavorsDO = new BlogFavorsDO();
            blogFavorsDO.setId(id);
            ResponseDO<BlogFavorsDO> responseDO = blogFavorsService.delBlogFavorsDO(id);
            logger.info("Result:" + responseDO.isSuccess());
        } catch (Exception e) {
            logger.error("根据ID逻辑删除表数据异常!", e);
        }
    }
}