package org.wsh.common.service.api;

import org.wsh.common.model.basic.ModuleDO;
import org.wsh.common.model.basic.RoleDO;
import org.wsh.common.pager.pagination.Pagination;
import org.wsh.common.support.beans.OptionsResponseDO;
import org.wsh.common.support.exception.BusinessException;
import org.wsh.common.support.response.ResponseDO;

import java.util.List;

public interface RoleService {
	
	/**
	 * 获取所有角色
	 * @return
	 */
	public List<RoleDO> getAllRole() throws BusinessException;
	
	/**
	 * 多条件查询角色信息
	 * @param roleQuery
	 * @param pagination
	 * @return
	 */
//	public List<RoleDO> getRoleListByParams(RoleQuery roleQuery, Pagination pagination);

	/**
	 * 多条件查询角色信息
	 * @param roleDO
	 * @param pagination
	 * @return
	 */
	public OptionsResponseDO<List<RoleDO>> getRoleListByParams(RoleDO roleDO, Pagination pagination)throws BusinessException;
	
	/**
	 * 添加角色信息
	 * @param roleName
	 * @param roleCode
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResponseDO doAddRole(Long currentUserId, String roleName, String roleCode)throws BusinessException;
	
	/**
	 * 根据角色ID删除角色信息
	 * @param roleId
	 * @return
	 * @throws BusinessException
	 */
	@SuppressWarnings("rawtypes")
	public ResponseDO doDelRoleById(Long roleId)throws BusinessException;
	
	/**
	 * 添加角色信息
	 * @param roleDO
	 * @return
	 */
	public int addRole(RoleDO roleDO);
	
	/**
	 * 根据角色ID获取角色信息(包括此角色所拥有的所有权限信息)
	 * @param roleId 角色ID
	 * @return
	 */
	public RoleDO getRoleAndPermsByRoleId(Long roleId);
	
	/**
	 * 根据角色ID获取所有权限及此角色已拥有的权限
	 * @param roleId 角色ID
	 * @return
	 */
	public List<ModuleDO> getModuleAndPermsByRoleId(Long roleId);
	
	/**
	 * 给角色添加权限
	 * @param roleId
	 * @param permissionIds
	 */
	public boolean addPermsForRole(Long roleId, String permissionIds, Long createUserId);
	
	/**
	 * 给角色添加权限
	 * @param roleId
	 * @param permissionIds
	 */
	public boolean addPermsForRole(Long roleId, Long[] permissionIds, Long createUserId);

	/**
	 * 根据角色ID获取角色信息
	 * @param roleId
	 * @return
	 * @throws BusinessException
	 */
	public RoleDO getRoleById(Long roleId)throws BusinessException;
	
	/**
	 * 修改角色信息
	 * @param currentUserId
	 * @param roleName
	 * @param roleCode
	 * @return
	 * @throws BusinessException
	 */
	@SuppressWarnings("rawtypes")
	public ResponseDO doUpdateRole(Long currentUserId, Long roleId, String roleName, String roleCode)throws BusinessException;
}

