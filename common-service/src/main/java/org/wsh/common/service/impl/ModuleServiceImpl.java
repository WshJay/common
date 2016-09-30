package org.wsh.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wsh.common.service.api.ModuleService;
import org.wsh.common.dao.ModuleDAO;
import org.wsh.common.model.basic.ModuleDO;
import org.wsh.common.pager.pagination.Pagination;
import org.wsh.common.support.beans.OptionsResponseDO;
import org.wsh.common.support.exception.BusinessException;
import org.wsh.common.support.response.ResponseDO;

import java.util.List;

import static org.wsh.common.support.beans.BeansUtil.newOptionsResponseDO;
import static org.wsh.common.support.beans.BeansUtil.newStaticOptionsResponseDO;

@Service("moduleService")
public class ModuleServiceImpl implements ModuleService {
	
	@Autowired
	private ModuleDAO moduleDAO;

	@Override
	public List<ModuleDO> getAllModule() throws BusinessException {
		try {
			List<ModuleDO> moduleList = moduleDAO.queryAll();
			return moduleList;
		} catch (Exception e) {
			throw new BusinessException("获取所有模块信息异常",e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public OptionsResponseDO<List<ModuleDO>> getModuleListByParams(
			ModuleDO moduleDO, Pagination pagination) throws BusinessException {
		try {
			int totalCount = moduleDAO.queryCountByParams(moduleDO);
			if (totalCount <= 0) {
				return newStaticOptionsResponseDO();
			}
			pagination.setTotalCount(totalCount);
			List<ModuleDO> moduleList = moduleDAO.queryByParams(moduleDO, pagination.getRowBounds());
			return newOptionsResponseDO(totalCount, moduleList, pagination.getPageSize(),pagination.getPP());
		} catch (Exception e) {
			throw new BusinessException("多条件查询模块信息异常",e);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseDO doAddModule(Long currentUserId, String moduleName)
			throws BusinessException {
		try {
			// TODO validata
			ModuleDO moduleDO = new ModuleDO();
			moduleDO.setCreateUserId(currentUserId);
			moduleDO.setName(moduleName);
			moduleDAO.insertModule(moduleDO);
			return newStaticOptionsResponseDO();
		} catch (Exception e) {
			throw new BusinessException(String.format("添加模块信息异常，参数currentUserId:[%s],name:[%s]", currentUserId, moduleName));
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseDO doDeleteModule(Long currentUserId, Long moduleId)
			throws BusinessException {
		try {
			// TODO validata
			// TODO 修改权限中的模块信息
			moduleDAO.deleteById(moduleId);
			return newStaticOptionsResponseDO();
		} catch (Exception e) {
			throw new BusinessException(String.format("删除模块信息异常，参数currentUserId:[%s],moduleId:[%s]", currentUserId, moduleId));
		}
	}

	
}

