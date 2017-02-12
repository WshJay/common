package org.wsh.common.model.blog;

import lombok.Getter;
import lombok.Setter;
import org.wsh.common.model.base.BaseDO;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  Blogtags模型
* since Date： 2017-02-07 16:53:38
*/
public class BlogTagsDO extends BaseDO{

	private static final long serialVersionUID = 1L;

	/**
	 * 名称
	 */
	@Setter
	@Getter
	private String name;
		
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
	 * 用户ID
	 */
	@Setter
	@Getter
	private Long userId;
	
	public BlogTagsDO() {
	}

	public BlogTagsDO(String name) {
		this.name = name;
	}
}