package org.wsh.common.service.test.blog;

import org.springframework.transaction.annotation.Transactional;
import org.wsh.common.enums.blog.BlogStatus;
import org.wsh.common.enums.blog.Privacy;
import org.wsh.common.model.blog.BlogDO;
import org.wsh.common.pager.pagination.Pagination;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wsh.common.service.api.blog.BlogService;
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
* since Date： 2017-02-07 16:06:54
*/
//@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:common-bean.xml")
public class BlogServiceTest extends LoggerService{

    @Resource
    private BlogService blogService;

    private Long id = 1L;

    @Test
    public void add() {
        try {
            BlogDO blogDO = insert();
            id = blogDO.getId();
            logger.info(blogDO.toString());
        } catch (Exception e) {
            logger.error("添加数据异常!", e);
        }
    }

    private BlogDO insert() throws Exception {
        BlogDO blogDO = new BlogDO("我是标题","http://img1.imgtn.bdimg.com/it/u=3575133701,3444996625&fm=214&gp=0.jpg","Test...", Privacy.PUBLIC, BlogStatus.NORMAL, 1L);

        ResponseDO<BlogDO> responseDO = blogService.addBlogDO(blogDO);
        logger.info("Result:" + responseDO.isSuccess() + "ErrorCode:" + responseDO.getErrorCode() + "ErrorMsg:" + responseDO.getErrorMsg());
        return blogDO;
    }

    @Test
    public void selectListForPage() {
        try {
            BlogDO blogDO = new BlogDO();
            OptionsResponseDO<List<BlogDO>> responseDO = blogService.queryBlogDOListForPage(blogDO, new Pagination());
            if (responseDO.isSuccess()) {
                for (BlogDO blogDO1 : responseDO.getData()) {
                    logger.info(blogDO1.toString());
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
            ResponseDO<BlogDO> responseDO = blogService.getBlogDOById(id);
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
            ResponseDO<BlogDO> oldResponseDO = blogService.getBlogDOById(id);
            BlogDO blogDO = oldResponseDO.getData();
            ResponseDO<BlogDO> responseDO = blogService.modifyBlogDO(blogDO);
            logger.info("Result:" + responseDO.isSuccess());
        } catch (Exception e) {
            logger.error("根据ID更新表数据异常!", e);
        }
    }

    @Test
    public void delete() {
        try {
            BlogDO blogDO = new BlogDO();
            blogDO.setId(id);
            ResponseDO<BlogDO> responseDO = blogService.delBlogDO(id);
            logger.info("Result:" + responseDO.isSuccess());
        } catch (Exception e) {
            logger.error("根据ID逻辑删除表数据异常!", e);
        }
    }
}