package com.xxx.common.repository.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.xxx.common.repository.base.BaseDO;

public class ModuleDO extends BaseDO{
	
	private static final long serialVersionUID = 3215532378050024234L;

	/**
	 * 权限描述
	 */
	@Setter
	@Getter
	private String name;
		
	/**
	 * 创建用户ID
	 */
	@Setter
	@Getter
	private Long createUserId;
	
	
	/**
	 * 模块所对应的权限列表
	 */
	@Setter
	@Getter
	private List<PermissionDO> permissionList;
	
}