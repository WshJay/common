package org.wsh.common.rest.controller;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.wsh.common.enums.blog.BlogStatus;
import org.wsh.common.enums.blog.Privacy;
import org.wsh.common.enums.expection.Errors;
import org.wsh.common.enums.flow.FileType;
import org.wsh.common.model.blog.BlogDO;
import org.wsh.common.model.blog.BlogTagsDO;
import org.wsh.common.model.flow.FileDO;
import org.wsh.common.pager.pagination.Pagination;
import org.wsh.common.service.api.blog.BlogTagsService;
import org.wsh.common.service.api.flow.FileService;
import org.wsh.common.support.response.ResponseDO;
import org.wsh.common.util.file.FileUtil;
import org.wsh.common.util.image.ImageUtils;
import org.wsh.common.util.image.jpgScaleZoom;
import org.wsh.common.util.logger.LoggerService;
import org.springframework.beans.factory.annotation.Value;
import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.io.File.separator;

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

    @Value("#{upload.rootPath}")
    private String UPLOADED_FILE_PATH;

    @Value("#{upload.realPath}")
    private String PAINT_IMAGE_PATH;

    @Value("#{upload.imgHeight}")
    private Integer IMAGE_HEIGHT;

    @GET
    @Path("/image/list")
    @Produces("application/json")
    public ResponseDO list() {
        try {
            FileDO fileParamDO = new FileDO();
            fileParamDO.setType(FileType.IMG);
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

    @POST
    @Path("/add")
    @Produces("application/json")
    public ResponseDO addBlog(@FormParam("name") String name,
                              @FormParam("filePath") String filePath, @FormParam("tagsId") Long tagsId){
        try {
            Assert.notNull(name,"参数不能为空");
            Assert.notNull(filePath,"参数不能为空");
            Assert.notNull(tagsId,"参数不能为空");

            String imgPath = UPLOADED_FILE_PATH + filePath;
            String realPath = PAINT_IMAGE_PATH + filePath;

            File file = new File(imgPath);
            FileUtil.CopyFile(imgPath,realPath);

            logger.debug("type:" + imgPath.split("\\.")[1]);

            String imgThumUrl = new StringBuffer(File.separator).append("upload").append(File.separator).append("images")
                    .append(File.separator).append("thumbnail").append(File.separator).append(file.getName()).toString();
            String imgThumbPath = UPLOADED_FILE_PATH + imgThumUrl;
            jpgScaleZoom test = new jpgScaleZoom(imgPath, imgThumbPath, "width", IMAGE_HEIGHT);
            test.makeImg();

            return fileService.addFileDO(new FileDO(FileType.IMG,name,imgThumUrl,filePath,2L,tagsId,""));
        } catch (Exception e) {
            logger.error("添加异常!",e);
            return new ResponseDO(Errors.DEFAULT_ERROR);
        }
    }

    @POST
    @Path(value = "/upload/image")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public ResponseDO uploadFile(MultipartFormDataInput input) {
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        List<InputPart> inputParts = uploadForm.get("imgfile");
        if (!CollectionUtils.isEmpty(inputParts)) {
            for (InputPart inputPart : inputParts)
                try {
                    // 解决文件名乱码问题
                    inputPart.setMediaType(MediaType.TEXT_PLAIN_TYPE);
                    MultivaluedMap<String, String> header = inputPart.getHeaders();
                    String oldfileName = getFileName(header);
                    if (StringUtils.isBlank(oldfileName)) {
                        return new ResponseDO(Errors.PARAMETER_IS_ERROR);
                    }
                    //convert the uploaded file to inputstream
                    InputStream inputStream = inputPart.getBody(InputStream.class, null);
                    String[] imageTypes = oldfileName.split("\\.");
                    if (imageTypes.length <= 1) {
                        return new ResponseDO(Errors.PARAMETER_IS_ERROR);
                    }
                    if (!ImageUtils.validateImgType(imageTypes[1])) {
                        return new ResponseDO(Errors.PARAMETER_IS_ERROR);
                    }
                    String fileName = System.currentTimeMillis() + "";

                    String imgUrl = new StringBuffer(File.separator).append("upload").append(File.separator).append("images")
                            .append(File.separator).append(fileName).append(".").append(imageTypes[1]).toString();
                    String imgPath = UPLOADED_FILE_PATH + imgUrl;
                    boolean writeResult = writeFile(inputStream, imgPath);
                    if (writeResult) {
                        HashMap<String, String> imgMap = new HashMap<>();
                        imgMap.put("filePath", imgUrl);
                        return new ResponseDO(imgMap);
                    } else {
                        return new ResponseDO(Errors.PARAMETER_IS_ERROR);
                    }
                } catch (IOException e) {
                    logger.error("上传图片异常!",e);
                    return new ResponseDO(Errors.PARAMETER_IS_ERROR);
                } catch (Exception e) {
                    logger.error("上传图片异常!",e);
                    return new ResponseDO(Errors.PARAMETER_IS_ERROR);
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

    public static void main(String[] args) {
        String s = "小辉.jpg";
        System.out.println(s.split("\\..")[0]);
    }
}
