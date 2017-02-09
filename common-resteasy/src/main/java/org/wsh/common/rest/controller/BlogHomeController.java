package org.wsh.common.rest.controller;

import org.springframework.stereotype.Controller;
import org.wsh.common.model.blog.BlogCatalogDO;
import org.wsh.common.model.blog.BlogDO;
import org.wsh.common.model.blog.BlogTagsDO;
import org.wsh.common.pager.pagination.Pagination;
import org.wsh.common.service.api.blog.*;
import org.wsh.common.service.api.basic.UserBasicService;
import org.wsh.common.support.beans.OptionsResponseDO;
import org.wsh.common.support.response.ResponseDO;
import org.wsh.common.util.collections.CollectionUtils;
import org.wsh.common.util.logger.LoggerService;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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

            HashSet<Long> idSet = CollectionUtils.ListCovertKeySet("authorId",optionsResponseDO.getData());
            if (!org.springframework.util.CollectionUtils.isEmpty(idSet)){
                ResponseDO<Map<Long,String>> userResponse = userBasicService.queryUserListByIds(new ArrayList<>(idSet));
                for (BlogDO blogDO : optionsResponseDO.getData()) {
                    blogDO.setAuthorName(userResponse.getData().get(blogDO.getAuthorId()));
                }
            }
            return optionsResponseDO;
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
}
