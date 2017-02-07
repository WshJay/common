package org.wsh.common.service.test.blog;

import org.springframework.transaction.annotation.Transactional;
import org.wsh.common.model.blog.BlogTagsDO;
import org.wsh.common.pager.pagination.Pagination;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wsh.common.service.api.blog.BlogTagsService;
import org.wsh.common.support.beans.OptionsResponseDO;
import org.wsh.common.support.response.ResponseDO;
import org.wsh.common.util.logger.LoggerService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;
import java.util.ArrayList;
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
public class BlogTagsServiceTest extends LoggerService{

    @Resource
    private BlogTagsService blogTagsService;

    private Long id = 1L;

    @Test
    public void add() {
        try {

            List<String> tagList = new ArrayList<>();
            tagList.add("清新");
            tagList.add("电影");
            tagList.add("宠物");
            tagList.add("语录");
            tagList.add("美女");
            tagList.add("旅行");
            tagList.add("北京");
            tagList.add("艺术");
            tagList.add("美图");
            tagList.add("模特");
            tagList.add("心情");
            tagList.add("摄影");
            for (String s : tagList) {
                BlogTagsDO blogTagsDO = insert(s);
                id = blogTagsDO.getId();
                logger.info(blogTagsDO.toString());
            }
        } catch (Exception e) {
            logger.error("添加数据异常!", e);
        }
    }

    private BlogTagsDO insert(String name) throws Exception {

        BlogTagsDO blogTagsDO = new BlogTagsDO(name);

        ResponseDO<BlogTagsDO> responseDO = blogTagsService.addBlogTagsDO(blogTagsDO);
        logger.info("Result:" + responseDO.isSuccess() + "ErrorCode:" + responseDO.getErrorCode() + "ErrorMsg:" + responseDO.getErrorMsg());
        return blogTagsDO;
    }

    @Test
    public void selectListForPage() {
        try {
            BlogTagsDO blogTagsDO = new BlogTagsDO();
            OptionsResponseDO<List<BlogTagsDO>> responseDO = blogTagsService.queryBlogTagsDOListForPage(blogTagsDO, new Pagination());
            if (responseDO.isSuccess()) {
                for (BlogTagsDO blogTagsDO1 : responseDO.getData()) {
                    logger.info(blogTagsDO1.toString());
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
            ResponseDO<BlogTagsDO> responseDO = blogTagsService.getBlogTagsDOById(id);
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
            ResponseDO<BlogTagsDO> oldResponseDO = blogTagsService.getBlogTagsDOById(id);
            BlogTagsDO blogTagsDO = oldResponseDO.getData();
            ResponseDO<BlogTagsDO> responseDO = blogTagsService.modifyBlogTagsDO(blogTagsDO);
            logger.info("Result:" + responseDO.isSuccess());
        } catch (Exception e) {
            logger.error("根据ID更新表数据异常!", e);
        }
    }

    @Test
    public void delete() {
        try {
            BlogTagsDO blogTagsDO = new BlogTagsDO();
            blogTagsDO.setId(id);
            ResponseDO<BlogTagsDO> responseDO = blogTagsService.delBlogTagsDO(id);
            logger.info("Result:" + responseDO.isSuccess());
        } catch (Exception e) {
            logger.error("根据ID逻辑删除表数据异常!", e);
        }
    }
}