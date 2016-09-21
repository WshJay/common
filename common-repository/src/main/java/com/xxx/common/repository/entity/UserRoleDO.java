package com.xxx.common.repository.entity;

import lombok.Getter;
import lombok.Setter;
import com.xxx.common.repository.base.BaseDO;

public class UserRoleDO extends BaseDO{
	
	private static final long serialVersionUID = 8579715081618580816L;

	/**
	 * 用户ID
	 */
	@Setter
	@Getter
	private Long userId;
		
	/**
	 * 角色ID
	 */
	@Setter
	@Getter
	private Long roleId;
	
	/**
	 * 创建用户ID
	 */
	@Setter
	@Getter
	private Long createUserId;
	
}