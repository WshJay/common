package org.wsh.common.test.model.vo;

import lombok.Getter;
import lombok.Setter;

public class RoleVO {

	/**
	 * ID
	 */
	@Setter
	@Getter
	private int id;
	
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
}

