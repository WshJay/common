package org.wsh.common.dao;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import org.wsh.common.model.basic.PermissionDO;

import java.util.List;

@Repository
public interface PermissionDAO {
	
	/**
	 * 获取所有权限信息
	 * @return
	 */
	List<PermissionDO> queryAll();
	
	/**
	 * 根据ID查询权限信息
	 * @param permissionId
	 * @return
	 */
	PermissionDO loadById(Long permissionId);
	
	/**
	 * 根据地址查询权限信息
	 * @param target 地址
	 * @return
	 */
	PermissionDO queryByTarget(String target);
	
	/**
	 * 多条件查询用户信息(分页)
	 * @param permissionDO
	 * @param rowBounds
	 * @return
	 */
	List<PermissionDO> queryByParams(PermissionDO permissionDO, RowBounds rowBounds);
	int queryCountByParams(PermissionDO permissionDO);
	
	/**
	 * 添加权限信息
	 * @param permissionDO
	 * @return
	 */
	int insertPermission(PermissionDO permissionDO);
	
	/**
	 * 修改权限信息
	 * @param permissionDO
	 */
	void updatePermission(PermissionDO permissionDO);
	
	/**
	 * 根据角色ID获取此角色下的所有权限信息
	 * @param permissionId
	 * @return
	 */
	List<PermissionDO> queryListByRoleId(Long permissionId);
	
	/**
	 * 根据权限ID删除角色信息 
	 * @param permissionId
	 */
	void deleteById(Long permissionId);
	
}
