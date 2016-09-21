package com.xxx.common.test.model.dto;

import java.util.List;

import com.xxx.common.repository.base.BaseDO;

import lombok.Getter;
import lombok.Setter;

public class RoleDO extends BaseDO{
	
	private static final long serialVersionUID = -2262595717800221799L;

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