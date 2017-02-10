package org.wsh.common.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.wsh.common.enums.expection.Errors;
import org.wsh.common.model.blog.BlogCatalogDO;
import org.wsh.common.model.blog.BlogCounterDO;
import org.wsh.common.model.blog.BlogDO;
import org.wsh.common.model.blog.BlogTagsDO;
import org.wsh.common.pager.pagination.Pagination;
import org.wsh.common.rest.response.BlogListVO;
import org.wsh.common.service.api.blog.*;
import org.wsh.common.service.api.basic.UserBasicService;
import org.wsh.common.support.beans.OptionsResponseDO;
import org.wsh.common.support.response.ResponseDO;
import org.wsh.common.util.collections.CollectionUtils;
import org.wsh.common.util.logger.LoggerService;

import javax.annotation.Resource;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static com.sun.tools.internal.xjc.reader.Ring.add;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-02-08 16:02
 */
@Path("/blog")
@Controller
public class BlogHomeController extends LoggerService{

    @Resource
    private BlogService blogService;

    @Resource
    private BlogTagsService blogTagsService;

    @Resource
    private BlogCommentService blogCommentService;

    @Resource
    private BlogCounterService blogCounterService;

    @Resource
    private BlogCatalogService blogCatalogService;

    @Resource
    private UserBasicService userBasicService;


    @GET
    @Path("/list")
    @Produces("application/json")
    public ResponseDO list(){
        OptionsResponseDO<List<BlogDO>> optionsResponseDO = new OptionsResponseDO<>();
        try {
            optionsResponseDO = blogService.queryBlogDOListForPage(new BlogDO(),new Pagination());
            OptionsResponseDO<List<BlogListVO>> responseDO = new OptionsResponseDO<>();
            List<BlogListVO> blogListVOs = new ArrayList<>();
            HashSet<Long> authorIdSet = CollectionUtils.ListCovertKeySet("authorId",optionsResponseDO.getData());
            HashSet<Long> idSet = CollectionUtils.ListCovertKeySet("id",optionsResponseDO.getData());
            if (!org.springframework.util.CollectionUtils.isEmpty(idSet)){
                ResponseDO<Map<Long,String>> userResponse = userBasicService.queryUserListByIds(new ArrayList<>(authorIdSet));

                ResponseDO<List<BlogCounterDO>> bCounterResponseDO = blogCounterService.queryBlogCounterDOListByBlogIds(new ArrayList<>(idSet));
                for (BlogDO blogDO : optionsResponseDO.getData()) {
                    BlogListVO blogListVO = new BlogListVO(blogDO);
                    blogListVO.setAuthorName(userResponse.getData().get(blogDO.getAuthorId()));
                    for (BlogCounterDO blogCounterDO : bCounterResponseDO.getData()) {
                        if (blogCounterDO.getBlogId().equals(blogDO.getId())){
//                            blogListVO.counterToBlogVO(blogListVO,blogCounterDO);
                            blogListVO.counterToBlogVO(blogCounterDO);
                        }
                    }
                    blogListVOs.add(blogListVO);
                }
            }
            responseDO.setData(blogListVOs);
            return responseDO;
        } catch (Exception e) {
            logger.error("查询异常!",e);
            return optionsResponseDO;
        }
    }

    @GET
    @Path("/tags/list")
    @Produces("application/json")
    public ResponseDO tagsList(){
        OptionsResponseDO<List<BlogTagsDO>> optionsResponseDO = new OptionsResponseDO<>();
        try {
            optionsResponseDO = blogTagsService.queryBlogTagsDOListForPage(new BlogTagsDO(),new Pagination());
            return optionsResponseDO;
        } catch (Exception e) {
            logger.error("查询异常!",e);
            return optionsResponseDO;
        }
    }

    @GET
    @Path("/catalog/list")
    @Produces("application/json")
    public ResponseDO catalogList(){
        OptionsResponseDO<List<BlogCatalogDO>> optionsResponseDO = new OptionsResponseDO<>();
        try {
            optionsResponseDO = blogCatalogService.queryBlogCatalogDOListForPage(new BlogCatalogDO(),new Pagination());
            return optionsResponseDO;
        } catch (Exception e) {
            logger.error("查询异常!",e);
            return optionsResponseDO;
        }
    }

    @GET
    @Path("/blog/{id}")
    @Produces("application/json")
    public ResponseDO detail(@PathParam("id") Long blogId){

        try {
            Assert.notNull(blogId,"ID不能为空");
            logger.info("blogId:" + blogId);
            return blogService.getBlogDOById(blogId);
        } catch (Exception e) {
            logger.error("查询异常!",e);
            return new ResponseDO(Errors.DEFAULT_ERROR);
        }
    }

    @POST
    @Path("/add/view")
    @Produces("application/json")
    public ResponseDO addViewNum(@FormParam("blogId") Long blogId){

        try {
            Assert.notNull(blogId,"ID不能为空");
            logger.info("blogId:" + blogId);
            return blogCounterService.addViewNum(blogId);
        } catch (Exception e) {
            logger.error("修改异常!",e);
            return new ResponseDO(Errors.DEFAULT_ERROR);
        }
    }
}
