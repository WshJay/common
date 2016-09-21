package com.xxx.common.repository.entity;

import lombok.Getter;
import lombok.Setter;
import com.xxx.common.repository.base.BaseDO;

public class PermissionDO extends BaseDO{
	
	private static final long serialVersionUID = 8215473615521324651L;

	/**
	 * 权限名称
	 */
	@Setter
	@Getter
	private String name;
		
	/**
	 * 权限代码
	 */
	@Setter
	@Getter
	private String code;
		
	/**
	 * 目标地址
	 */
	@Setter
	@Getter
	private String target;
		
	/**
	 * 是否验证(0表示不验证权限，1表示验证权限)
	 */
	@Setter
	@Getter
	private int allow;
		
	/**
	 * 类型
	 */
	@Setter
	@Getter
	private int type;
		
	/**
	 * 模块ID
	 */
	@Setter
	@Getter
	private Long moduleId;
		
	/**
	 * 创建用户ID
	 */
	@Setter
	@Getter
	private Long createUserId;
	
	
	/**
	 * 是否拥有权限
	 */
	@Setter
	@Getter
	private boolean isOwn = false;
	
}