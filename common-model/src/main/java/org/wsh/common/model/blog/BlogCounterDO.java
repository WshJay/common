package org.wsh.common.model.blog;

import lombok.Getter;
import lombok.Setter;
import org.wsh.common.model.base.BaseDO;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  Blogcounter模型
* since Date： 2017-02-08 16:57:38
*/
public class BlogCounterDO extends BaseDO{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 父ID
	 */
	@Setter
	@Getter
	private Long blogId;
		
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
		
	/**
	 * 浏览量
	 */
	@Setter
	@Getter
	private int viewNum;
		
	/**
	 * 评论数量
	 */
	@Setter
	@Getter
	private int commentNum;
		
	/**
	 * 赞数量
	 */
	@Setter
	@Getter
	private int praiseNum;
	
	public BlogCounterDO() {
	}

	public BlogCounterDO(Long blogId) {
		this.blogId = blogId;
	}
}