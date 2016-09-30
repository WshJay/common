package org.wsh.common.dao;

import org.springframework.stereotype.Repository;
import org.wsh.common.model.basic.UserRoleDO;

@Repository
public interface UserRoleDAO {
	
	/**
	 * 添加用户角色关联信息
	 * @param userRoleDO
	 * @return
	 */
	Long insertUserRole(UserRoleDO userRoleDO);
	
	/**
	 * 根据用户ID删除用户角色关联信息
	 * @param userId 用户ID
	 */
	void deleteByUserId(Long userId);
	
}
