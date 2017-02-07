package org.wsh.common.model.blog;

import lombok.Getter;
import lombok.Setter;
import org.wsh.common.model.base.BaseDO;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  Bloginnertags模型
* since Date： 2017-02-07 16:53:38
*/
public class BlogInnerTagsDO extends BaseDO{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 博客ID
	 */
	@Setter
	@Getter
	private Long blogId;
		
	/**
	 * 博客标签ID
	 */
	@Setter
	@Getter
	private Long blogTagsId;
	
	public BlogInnerTagsDO() {
	}

	public BlogInnerTagsDO(Long blogId, Long blogTagsId) {
		this.blogId = blogId;
		this.blogTagsId = blogTagsId;
	}
}