package org.wsh.common.rest.controller;

import org.apache.commons.io.IOUtils;
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
import org.wsh.common.util.image.ImageUtils;
import org.wsh.common.util.logger.LoggerService;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import static org.apache.shiro.web.servlet.Cookie.ROOT_PATH;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017/2/12 16:11
 */
@Path("/file")
@Controller
public class FileController extends LoggerService {

    @Resource
    private FileService fileService;

    @Resource
    private BlogTagsService blogTagsService;

    @GET
    @Path("/image/list")
    @Produces("application/json")
    public ResponseDO list() {
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
            return fileService.queryFileDOListForPage(fileParamDO, new Pagination(100));
        } catch (Exception e) {
            logger.error("查询异常!", e);
            return new ResponseDO(Errors.DEFAULT_ERROR);
        }
    }

    @GET
    @Path("/tags/list/{userId}")
    @Produces("application/json")
    public ResponseDO userTagsList(@PathParam("userId") Long userId) {
        try {
            Assert.notNull(userId, "参数不能为空!");
            BlogTagsDO blogTagsDO = new BlogTagsDO();
            blogTagsDO.setUserId(userId);
            return blogTagsService.queryBlogTagsDOListForPage(blogTagsDO, new Pagination(6));
        } catch (Exception e) {
            logger.error("查询异常!", e);
            return new ResponseDO(Errors.DEFAULT_ERROR);
        }
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public ResponseDO detail(@PathParam("id") Long id) {
        try {
            Assert.notNull(id, "参数不能为空!");

            return fileService.getFileDOById(id);
        } catch (Exception e) {
            logger.error("查询异常!", e);
            return new ResponseDO(Errors.DEFAULT_ERROR);
        }
    }

    private static String UPLOADED_FILE_PATH = "E:\\";
    @POST
    @Path(value = "/upload/image")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDO uploadFile(MultipartFormDataInput input) {
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        List<InputPart> inputParts = uploadForm.get("imgfile");
        if (!CollectionUtils.isEmpty(inputParts)) {
            for (InputPart inputPart : inputParts) {
                try {
                    MultivaluedMap<String, String> header = inputPart.getHeaders();
                    String fileName = new String(getFileName(header).getBytes("UTF-8"));
                    //convert the uploaded file to inputstream
                    InputStream inputStream = inputPart.getBody(InputStream.class, null);
                    String[] imageTypes = fileName.split(".");
                    if (imageTypes.length <= 1) {
                        return new ResponseDO(Errors.PARAMETER_IS_ERROR);
                    }
                    if (!ImageUtils.validateImgType(imageTypes[1])) {
                        return new ResponseDO(Errors.PARAMETER_IS_ERROR);
                    }
                    String imgPath = ROOT_PATH + System.currentTimeMillis() + "." + imageTypes[1];
                    String imgThumbPath = ROOT_PATH + File.separator + System.currentTimeMillis() + "." + imageTypes[1];
                    boolean writeResult = writeFile(inputStream, imgPath);
                    if (writeResult) {
                        ImageUtils.scale2(imgPath, imgThumbPath, 240, 360, false);
                        HashMap<String, String> imgMap = new HashMap<>();
                        imgMap.put("coverPath", imgThumbPath);
                        imgMap.put("coverPath", imgPath);
                        return new ResponseDO(imgMap);
                    } else {
                        return new ResponseDO(Errors.PARAMETER_IS_ERROR);
                    }
                } catch (IOException e) {
                    return new ResponseDO(Errors.PARAMETER_IS_ERROR);
                }
            }
        }
        return new ResponseDO();
    }

    /**
     * 写入文件
     * @param inputStream InputStream
     * @param imgPath 文件地址
     */
    private boolean writeFile(InputStream inputStream, String imgPath) {
        FileOutputStream fop = null;
        try {
            byte[] bytes = IOUtils.toByteArray(inputStream);
            fop = new FileOutputStream(imgPath);
            fop.write(bytes);
            return true;
        } catch (IOException e) {
            logger.error("上传失败!",e);
            return false;
        } finally {
            try {
                fop.flush();
                fop.close();
                inputStream.close();
            } catch (IOException e) {
                logger.error("上传图片出错!",e);
                return false;
            }
        }
    }

    /**
     * 获取文件名
     * @param header MultivaluedMap<String, String>
     * @return 文件名
     */
    private String getFileName(MultivaluedMap<String, String> header) {
        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
        for (String filename : contentDisposition) {
            if ((filename.trim().startsWith("filename"))) {
                String[] name = filename.split("=");
                return name[1].trim().replaceAll("\"", "");
            }
        }
        return null;
    }
}
