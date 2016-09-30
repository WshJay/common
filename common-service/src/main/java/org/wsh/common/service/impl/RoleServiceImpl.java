package org.wsh.common.service.impl;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.wsh.common.service.api.RoleService;
import org.wsh.common.dao.ModuleDAO;
import org.wsh.common.dao.PermissionDAO;
import org.wsh.common.dao.RoleDAO;
import org.wsh.common.dao.RolePermissionDAO;
import org.wsh.common.enums.expection.Errors;
import org.wsh.common.model.basic.ModuleDO;
import org.wsh.common.model.basic.PermissionDO;
import org.wsh.common.model.basic.RoleDO;
import org.wsh.common.model.basic.RolePermissionDO;
import org.wsh.common.pager.pagination.Pagination;
import org.wsh.common.support.beans.OptionsResponseDO;
import org.wsh.common.support.exception.BusinessException;
import org.wsh.common.support.response.ResponseDO;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.wsh.common.support.beans.BeansUtil.newOptionsResponseDO;
import static org.wsh.common.support.beans.BeansUtil.newResponseDO;
import static org.wsh.common.support.beans.BeansUtil.newStaticOptionsResponseDO;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDAO roleDAO;
	
	@Autowired
	private ModuleDAO moduleDAO;
	
	@Autowired
	private PermissionDAO permissionDAO;
	
	@Autowired
	private RolePermissionDAO rolePermissionDAO;
	
	private final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);
	
	@PostConstruct
    public void init() {
		log.info("初始化执行...");
    }
	
	/*@SuppressWarnings("unchecked")
	@Override
	public List<RoleDO> getRoleListByParams(RoleQuery roleQuery,
			Pagination pagination) {
		int totalCount = roleDAO.queryCountByParams(roleQuery);
		if (totalCount <= 0) {
			return Collections.EMPTY_LIST;
		}
		pagination.setTotalCount(totalCount);
		List<RoleDO> roleList = roleDAO.queryByParams(roleQuery, pagination.getRowBounds());
		return roleList;
	}*/

	@Override
	public int addRole(RoleDO roleDO) {
		roleDO.setCreateUserId(1l);
		return roleDAO.insertRole(roleDO);
	}

	@Override
	public RoleDO getRoleAndPermsByRoleId(Long roleId) {
		if (roleId <= 0) {
			return null;
		}
		RoleDO roleDO = roleDAO.loadById(roleId);
		List<PermissionDO> permissionList = permissionDAO.queryListByRoleId(roleId);
		if (!CollectionUtils.isEmpty(permissionList)) {
			roleDO.setPermissionSet(new HashSet<PermissionDO>(permissionList));
		}
		return roleDO;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<ModuleDO> getModuleAndPermsByRoleId(Long roleId) {
		if (roleId <= 0) {
			return Collections.EMPTY_LIST;
		}
		// 根据角色ID获取角色信息
		RoleDO roleDO = roleDAO.loadById(roleId);
		if (roleDO == null){
			return Collections.EMPTY_LIST;
		}
		// 获取所有模块数据
		List<ModuleDO> moduleDOList = moduleDAO.queryAll();
		// 获取所有权限信息
		List<PermissionDO> permissionList = permissionDAO.queryAll();
		// 根据角色ID获取此角色下的所有权限信息
		List<PermissionDO> rolePermsList = permissionDAO.queryListByRoleId(roleId);
		// 组装数据
		if (!CollectionUtils.isEmpty(permissionList) && !CollectionUtils.isEmpty(rolePermsList)) {
			for (PermissionDO permissionDO : permissionList) {
				for (PermissionDO rolePerm : rolePermsList) {
					if (permissionDO.getId() == rolePerm.getId()) {
						permissionDO.setOwn(true);
					}
				}
			}
		}
		for (ModuleDO moduleDO : moduleDOList) {
			List<PermissionDO> permissions = new ArrayList<PermissionDO>();
			for (PermissionDO permissionDO : permissionList) {
				if (moduleDO.getId() == permissionDO.getModuleId()) {
					permissions.add(permissionDO);
				}
			}
			moduleDO.setPermissionList(permissions);
		}
		return moduleDOList;
	}

	@Override
	public boolean addPermsForRole(Long roleId, String permissionIds, Long createUserId) {
		
		if (roleId <= 0 || StringUtils.isBlank(permissionIds) || createUserId <= 0) {
			return false;
		}
		RoleDO roleDO = roleDAO.loadById(roleId);
		if (roleDO == null) {
			return false;
		}
		// 删除此角色所有权限信息
		rolePermissionDAO.deleteByRoleId(roleId);
		String[] pIds = permissionIds.split(",");
		for (int i = 0; i < pIds.length; i++) {
			if (!StringUtils.isBlank(pIds[i])) {
				// TODO 校验权限ID真实性
				Long permissionId = Long.parseLong(pIds[i]);
				RolePermissionDO rolePermissionDO = new RolePermissionDO();
				rolePermissionDO.setRoleId(roleId);
				rolePermissionDO.setPermissionId(permissionId);
				rolePermissionDO.setCreateUserId(createUserId);
				rolePermissionDAO.insertRolePermission(rolePermissionDO);
			}
		}
		return true;
	}

	@Override
	public boolean addPermsForRole(Long roleId, Long[] permissionIds, Long createUserId) {
		
		if (roleId <= 0 || permissionIds.length == 0 || createUserId <= 0) {
			return false;
		}
		RoleDO roleDO = roleDAO.loadById(roleId);
		if (roleDO == null) {
			return false;
		}
		// 删除此角色所有权限信息
		rolePermissionDAO.deleteByRoleId(roleId);
		for (int i = 0; i < permissionIds.length; i++) {
			// TODO 校验权限ID真实性
			Long permissionId = permissionIds[i];
			RolePermissionDO rolePermissionDO = new RolePermissionDO();
			rolePermissionDO.setRoleId(roleId);
			rolePermissionDO.setPermissionId(permissionId);
			rolePermissionDO.setCreateUserId(createUserId);
			rolePermissionDAO.insertRolePermission(rolePermissionDO);
		}
		
		return true;
	}

	@Override
	public List<RoleDO> getAllRole() throws BusinessException {
		try {
			List<RoleDO> roleList = roleDAO.queryAll();
			return roleList;
		} catch (Exception e) {
			throw new BusinessException("获取所有角色信息异常",e);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public OptionsResponseDO<List<RoleDO>> getRoleListByParams(RoleDO roleDO,
															   Pagination pagination) throws BusinessException {
		try {
			int totalCount = roleDAO.queryCountByParams(roleDO);
			if (totalCount <= 0) {
				return newStaticOptionsResponseDO();
			}
			pagination.setTotalCount(totalCount);
			List<RoleDO> roleList = roleDAO.queryByParams(roleDO, pagination.getRowBounds());
			return newOptionsResponseDO(totalCount, roleList, pagination.getPageSize(),pagination.getPP());
		} catch (Exception e) {
			throw new BusinessException("多条件查询角色信息异常",e);
		}
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseDO doAddRole(Long currentUserId, String roleName, String roleCode)
			throws BusinessException {
		try {
			// TODO validata
			RoleDO roleDO = new RoleDO();
			roleDO.setRoleName(roleName);
			roleDO.setRoleCode(roleCode);
			roleDO.setCreateUserId(currentUserId);
			roleDAO.insertRole(roleDO);
			return newStaticOptionsResponseDO();
		} catch (Exception e) {
			throw new BusinessException(String.format("添加角色信息异常，参数currentUserId:[%s],roleName:[%s],roleCode:[%s]", currentUserId,roleName,roleCode));
		}
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseDO doDelRoleById(Long roleId) throws BusinessException {
		try {
			RoleDO role = roleDAO.loadById(roleId);
			if (role == null) {
				return newResponseDO(Errors.PARAMETER_IS_ERROR);
			}
			roleDAO.deleteById(roleId);
			return newStaticOptionsResponseDO();
		} catch (Exception e) {
			throw new BusinessException(String.format("根据角色ID:[%s]删除角色信息", roleId));
		}
	}

	@Override
	public RoleDO getRoleById(Long roleId) throws BusinessException {
		try {
			return roleDAO.loadById(roleId);
		} catch (Exception e) {
			throw new BusinessException(String.format("根据角色ID:[%s]查询角色信息", roleId));
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseDO doUpdateRole(Long currentUserId, Long roleId, String roleName,
			String roleCode) throws BusinessException {
		try {
			RoleDO roleDO = new RoleDO();
			roleDO.setId(roleId);
			roleDO.setRoleName(roleName);
			roleDO.setRoleCode(roleCode);
			roleDAO.updateRole(roleDO);
			return newStaticOptionsResponseDO();
		} catch (Exception e) {
			throw new BusinessException(String.format("修改角色信息异常,参数:currentUserId:[%s],roleId:[%s],roleName:[%s],roleCode:[%s]", currentUserId,roleId,roleName,roleCode));
		}
	}

}

