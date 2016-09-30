package org.wsh.common.query;

import lombok.Getter;
import lombok.Setter;
import org.wsh.common.pager.base.Query;

public class RoleQuery extends Query {

	/**
	 * 角色名
	 */
	@Setter
	@Getter
	private String roleName;
		
	/**
	 * 角色code
	 */
	@Setter
	@Getter
	private String roleCode;
		
	/**
	 * 
	 */
	@Setter
	@Getter
	private int status;
		
	/**
	 * 创建用户ID
	 */
	@Setter
	@Getter
	private Long createUserId;
}

