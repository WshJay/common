package com.xxx.common.server.service.impl;

import static com.xxx.common.server.util.BeansUtil.newOptionsResponseDO;
import static com.xxx.common.server.util.BeansUtil.newResponseDO;
import static com.xxx.common.server.util.BeansUtil.newStaticOptionsResponseDO;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxx.common.repository.dao.PermissionDAO;
import com.xxx.common.repository.data.beans.OptionsResponseDO;
import com.xxx.common.repository.data.enums.Errors;
import com.xxx.common.repository.data.support.ResponseDO;
import com.xxx.common.repository.entity.PermissionDO;
import com.xxx.common.repository.entity.RoleDO;
import com.xxx.common.repository.entity.UserBasicDO;
import com.xxx.common.repository.pagination.Pagination;
import com.xxx.common.repository.query.PermissionQuery;
import com.xxx.common.server.service.PermissionService;
import com.xxx.common.util.exception.BusinessException;
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService{
	
	@Autowired
	private PermissionDAO permissionDAO;

	@Override
	public int addPermission(PermissionDO permissionDO) {
		permissionDO.setModuleId(1L);
		permissionDO.setCreateUserId(1L);
		return permissionDAO.insertPermission(permissionDO);
	}

	@Override
	public String getAllPermissionIds() {
		StringBuffer stringBuff = new StringBuffer();
		List<PermissionDO> pList = permissionDAO.queryAll();
		for (int i = 0; i < pList.size(); i++) {
			stringBuff.append(pList.get(i).getId());
			if (i < (pList.size() - 1)) {
				stringBuff.append(",");
			}
		}
		return stringBuff.toString();
	}

	@Override
	public List<PermissionDO> getAllPermission() {
		return permissionDAO.queryAll();
	}

	@SuppressWarnings("unchecked")
	@Override
	public OptionsResponseDO<List<PermissionDO>> getPermissionListByParams(
			PermissionDO permissionDO, Pagination pagination)
			throws BusinessException {
		try {
			int totalCount = permissionDAO.queryCountByParams(permissionDO);
			if (totalCount <= 0) {
				return newStaticOptionsResponseDO();
			}
			pagination.setTotalCount(totalCount);
			List<PermissionDO> permissionList = permissionDAO.queryByParams(permissionDO, pagination.getRowBounds());
			return newOptionsResponseDO(totalCount, permissionList, pagination.getPageSize(),pagination.getPP());
		} catch (Exception e) {
			throw new BusinessException("多条件查询权限信息异常",e);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseDO doAddPermission(Long currentUserId, String name,
			String code, String target, int allow, int type, Long moduleId)
			throws BusinessException {
		try {
			// TODO validata
			PermissionDO permissionDO = new PermissionDO();
			permissionDO.setCreateUserId(currentUserId);
			permissionDO.setName(name);
			permissionDO.setCode(code);
			permissionDO.setTarget(target);
			permissionDO.setAllow(allow);
			permissionDO.setType(type);
			permissionDO.setModuleId(moduleId);
			permissionDAO.insertPermission(permissionDO);
			return newStaticOptionsResponseDO();
		} catch (Exception e) {
			throw new BusinessException(String.format("添加权限信息异常，参数currentUserId:[%s],name:[%s],code:[%s],target:[%s],allow:[%s],type:[%s],moduleId:[%s]", currentUserId, name, code, target, allow, type, moduleId));
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseDO doUpdatePermission(Long currentUserId, String name,
			String code, String target, int allow, int type, Long moduleId, Long permissionId)
			throws BusinessException {
		try {
			// TODO validata
			PermissionDO permissionDO = new PermissionDO();
			permissionDO.setCreateUserId(currentUserId);
			permissionDO.setId(permissionId);
			permissionDO.setName(name);
			permissionDO.setCode(code);
			permissionDO.setTarget(target);
			permissionDO.setAllow(allow);
			permissionDO.setType(type);
			permissionDO.setModuleId(moduleId);
			permissionDAO.updatePermission(permissionDO);
			return newStaticOptionsResponseDO();
		} catch (Exception e) {
			throw new BusinessException(String.format("修改权限信息异常，参数currentUserId:[%s],name:[%s],code:[%s],target:[%s],allow:[%s],type:[%s],moduleId:[%s],permissionId:[%s]", currentUserId, name, code, target, allow, type, moduleId,permissionId));
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseDO doDelPermission(Long currentUserId, Long permissionId) throws BusinessException {
		try {
			PermissionDO permissionDO = permissionDAO.loadById(permissionId);
			if (permissionDO == null) {
				return newResponseDO(Errors.PARAMETER_IS_ERROR);
			}
			permissionDAO.deleteById(permissionId);
			return newStaticOptionsResponseDO();
		} catch (Exception e) {
			throw new BusinessException(String.format("根据权限ID:[%s]删除角色信息异常", permissionId));
		}
	}

	@Override
	public PermissionDO getPermissionById(Long permissionId)
			throws BusinessException {
		try {
			PermissionDO permissionDO = permissionDAO.loadById(permissionId);
			return permissionDO;
		} catch (Exception e) {
			throw new BusinessException(String.format("根据权限ID:[%s]查询角色信息异常", permissionId));
		}
	}


}

