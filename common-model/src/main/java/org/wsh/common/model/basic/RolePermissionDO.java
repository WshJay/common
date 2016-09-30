package org.wsh.common.model.basic;

import lombok.Getter;
import lombok.Setter;
import org.wsh.common.model.base.BaseDO;

public class RolePermissionDO extends BaseDO {
	
	private static final long serialVersionUID = 6728208589101369989L;

	/**
	 * 角色ID
	 */
	@Setter
	@Getter
	private Long roleId;
		
	/**
	 * 权限ID
	 */
	@Setter
	@Getter
	private Long permissionId;
	
	/**
	 * 创建用户ID
	 */
	@Setter
	@Getter
	private Long createUserId;
	
}