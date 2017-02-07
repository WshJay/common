package org.wsh.common.model.blog;

import lombok.Getter;
import lombok.Setter;
import org.wsh.common.model.base.BaseDO;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  Blogcontent模型
* since Date： 2017-02-07 16:53:38
*/
public class BlogContentDO extends BaseDO{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 内容
	 */
	@Setter
	@Getter
	private String content;
		
	/**
	 * 版本号(乐观锁)
	 */
	@Setter
	@Getter
	private int version;
		
	/**
	 * 备注说明
	 */
	@Setter
	@Getter
	private String description;
		
	/**
	 * 0正常,1删除
	 */
	@Setter
	@Getter
	private int isDeleted;
	
	public BlogContentDO() {
	}

	public BlogContentDO(String content) {
		this.content = content;
	}
}