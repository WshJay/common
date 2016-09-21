package com.xxx.common.repository.dao ;

import com.xxx.common.repository.base.SqlMapper;
import com.xxx.common.repository.entity.UserRoleDO;

public interface UserRoleDAO extends SqlMapper {
	
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
