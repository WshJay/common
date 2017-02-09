package org.wsh.common.model.blog;

import lombok.Getter;
import lombok.Setter;
import org.wsh.common.enums.blog.BlogStatus;
import org.wsh.common.enums.blog.Privacy;
import org.wsh.common.model.base.BaseDO;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  Blog模型
* since Date： 2017-02-07 16:53:38
*/
public class BlogDO extends BaseDO{

	/**
	 * 标题
	 */
	@Setter
	@Getter
	private String title;
		
	/**
	 * 封面地址
	 */
	@Setter
	@Getter
	private String coverUrl;
		
	/**
	 * 内容ID
	 */
	@Setter
	@Getter
	private Long contentId;
		
	/**
	 * 公开程度[private:私有,public:公开]
	 */
	@Setter
	@Getter
	private Privacy privacy;
		
	/**
	 * 状态['SCREEN':屏蔽,'MAL':正常]
	 */
	@Setter
	@Getter
	private BlogStatus status;
		
	/**
	 * 作者ID
	 */
	@Setter
	@Getter
	private Long authorId;
		
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
	 * 内容
	 */
	@Setter
	@Getter
	private String content;

	/**
	 * 作者名称
	 */
	@Setter
	@Getter
	private String authorName;
	
	public BlogDO() {
	}

	public BlogDO(String title, String coverUrl, Long contentId, Privacy privacy, BlogStatus status, Long authorId) {
		this.title = title;
		this.coverUrl = coverUrl;
		this.contentId = contentId;
		this.privacy = privacy;
		this.status = status;
		this.authorId = authorId;
	}

	public BlogDO(String title, String coverUrl, String content, Privacy privacy, BlogStatus status, Long authorId) {
		this.title = title;
		this.coverUrl = coverUrl;
		this.content = content;
		this.privacy = privacy;
		this.status = status;
		this.authorId = authorId;
	}
}