package org.wsh.common.test.image;

import org.junit.Test;
import org.wsh.common.util.file.FileUtil;
import org.wsh.common.util.image.ImageUtils;
import org.wsh.common.util.logger.LoggerService;

import java.io.File;
import java.util.List;

import static org.wsh.common.util.file.FileUtil.getDirFileList;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-02-15 10:58
 */
public class ImageUtilTest extends LoggerService{

    @Test
    public void test(){
//        ImageUtils.scale("E:/abc.jpg","E:/a1.jpg",1,false);
//        ImageUtils.scale2("E:/abc.jpg","E:/a2.jpg",200, 400,false);

        List<File> imageList = FileUtil.getDirFileList("E:/images",null);
        for (File file : imageList) {
            System.out.println(file.getName());
            if (file.getPath().contains("jpg")){
                ImageUtils.scale(file.getPath(),file.getPath(),1,false);
//                ImageUtils.scale2(file.getPath(),file.getPath(),320, 240,false);
            }else{
                String fileName = file.getName().replace("JPG","jpg");
                ImageUtils.scale(file.getPath(),"E:/images/" + fileName,1,false);
//                ImageUtils.scale2(file.getPath(),"E:/images/" + fileName,320, 240,false);
            }
//            ImageUtils.scale(file.getPath(),file.getPath(),2,false);
        }
    }
}
