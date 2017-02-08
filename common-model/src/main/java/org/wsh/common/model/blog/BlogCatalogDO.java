package org.wsh.common.model.blog;

import lombok.Getter;
import lombok.Setter;
import org.wsh.common.model.base.BaseDO;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  Blogcatalog模型
* since Date： 2017-02-08 14:50:42
*/
public class BlogCatalogDO extends BaseDO{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 名称
	 */
	@Setter
	@Getter
	private String name;
		
	/**
	 * 父ID
	 */
	@Setter
	@Getter
	private Long fatherId;
		
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
	
	public BlogCatalogDO() {
	}

	public BlogCatalogDO(String name, Long fatherId) {
		this.name = name;
		this.fatherId = fatherId;
	}
}