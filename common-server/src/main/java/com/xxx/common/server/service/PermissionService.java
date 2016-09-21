package com.xxx.common.server.service;

import java.util.List;
import java.util.Set;

import com.xxx.common.repository.data.beans.OptionsResponseDO;
import com.xxx.common.repository.data.support.ResponseDO;
import com.xxx.common.repository.entity.PermissionDO;
import com.xxx.common.repository.entity.RoleDO;
import com.xxx.common.repository.pagination.Pagination;
import com.xxx.common.repository.query.PermissionQuery;
import com.xxx.common.util.exception.BusinessException;

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
	 * @param permissionQuery
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
}

