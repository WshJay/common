package org.wsh.common.model.flow;

import lombok.Getter;
import lombok.Setter;
import org.wsh.common.enums.flow.FileType;
import org.wsh.common.model.base.BaseDO;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  File模型
* since Date： 2016-12-28 11:14:25
*/
public class FileDO extends BaseDO{

	private static final long serialVersionUID = 1L;

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
		
	/**
	 * 版本号(乐观锁)
	 */
	@Setter
	@Getter
	private int version;
		
	/**
	 * 0正常,1删除
	 */
	@Setter
	@Getter
	private int isDeleted;
	
	public FileDO() {
	}

	public FileDO(FileType type, String name, String coverPath, String filePath, Long userId, Long tagsId, String description) {
		this.type = type;
		this.name = name;
		this.coverPath = coverPath;
		this.filePath = filePath;
		this.userId = userId;
		this.tagsId = tagsId;
		this.description = description;
	}

	@Override
	public String toString() {
		return "FileDO{" +
				"id='" + super.getId() + '\'' +
				"name='" + name + '\'' +
				", coverPath='" + coverPath + '\'' +
				", filePath='" + filePath + '\'' +
				", type=" + type +
				", userId=" + userId +
				", tagsId=" + tagsId +
				", description='" + description + '\'' +
				", version=" + version +
				", isDeleted=" + isDeleted +
				'}';
	}
}