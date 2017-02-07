package org.wsh.common.model.blog;

import lombok.Getter;
import lombok.Setter;
import org.wsh.common.model.base.BaseDO;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  Blogfollow模型
* since Date： 2017-02-07 16:53:38
*/
public class BlogFollowDO extends BaseDO{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 被关注者ID
	 */
	@Setter
	@Getter
	private Long followId;
		
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
	
	public BlogFollowDO() {
	}

	public BlogFollowDO(Long followId, Long userId) {
		this.followId = followId;
		this.userId = userId;
	}
}