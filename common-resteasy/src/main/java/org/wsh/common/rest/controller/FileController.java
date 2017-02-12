package org.wsh.common.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.wsh.common.enums.expection.Errors;
import org.wsh.common.model.blog.BlogTagsDO;
import org.wsh.common.model.flow.FileDO;
import org.wsh.common.pager.pagination.Pagination;
import org.wsh.common.rest.response.FileVO;
import org.wsh.common.service.api.blog.BlogTagsService;
import org.wsh.common.service.api.flow.FileService;
import org.wsh.common.support.beans.OptionsResponseDO;
import org.wsh.common.support.response.ResponseDO;
import org.wsh.common.util.logger.LoggerService;

import javax.annotation.Resource;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017/2/12 16:11
 */
@Path("/file")
@Controller
public class FileController extends LoggerService{

    @Resource
    private FileService fileService;

    @Resource
    private BlogTagsService blogTagsService;

    @GET
    @Path("/image/list")
    @Produces("application/json")
    public ResponseDO list(){
        try {
            FileDO fileParamDO = new FileDO();
            fileParamDO.setType("IMG");
            fileParamDO.setUserId(2L);
//            if (tagsId != null){
//                fileParamDO.setTagsId(tagsId);
//            }
//            OptionsResponseDO<List<FileVO>> optionsResponseVO = new OptionsResponseDO<>();
//            OptionsResponseDO<List<FileDO>> optionsResponseDO = fileService.queryFileDOListForPage(fileParamDO,new Pagination(100));
//            if (!CollectionUtils.isEmpty(optionsResponseDO.getData())){
//                List<FileVO> fileList = new ArrayList<>();
//                for (FileDO fileDO : optionsResponseDO.getData()) {
//                    FileVO fileVO = new FileVO(fileDO);
//                    fileList.add(fileVO);
//                }
//                optionsResponseVO.setData(fileList);
//            }
            return fileService.queryFileDOListForPage(fileParamDO,new Pagination(100));
        } catch (Exception e) {
            logger.error("查询异常!",e);
            return new ResponseDO(Errors.DEFAULT_ERROR);
        }
    }

    @GET
    @Path("/tags/list/{userId}")
    @Produces("application/json")
    public ResponseDO userTagsList(@PathParam("userId") Long userId){
        try {
            Assert.notNull(userId,"参数不能为空!");
            BlogTagsDO blogTagsDO = new BlogTagsDO();
            blogTagsDO.setUserId(userId);
            return blogTagsService.queryBlogTagsDOListForPage(blogTagsDO,new Pagination(6));
        } catch (Exception e) {
            logger.error("查询异常!",e);
            return new ResponseDO(Errors.DEFAULT_ERROR);
        }
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public ResponseDO detail(@PathParam("id") Long id){
        try {
            Assert.notNull(id,"参数不能为空!");

            return fileService.getFileDOById(id);
        } catch (Exception e) {
            logger.error("查询异常!",e);
            return new ResponseDO(Errors.DEFAULT_ERROR);
        }
    }
}
