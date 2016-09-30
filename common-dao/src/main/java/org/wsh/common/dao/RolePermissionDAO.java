package org.wsh.common.dao;

import org.springframework.stereotype.Repository;
import org.wsh.common.model.basic.RolePermissionDO;

import java.util.List;

@Repository
public interface RolePermissionDAO {
	
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
