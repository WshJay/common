package org.wsh.common.model.blog;

import lombok.Getter;
import lombok.Setter;
import org.wsh.common.model.base.BaseDO;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  Blogfavors模型
* since Date： 2017-02-07 16:53:38
*/
public class BlogFavorsDO extends BaseDO{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 博客ID
	 */
	@Setter
	@Getter
	private Long blogId;
		
	/**
	 * 用户ID
	 */
	@Setter
	@Getter
	private Long userId;
		
	/**
	 * 版本号(乐观锁)
	 */
	@Setter
	@Getter
	private int version;
	
	public BlogFavorsDO() {
	}

	public BlogFavorsDO(Long blogId, Long userId) {
		this.blogId = blogId;
		this.userId = userId;
	}
}