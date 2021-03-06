package org.wsh.common.query;

import lombok.Getter;
import lombok.Setter;
import org.wsh.common.pager.base.Query;

public class MenuQuery extends Query {
	
	/**
	 * 栏目名称
	 */
	@Setter
	@Getter
	private String name;
		
	/**
	 * 资源ID
	 */
	@Setter
	@Getter
	private Long resId;
		
	/**
	 * 父栏目ID
	 */
	@Setter
	@Getter
	private Long fatherId;
		
	/**
	 * 状态(0表示展示，1表示不展示)
	 */
	@Setter
	@Getter
	private int status;
}

