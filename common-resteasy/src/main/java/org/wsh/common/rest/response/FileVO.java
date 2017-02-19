package org.wsh.common.rest.response;

import lombok.Getter;
import lombok.Setter;
import org.wsh.common.enums.flow.FileType;
import org.wsh.common.model.blog.BlogCounterDO;
import org.wsh.common.model.flow.FileDO;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017/2/12 16:20
 */
public class FileVO {

    /**
     * 名称
     */
    @Setter
    @Getter
    private String name;

    /**
     * 封面地址
     */
    @Setter
    @Getter
    private String coverPath;

    /**
     * 地址
     */
    @Setter
    @Getter
    private String filePath;

    /**
     * 文件类型[IMG:图片,TEXT:文本,ZIP:压缩包]
     */
    @Setter
    @Getter
    private FileType type;

    /**
     * 用户ID
     */
    @Setter
    @Getter
    private Long userId;

    /**
     * 标签ID
     */
    @Setter
    @Getter
    private Long tagsId;

    /**
     * 描述
     */
    @Setter
    @Getter
    private String description;

    public FileVO() {

    }

    public FileVO(FileDO fileDO) {
        this.name = fileDO.getName();
        this.coverPath = fileDO.getCoverPath();
        this.filePath = fileDO.getFilePath();
        this.type = fileDO.getType();
        this.userId = fileDO.getUserId();
        this.tagsId = fileDO.getTagsId();
        this.description = fileDO.getDescription();
    }
}
