package org.wsh.common.service.api;

import org.wsh.common.model.basic.PermissionDO;
import org.wsh.common.model.basic.RolePermissionDO;
import org.wsh.common.pager.pagination.Pagination;
import org.wsh.common.support.beans.OptionsResponseDO;
import org.wsh.common.support.exception.BusinessException;
import org.wsh.common.support.response.ResponseDO;

import java.util.List;
import java.util.Set;


public interface PermissionService {
	
	/**
	 * 根据权限ID获取权限信息
	 * @param permissionId
	 * @return
	 * @throws BusinessException
	 */
	public PermissionDO getPermissionById(Long permissionId) throws BusinessException;
	
	/**
	 * 多条件查询权限信息
	 * @param permissionDO
	 * @param pagination
	 * @return
	 */
	public OptionsResponseDO<List<PermissionDO>> getPermissionListByParams(PermissionDO permissionDO, Pagination pagination)throws BusinessException;

	/**
	 * 添加权限
	 * @param permissionDO
	 * @return
	 */
	public int addPermission(PermissionDO permissionDO);
	
	/**
	 * 添加权限
	 * @param currentUserId
	 * @param name
	 * @param code
	 * @param target
	 * @param allow
	 * @param type
	 * @param moduleId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResponseDO doAddPermission(Long currentUserId, String name, String code, String target, int allow, int type, Long moduleId)throws BusinessException;
	
	/**
	 * 修改权限信息
	 * @param currentUserId
	 * @param name
	 * @param code
	 * @param target
	 * @param allow
	 * @param type
	 * @param moduleId
	 * @param permissionId
	 * @return
	 * @throws BusinessException
	 */
	@SuppressWarnings("rawtypes")
	public ResponseDO doUpdatePermission(Long currentUserId, String name, String code, String target, int allow, int type, Long moduleId, Long permissionId)throws BusinessException;
	
	/**
	 * 删除权限信息
	 * @param currentUserId
	 * @param permissionId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResponseDO doDelPermission(Long currentUserId, Long permissionId) throws BusinessException;
	
	/**
	 * 获取所有权限Ids
	 * @return
	 */
	@Deprecated
	public String getAllPermissionIds();

	/**
	 * 获取所有权限信息
	 * @return
	 */
	public List<PermissionDO> getAllPermission();

	/***
	 * 获取角色权限对应关系
	 * @return
     */
	public List<RolePermissionDO> getRolePermissionList();
}

