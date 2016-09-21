package com.xxx.common.repository.dao ;

import java.util.List;
import com.xxx.common.repository.base.SqlMapper;
import com.xxx.common.repository.entity.RolePermissionDO;

public interface RolePermissionDAO extends SqlMapper {
	
	/**
	 * 获取表数据
	 */
	public List<RolePermissionDO> getRolePermissionDOList();
	
	/**
	 * 根据角色ID删除角色权限关联信息
	 * @param roleId
	 */
	void deleteByRoleId(Long roleId);
	
	/**
	 * 添加角色权限关联信息
	 * @param rolePermissionDO
	 * @return
	 */
	int insertRolePermission(RolePermissionDO rolePermissionDO);
	
}
