package org.wsh.common.model.basic;

import lombok.Getter;
import lombok.Setter;
import org.wsh.common.model.base.BaseDO;

import java.util.Set;

public class RoleDO extends BaseDO {
	
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
	
	
	/**
	 * 角色下所有权限
	 */
	@Setter
	@Getter
	private Set<PermissionDO> permissionSet;
	
}