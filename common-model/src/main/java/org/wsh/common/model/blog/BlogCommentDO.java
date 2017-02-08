package org.wsh.common.model.blog;

import lombok.Getter;
import lombok.Setter;
import org.wsh.common.enums.blog.CommentStatus;
import org.wsh.common.enums.blog.CommentType;
import org.wsh.common.model.base.BaseDO;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  Blogcomment模型
* since Date： 2017-02-08 14:50:43
*/
public class BlogCommentDO extends BaseDO{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 评论用户ID
	 */
	@Setter
	@Getter
	private Long userAId;
		
	/**
	 * 评论回复用户ID
	 */
	@Setter
	@Getter
	private Long userBId;
		
	/**
	 * 博客ID
	 */
	@Setter
	@Getter
	private Long blogId;
		
	/**
	 * 评论内容
	 */
	@Setter
	@Getter
	private String content;
		
	/**
	 * 评论类型['COMMENT':评论,'REPLY':回复,'AT':@]
	 */
	@Setter
	@Getter
	private CommentType type;
		
	/**
	 * 状态['NORMAL':正常,'DELETE':删除,'HIDDEN':隐藏]
	 */
	@Setter
	@Getter
	private CommentStatus status;
		
	/**
	 * 版本号(乐观锁)
	 */
	@Setter
	@Getter
	private int version;
	
	public BlogCommentDO() {
	}

	public BlogCommentDO(Long userAId, Long userBId, Long blogId, String content, CommentType type, CommentStatus status) {
		this.userAId = userAId;
		this.userBId = userBId;
		this.blogId = blogId;
		this.content = content;
		this.type = type;
		this.status = status;
	}
}