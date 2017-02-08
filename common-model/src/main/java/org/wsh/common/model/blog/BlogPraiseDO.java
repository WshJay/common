package org.wsh.common.model.blog;

import lombok.Getter;
import lombok.Setter;
import org.wsh.common.model.base.BaseDO;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  BlogPraise模型
* since Date： 2017-02-08 14:50:43
*/
public class BlogPraiseDO extends BaseDO{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 用户ID
	 */
	@Setter
	@Getter
	private Long userId;
		
	/**
	 * 博客ID
	 */
	@Setter
	@Getter
	private Long blogId;
	
	public BlogPraiseDO() {
	}

	public BlogPraiseDO(Long userId, Long blogId) {
		this.userId = userId;
		this.blogId = blogId;
	}
}